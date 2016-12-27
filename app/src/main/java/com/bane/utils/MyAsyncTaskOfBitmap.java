package com.bane.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.bane.myinterface.BitmapCallBack;

/**
 * Created by Administrator on 2016/8/16 0016.
 */
public class MyAsyncTaskOfBitmap extends AsyncTask<String,Void,Bitmap>{
    private BitmapCallBack callBack;
    private String url =null;


    public MyAsyncTaskOfBitmap(BitmapCallBack callBack){
        this.callBack =callBack;

    }
    @Override
    protected Bitmap doInBackground(String... params) {
        url =params[0];

        return HttpURLUtils.getDataOfBitmap(url);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if(bitmap!=null){
            callBack.getDataOfBitmap(url,bitmap);
        }
    }
}
