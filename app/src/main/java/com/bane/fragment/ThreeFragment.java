package com.bane.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bane.adapter.MyBaseAdapter;
import com.bane.adapter.ViewHolder;
import com.bane.bean.search.SearchImageBean;
import com.bane.bean.search.SearchViewPagerBean;
import com.bane.bean.search.SerchListViewBean;
import com.bane.fragment.mylist.MyListView;
import com.bane.myinterface.AdapterSetData;
import com.bane.myinterface.MyInterface;
import com.bane.mymain.SearchActivity;
import com.bane.url.DataStatic;
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
public class ThreeFragment extends Fragment {

    private static final String TAG = "333";

    private MyBaseAdapter<SerchListViewBean.DataBean> myBaseAdapter;
    private PagerAdapter pagerAdapter;
    private List<View> list_linear = new ArrayList<>();
    private List<SearchViewPagerBean.DataBean.TopicBean> listDatabean = new ArrayList<>();
    private List<SerchListViewBean.DataBean> listviewDatabean = new ArrayList<>();
    private List<SearchImageBean.DataBean> listImagebean = new ArrayList<>();
    private ViewPager mViewPager;
    private MyListView mMyListView;
    private SearchViewPagerBean svpb;
    private SerchListViewBean slvb;
    private SearchImageBean sib;
    private Handler handler = new Handler();
    private boolean flag = false;
    private RadioGroup mRadioGroup;

    private ImageView iv_pager1;
    private ImageView iv_pager2;
    private ImageView iv5;


    private TextView mFragment3ImageView1;
    private TextView mFragment3ImageView2;
    private TextView mFragment3ImageView3;
    private TextView mFragment3ImageView4;
    private TextView mFragment3ImageView5;

    private Executor executorImage;
    private Executor executorPager;
    private Executor executorListview;
    private Runnable r;

    private void assignViews(View view) {

        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mMyListView = (MyListView) view.findViewById(R.id.myListView);
        mFragment3ImageView1 = (TextView) view.findViewById(R.id.fragment3_imageView_1);
        mFragment3ImageView2 = (TextView) view.findViewById(R.id.fragment3_imageView_2);
        mFragment3ImageView3 = (TextView) view.findViewById(R.id.fragment3_imageView_3);
        mFragment3ImageView4 = (TextView) view.findViewById(R.id.fragment3_imageView_4);

        mFragment3ImageView5 = (TextView) view.findViewById(R.id.fragment3_textview5);

        iv5 = (ImageView) view.findViewById(R.id.fragment3_imageview5);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.pager_bottom_radiobutton);

        mMyListView.setFocusable(false);

