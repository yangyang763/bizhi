package com.bane.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.bane.myinterface.MyInterface;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class MyAsyncTaskUtils<T> extends AsyncTask<String,Void,T>{

    private MyInterface myInterface;
    private Class<T> clazz;


    public MyAsyncTaskUtils(MyInterface myInterface, Class<T> clazz){
        this.myInterface = myInterface;
        this.clazz = clazz;

    }

    @Override
    protected T doInBackground(String... params) {

        String json = HttpURLUtils.getDataOfString(params[0]);

        Log.i("===", "doInBackground: "+json);

        T bean  = new Gson().fromJson(json,clazz);

        return bean;
    }

    @Override
    protected void onPostExecute(T t) {
        super.onPostExecute(t);

        if(t!=null&&myInterface!=null){
            myInterface.getData(t);
        }
    }
}
