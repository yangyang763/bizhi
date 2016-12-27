package com.bane.mymain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.bane.bean.DataBeanClazz;
import com.bane.myinterface.MyInterface;
import com.bane.utils.MyAsyncTaskUtils;
import com.example.administrator.android32_asynctask_json.R;

import java.util.ArrayList;
import java.util.List;

public class MyMainActivity extends AppCompatActivity {

    private String path = "http://bz.budejie.com/?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=wallPaperNew&index=1&size=60&bigid=0";
    private DataBeanClazz dataBean;
    List<DataBeanClazz.DataBean.WallpaperListInfoBean> WallpaperListInfo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_main);


        new MyAsyncTaskUtils<DataBeanClazz>(new MyInterface() {
            @Override
            public void getData(Object bean) {

                if(bean!=null){
                    Log.i("===", "getData: "+bean.toString());
                    dataBean = (DataBeanClazz) bean;
                    WallpaperListInfo = dataBean.getData().getWallpaperListInfo();
                }

            }
        }, DataBeanClazz.class).execute(path);


        for (DataBeanClazz.DataBean.WallpaperListInfoBean a:WallpaperListInfo) {

            Log.i("===", "onCreate: "+a.toString());
        }
    }

}
