package com.bane.fragment_one;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.bane.adapter.MyBaseAdapter;
import com.bane.adapter.ViewHolder;
import com.bane.bean.recommand.ReCommandNewBean;
import com.bane.bean.search.PgerBean;
import com.bane.myinterface.AdapterSetData;
import com.bane.myinterface.MyInterface;
import com.bane.mymain.ClickActivity;
import com.bane.url.DataStatic;
import com.bane.utils.MyAsyncTaskUtils;
import com.bane.utils.SetImageUtils;
import com.example.administrator.android32_asynctask_json.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class OneFiveFragment extends Fragment {

   private Executor executor;
    private Handler handler = new Handler();

    public OneFiveFragment() {
        // Required empty public constructor
    }

    private PullToRefreshGridView pullToRefreshGridView;

    private String path = null;



    private List<PgerBean.DataBean.PicListBean> list = new ArrayList<>();

    private ArrayList<String> list_trans = new ArrayList<String>();
    private PgerBean bean;

    private MyBaseAdapter<PgerBean.DataBean.PicListBean> adapter;


    private void assignViews(View view) {
        pullToRefreshGridView = (PullToRefreshGridView) view.findViewById(R.id.pullto_refresh_gridview_3);
        pullToRefreshGridView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        executor = Executors.newFixedThreadPool(3);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one_thrid, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDataFromActivity();

        assignViews(view);

        initDataDird();

        setOnLisenter();

    }

    private void setClickLienter() {

        pullToRefreshGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), ClickActivity.class);

                intent.putExtra("position",position);
//
//                intent.putExtra("count",list.size());
//
//                intent.putExtra("url",list.get(position).getWallPaperMiddle());

                intent.putStringArrayListExtra("list",list_trans);

                startActivity(intent);

//                Bundle b = new Bundle();
//                b.putString("url",list.get(position).getWallPaperMiddle());
//                ClickOneFragment cof = new ClickOneFragment();
//
//                cof.setArguments(b);
//                getFragmentManager().beginTransaction().replace(R.id.tab_viewPager,cof,"fragment_click").commit();
//                getFragmentManager().findFragmentByTag("fragment_click");


            }
        });
    }

    private void setOnLisenter() {

        pullToRefreshGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        adapter.notifyDataSetChanged();
                        pullToRefreshGridView.onRefreshComplete();
                        Log.i("===", "onPullUpToRefresh: ==================上啦刷新！！");

                    }
                }, DataStatic.PULL_UP_TIME);
            }
        });
    }

    private void initDataDird() {

        new MyAsyncTaskUtils<PgerBean>(new MyInterface<PgerBean>() {
            @Override
            public void getData(PgerBean o) {
                        if(o!=null){

                            bean = o;

                            list= bean.getData().getPic_List();

                            initListData();

                            initAdapter();

                            setClickLienter();

                        }
            }
        },PgerBean.class).executeOnExecutor(executor,path);
    }

    private void initListData() {
        if(list!=null) {
            for (int i = 0; i < list.size(); i++) {
                list_trans.add(list.get(i).getWallPaperMiddle());
            }
        }
    }

    private void initAdapter() {

        adapter = new MyBaseAdapter<PgerBean.DataBean.PicListBean>(getActivity(),
                list, R.layout.fragment_one_thrid_adapter, new AdapterSetData<PgerBean.DataBean.PicListBean>() {
            @Override
            public void setData(ViewHolder mHolder, PgerBean.DataBean.PicListBean o) {

                ImageView iv =mHolder.getDataOfView(R.id.fragment_one_third_image_1);

                SetImageUtils.setImage(iv,o.getWallPaperMiddle(),false);

            }
        });

        pullToRefreshGridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void getDataFromActivity() {

       Bundle b = getArguments();
        path = b.getString("path");

        Log.i("+++++++++++", "getDataFromActivity: "+path);
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        myInterface =(MyInterface<String>) activity;
//    }
}
