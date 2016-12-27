package com.bane.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class ViewHolder {

    SparseArray<View> mViews;

    private View mConvertView;

    public ViewHolder(Context context, int layoutId, ViewGroup parent){

        this.mViews = new SparseArray<>();

        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);

        mConvertView.setTag(this);

    }

    public static ViewHolder getInstance(Context context,View convertView,int layoutId,ViewGroup parent) {
        ViewHolder mHolder;

        if(convertView ==null){
            mHolder = new ViewHolder(context,layoutId,parent);

        }else{

            mHolder = (ViewHolder) convertView.getTag();
        }

        return mHolder;
    }

    public <T extends View>T getDataOfView(int viewId){
        T view =  (T) mViews.get(viewId);

        if(view==null){

            view = (T) mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }

        return view;
    }

    public View getConvertView(){

        return mConvertView;
    }
}