        executorImage = Executors.newFixedThreadPool(3);
        executorPager = Executors.newFixedThreadPool(3);
        executorListview = Executors.newFixedThreadPool(3);
    }

    public ThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_three, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assignViews(view);

        initImageViewData();

        initViewPagerData();

        initPagerOnListener();

        initListviewData();

        initSetPosition();

    }


    private void initPagerOnListener() {

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mRadioGroup.check(position % list_linear.size());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:

                        handler.removeCallbacks(r);
                        flag = true;

                        break;
                    case ViewPager.SCROLL_STATE_IDLE:

                        if (flag) {
                            handler.postDelayed(r, DataStatic.CHANGE_MILLS);
                            flag = false;
                        }

                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:

                        break;

                    default:
                        break;
                }
            }
        });

    }

    private void initRadioButton() {
        if (list_linear != null) {
            for (int i = 0; i < list_linear.size(); i++) {

                RadioButton rb = new RadioButton(getActivity());
                rb.setClickable(false);
                rb.setId(i);

                mRadioGroup.addView(rb);
            }
        }

    }

    private void initSetPosition() {

        mViewPager.setCurrentItem(20000);

        r = new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);

                handler.postDelayed(this, DataStatic.CHANGE_MILLS);
            }
        };

        handler.postDelayed(r, DataStatic.CHANGE_MILLS);

    }

    private void initImageViewData() {

        new MyAsyncTaskUtils<SearchImageBean>(new MyInterface<SearchImageBean>() {
            @Override
            public void getData(SearchImageBean o) {

                if (o != null) {
                    sib = o;
                    listImagebean = sib.getData();
                    initSetImageData();
                }
            }
        }, SearchImageBean.class).executeOnExecutor(executorImage, InternetURL.SEARIMAGEBEAN);
    }

    private void initSetImageData() {
        // TODO: 2016/8/18 0018

        mFragment3ImageView1.setText(listImagebean.get(0).getKeyword());
        SetImageUtils.setImageText(mFragment3ImageView1, listImagebean.get(0).getImgs().get(0), true);
        mFragment3ImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("type","3");
                intent.putExtra("name", listImagebean.get(0).getKeyword());
                intent.putExtra("path", InternetURL.RCRANDOMBEAN);

                startActivity(intent);

//                intent.putExtra("path",InternetURL.SEARIMAGE+listImagebean.get(0).getKeyword());
//                try {
//                    byte[] buf =listImagebean.get(0).getKeyword().getBytes("UTF-8");
//                    String key = new String(buf,"GBK");
//                    String path = InternetURL.SEARIMAGE+ key;
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                  }

            }
        });
        mFragment3ImageView2.setText(listImagebean.get(1).getKeyword());
        SetImageUtils.setImageText(mFragment3ImageView2, listImagebean.get(1).getImgs().get(0), true);
        mFragment3ImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("type","3");
                intent.putExtra("name", listImagebean.get(1).getKeyword());
                intent.putExtra("path", InternetURL.RCRANDOMBEAN);
                startActivity(intent);

            }
        });

        mFragment3ImageView3.setText(listImagebean.get(2).getKeyword());
        SetImageUtils.setImageText(mFragment3ImageView3, listImagebean.get(2).getImgs().get(0), true);
        mFragment3ImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("type","3");
                intent.putExtra("name", listImagebean.get(2).getKeyword());
                intent.putExtra("path", InternetURL.RCRANDOMBEAN);
                startActivity(intent);

            }
        });


        mFragment3ImageView4.setText(listImagebean.get(3).getKeyword());
        SetImageUtils.setImageText(mFragment3ImageView4, listImagebean.get(3).getImgs().get(0), true);
        mFragment3ImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("type","3");
                intent.putExtra("name", listImagebean.get(3).getKeyword());
                intent.putExtra("path", InternetURL.RCRANDOMBEAN);
                startActivity(intent);

            }
        });
    }

    private void initPagerAdapter() {

        pagerAdapter = new PagerAdapter() {

            @Override
            public int getCount() {

                return 50000;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
                container.removeView(list_linear.get(position % list_linear.size()));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                container.addView(list_linear.get(position % list_linear.size()));
                return list_linear.get(position % list_linear.size());
            }
        };

        mViewPager.setAdapter(pagerAdapter);

        pagerAdapter.notifyDataSetChanged();

    }

    private void initListAdapter() {

        myBaseAdapter = new MyBaseAdapter<SerchListViewBean.DataBean>(
                getActivity(), listviewDatabean, R.layout.fragment_three_listview_adapter, new AdapterSetData<SerchListViewBean.DataBean>() {
            @Override
            public void setData(ViewHolder mHolder, SerchListViewBean.DataBean slvb_db) {

                TextView tv = mHolder.getDataOfView(R.id.fragment3_textview2);
                tv.setText(slvb_db.getKeyword());

                ImageView iv1 = mHolder.getDataOfView(R.id.fragment3_image1);
                SetImageUtils.setImage(iv1, slvb_db.getImgs().get(0), false);

                ImageView iv2 = mHolder.getDataOfView(R.id.fragment3_image2);
                SetImageUtils.setImage(iv2, slvb_db.getImgs().get(1), false);

                ImageView iv3 = mHolder.getDataOfView(R.id.fragment3_image3);
                SetImageUtils.setImage(iv3, slvb_db.getImgs().get(2), false);
            }
        });

        mMyListView.setAdapter(myBaseAdapter);
        myBaseAdapter.notifyDataSetChanged();

        mMyListView.setFocusable(false);

    }

    private void initViewPagerData() {

        new MyAsyncTaskUtils<SearchViewPagerBean>(new MyInterface<SearchViewPagerBean>() {
            @Override
            public void getData(SearchViewPagerBean svpbean) {
                if (svpbean != null) {
                    svpb = svpbean;
                    listDatabean = svpb.getData().getTopic();
                    Log.i(TAG, "listDatabean.size(): --------------" + listDatabean.size());
                    initPagerImage();

                }
            }
        }, SearchViewPagerBean.class).executeOnExecutor(executorPager, InternetURL.SEARPAGERBEAN);
    }

    private void initPagerImage() {
        if (listDatabean != null) {

            for (int i = 0; i < listDatabean.size() / 2; i++) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_three_viewpager, null);

                iv_pager1 = (ImageView) view.findViewById(R.id.fragment3_pager_1);
                iv_pager2 = (ImageView) view.findViewById(R.id.fragment3_pager_2);

                SetImageUtils.setImage(iv_pager1, listDatabean.get(2 * i).getCover_path(), true);
                SetImageUtils.setImage(iv_pager2, listDatabean.get(2 * i + 1).getCover_path(), true);

                //TODO: 2016/8/18 0018
                final int finalI = i;
                iv_pager1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),SearchActivity.class);
                        intent.putExtra("type","5");
                        intent.putExtra("name",listDatabean.get(2 * finalI).getSubject());
                        intent.putExtra("path",InternetURL.SERAPAGER+listDatabean.get(2 * finalI).getId());
                        startActivity(intent);
                    }
                });

                iv_pager2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),SearchActivity.class);
                        intent.putExtra("type","5");
                        intent.putExtra("name",listDatabean.get(2 * finalI+1).getSubject());
                        intent.putExtra("path",InternetURL.SERAPAGER+listDatabean.get(2 * finalI+1).getId());
                        startActivity(intent);
                    }
                });

                list_linear.add(view);
            }
        }

        initPagerAdapter();

        initRadioButton();

    }


    private void initListviewData() {

        new MyAsyncTaskUtils<SerchListViewBean>(new MyInterface<SerchListViewBean>() {
            @Override
            public void getData(SerchListViewBean slvbean) {
                if (slvbean != null) {
                    slvb = slvbean;
                    listviewDatabean = slvb.getData();

                    initListAdapter();

                    initListLisenter();

                    Log.i(TAG, "getData: listviewDatabean" + listviewDatabean);
                }
            }
        }, SerchListViewBean.class).executeOnExecutor(executorListview, InternetURL.SEARLISTVIEWBEAN);
    }

    private void initListLisenter() {

        mMyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(),SearchActivity.class);
                intent.putExtra("type","3");
                intent.putExtra("name",listviewDatabean.get(position).getKeyword());
                intent.putExtra("path",InternetURL.RCHOTBEAN);
                startActivity(intent);
            }
        });
    }
}
