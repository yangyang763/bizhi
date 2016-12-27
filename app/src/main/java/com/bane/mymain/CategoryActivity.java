package com.bane.mymain;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.bane.fragment_one.OneThridFragment;
import com.bane.url.InternetURL;
import com.example.administrator.android32_asynctask_json.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {


    private TabLayout mTablayout;
    private FragmentManager manager;
    private PagerAdapter pagerAdapter;
    private List<Fragment> list = new ArrayList<>();
    private ViewPager mTabViewPager;
    private String ID;
    private String path_hot;
    private String path_new;
    private String path_random;
    private String name;
    private TextView tv;

    private void assignViews() {
        mTablayout = (TabLayout) findViewById(R.id.activity_cate_tablayout);

        mTabViewPager = (ViewPager) findViewById(R.id.activity_cate_tab_viewPager);

        tv= (TextView) findViewById(R.id.activity_cate_textview0);


        Intent intent = getIntent();

        ID =intent.getStringExtra("data_ID");
        path_hot=intent.getStringExtra("data_hot");
        path_new=intent.getStringExtra("data_new");
        path_random=intent.getStringExtra("data_random");

        name = intent.getStringExtra("name");

        tv.setText(name);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_category);

        assignViews();

        initMyTabLayout();

        initFragments();


        initPagerAdapter();


        manager = getSupportFragmentManager();


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

        pagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

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

//        Bundle b = new Bundle();
//        b.putString("path", InternetURL.RCNEWBEAN);
//        OneFirstFragment one = new OneFirstFragment();
//        one.setArguments(b);
//        list.add(one);

//        Bundle b2 = new Bundle();
//        b2.putString("path", InternetURL.RCHOTBEAN);
//        OneFirstFragment two = new OneFirstFragment();
//        two.setArguments(b2);
//        list.add(two);

        Bundle b1 = new Bundle();
        b1.putString("path", path_hot);
        OneThridFragment two1 = new OneThridFragment();
        two1.setArguments(b1);

        Bundle b2 = new Bundle();
        b2.putString("path", path_new);
        OneThridFragment two2 = new OneThridFragment();
        two2.setArguments(b2);

        Bundle b3 = new Bundle();
        b3.putString("path", path_random);
        OneThridFragment two3 = new OneThridFragment();
        two3.setArguments(b3);

        list.add(two1);
        list.add(two2);
        list.add(two3);

//        Bundle b3 = new Bundle();
//        b3.putString("path", InternetURL.RCRANDOMBEAN);
//        OneFirstFragment three = new OneFirstFragment();
//        three.setArguments(b3);
//        list.add(three);

    }
}
