package com.bane.mymain;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.bane.myinterface.BitmapCallBack;
import com.bane.utils.MyAsyncTaskOfBitmap;
import com.bane.utils.MyBitmapThreebleSave;
import com.bane.utils.SetImageUtils;
import com.example.administrator.android32_asynctask_json.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClickActivity extends AppCompatActivity {

    private static final String TAG = "---===";

    private Bitmap bit;
//    private int count;
    private int position_ac;
//  private String url;
    private List<ImageView> list = new ArrayList<>();
    private ArrayList<String> list_data = new ArrayList<>();
    private PagerAdapter adapter;
    private ViewPager viewpager;
    private int index;


    private void assignViews() {
//        imageView = (ImageView) findViewById(R.id.activity_click_iamgeview_1);
        viewpager = (ViewPager) findViewById(R.id.activity_click_viewpager);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_click_viewpager);

        assignViews();

        getDataFrom();

        initImageData();

        initAdapter();

        setAdapter();

        setCurrntPosition();

        adapter.notifyDataSetChanged();
    }

    private void setCurrntPosition() {

        viewpager.setCurrentItem(position_ac);

    }

    private void setAdapter() {

        viewpager.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    private void initAdapter() {

        adapter = new PagerAdapter() {

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {

                container.addView(list.get(position));


                SetImageUtils.setImage(list.get(position),list_data.get(position),false);

                index =position;



                return list.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

//                super.destroyItem(container, position, object);
                container.removeView(list.get(position));

            }
        };

    }

    private void initImageData() {

        for (int i = 0; i < list_data.size(); i++) {

            ImageView iv = new ImageView(this);

            iv.setImageResource(R.mipmap.load);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);

            list.add(iv);
        }
    }

    public void getDataFrom() {
        Intent intent = getIntent();

//        url = intent.getStringExtra("url");
//        count= intent.getIntExtra("count",0);
        position_ac = intent.getIntExtra("position",0);

        list_data = intent.getStringArrayListExtra("list");

        Log.i(TAG, "getDataFrom: "+list_data.size()+","+position_ac);

    }


    public void onclick(View view) {

        switch (view.getId()) {
            case R.id.activity_click_button1:
                Toast.makeText(ClickActivity.this, "收藏", Toast.LENGTH_SHORT).show();

                break;
            case R.id.activity_click_button2:

                try {
                    bit = MyBitmapThreebleSave.getInstance().getBitmap(list_data.get(index));


                    setWallpaper(bit);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(ClickActivity.this, "设为壁纸", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_click_button3:
                Toast.makeText(ClickActivity.this, "下载", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_click_imageview1:
                Toast.makeText(ClickActivity.this, "返回", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_click_imageview3:
                Toast.makeText(ClickActivity.this, "分享", Toast.LENGTH_SHORT).show();

                break;

            default:
                break;
        }
    }
}
