package com.bane.asynctask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.bane.myinterface.BitmapCallBack;
import com.bane.utils.HttpURLUtils;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
//public class MyAsyncTaskOfBitmap extends AsyncTask<String,Void,Bitmap>{
//
//    private String url ;
//    private BitmapCallBack callBack;
//
//    public MyAsyncTaskOfBitmap(BitmapCallBack callBack){
//
//        this.callBack =callBack;
//
//    }
//
//    @Override
//    protected Bitmap doInBackground(String... params) {
//
//        url = params[0];
//
//        return HttpURLUtils.getDataOfBitap(url);
//    }
//
//    @Override
//    protected void onPostExecute(Bitmap bitmap) {
//        super.onPostExecute(bitmap);
//
//        if(bitmap !=null){
//            callBack.getDataOfBitmap(url,bitmap);
//        }
//    }
//}
