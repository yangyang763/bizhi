package com.example.doublepager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private List<LinearLayout> list = new ArrayList<>();
    private PagerAdapter pagerAdapter;

    private void assignViews() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignViews();

        initData();

        initAdapter();

    }

    private void initAdapter() {

        pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return 4000;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

                container.removeView(list.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                container.addView(list.get(position));

                return list.get(position);
            }
        };

        viewPager.setAdapter(pagerAdapter);

        pagerAdapter.notifyDataSetChanged();
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {

        ImageView iv1 = new ImageView(this);
        iv1.setImageResource(R.mipmap.ic_launcher);
        iv1.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView iv2 = new ImageView(this);
        iv2.setImageResource(R.mipmap.ic_launcher);
        iv2.setScaleType(ImageView.ScaleType.FIT_XY);

        LinearLayout ll1 = new LinearLayout(this);

        ll1.addView(iv1);
        ll1.addView(iv2);
        list.add(ll1);
        }
    }
}
