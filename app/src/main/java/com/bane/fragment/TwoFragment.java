package com.bane.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bane.adapter.MyBaseAdapter;
import com.bane.adapter.ViewHolder;
import com.bane.bean.category.CategoryBean;
import com.bane.myinterface.AdapterSetData;
import com.bane.myinterface.MyInterface;
import com.bane.mymain.CategoryActivity;
import com.bane.url.InternetURL;
import com.bane.utils.MyAsyncTaskUtils;
import com.bane.utils.SetImageUtils;
import com.example.administrator.android32_asynctask_json.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {

    private ListView mFragment2Listivew;
    private List<CategoryBean.DataBean> listDatabean = new ArrayList<>();
    private MyBaseAdapter<CategoryBean.DataBean> adapter;
    private CategoryBean categoryBean;
    private Executor executor;

    private void assignViews(View view) {

        mFragment2Listivew = (ListView) view.findViewById(R.id.fragment2_listivew);
        executor = Executors.newFixedThreadPool(3);
    }

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assignViews(view);

        initListData();

        initAdapter();


    }

    private void setOnclick() {

        mFragment2Listivew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), CategoryActivity.class);

                intent.putExtra("data_hot",InternetURL.CATEGORY_HOT+listDatabean.get(position).getID()+"");
                intent.putExtra("data_new",InternetURL.CATEGORY_NEW+listDatabean.get(position).getID()+"");
                intent.putExtra("data_random",InternetURL.CATEGORY_RANDOM+listDatabean.get(position).getID()+"");

                intent.putExtra("name",listDatabean.get(position).getPicCategoryName());



                startActivity(intent);

            }
        });

    }

    private void initAdapter() {

        adapter = new MyBaseAdapter<CategoryBean.DataBean>(getActivity(), listDatabean, R.layout.fragment_two_adapter, new AdapterSetData<CategoryBean.DataBean>() {
            @Override
            public void setData(ViewHolder mHolder, CategoryBean.DataBean tb) {

                ImageView iv1 = mHolder.getDataOfView(R.id.fragment_two_image_1);
                iv1.setImageResource(R.mipmap.ic_launcher);

                SetImageUtils.setImage(iv1,tb.getCategoryPic(),true);

                ImageView iv2 = mHolder.getDataOfView(R.id.fragment_two_image_2);
                iv2.setImageResource(R.drawable.arrow);

                TextView tv1=  mHolder.getDataOfView(R.id.fragment_two_text_1);
                tv1.setText(tb.getPicCategoryName());

                TextView tv2 = mHolder.getDataOfView(R.id.fragment_two_text_2);
                tv2.setText(tb.getDescWords());

            }
        });

        mFragment2Listivew.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initListData() {

        new MyAsyncTaskUtils<CategoryBean>(new MyInterface<CategoryBean>() {
            @Override
            public void getData(CategoryBean result) {
                if(result!=null){
                    categoryBean =result;
                    listDatabean.addAll(categoryBean.getData());

                    setOnclick();

                }
            }
        },CategoryBean.class).executeOnExecutor(executor,InternetURL.CATEGORYBEAN);
    }
}
