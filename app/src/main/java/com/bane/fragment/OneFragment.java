package com.bane.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bane.fragment_one.OneThridFragment;
import com.bane.url.InternetURL;
import com.example.administrator.android32_asynctask_json.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {

    private TabLayout mTablayout;
    private FragmentManager manager;
    private PagerAdapter pagerAdapter;
    private List<Fragment> list = new ArrayList<>();
    private ViewPager mTabViewPager;

    private void assignViews(View view) {
        mTablayout = (TabLayout) view.findViewById(R.id.tablayout);

        mTabViewPager = (ViewPager) view.findViewById(R.id.tab_viewPager);
    }

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assignViews(view);

        initMyTabLayout();

        initFragments();

        initPagerAdapter();

//        manager = getFragmentManager();
//        manager.beginTransaction().add(R.id.tab_viewPager,list.get(0)).commit();
//        setTabLisener(manager);

        mTablayout.setupWithViewPager(mTabViewPager);

        //修改标题
        mTablayout.getTabAt(0).setText("最新");
        mTablayout.getTabAt(1).setText("热门");
        mTablayout.getTabAt(2).setText("随机");

    }

    private void setTabLisener(final FragmentManager manager) {

        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int index = Integer.parseInt(tab.getTag().toString());

                if(!list.get(index).isAdded()){
                    manager.beginTransaction().add(R.id.tab_viewPager,list.get(index)).commit();
                }else {
                    manager.beginTransaction().show(list.get(index)).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                int position = Integer.parseInt(tab.getTag().toString());
                manager.beginTransaction().hide(list.get(position)).commit();

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initMyTabLayout() {
        mTablayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.color_green));

        mTablayout.setSelectedTabIndicatorHeight(5);
        mTablayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.color_green));

        mTablayout.setTabMode(TabLayout.MODE_FIXED);
        mTablayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void initPagerAdapter() {

        pagerAdapter = new FragmentStatePagerAdapter(getFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return super.getPageTitle(position);
            }
        };

        mTabViewPager.setAdapter(pagerAdapter);

        pagerAdapter.notifyDataSetChanged();

    }

    private void initFragments() {

        Bundle b1 = new Bundle();
        b1.putString("path", InternetURL.RCNEWBEAN);
        OneThridFragment two1 = new OneThridFragment();
        two1.setArguments(b1);

        Bundle b2 = new Bundle();
        b2.putString("path", InternetURL.RCHOTBEAN);
        OneThridFragment two2 = new OneThridFragment();
        two2.setArguments(b2);

        Bundle b3 = new Bundle();
        b3.putString("path", InternetURL.RCRANDOMBEAN);
        OneThridFragment two3 = new OneThridFragment();
        two3.setArguments(b3);

        list.add(two1);
        list.add(two2);
        list.add(two3);

    }
}
