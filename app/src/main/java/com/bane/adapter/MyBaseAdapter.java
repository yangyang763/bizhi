package com.bane.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bane.myinterface.AdapterSetData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class MyBaseAdapter<T> extends BaseAdapter{

    private Context context;

    private List<T> list = new ArrayList<>();
    private int layoutId;

    private AdapterSetData interfaceAdapter;

    public MyBaseAdapter(Context context,List<T> list,int layoutId,AdapterSetData interfaceAdapter){

        this.context = context;
        this.list=list;
        this.layoutId = layoutId;
        this.interfaceAdapter = interfaceAdapter;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mHolder =ViewHolder.getInstance(context,convertView,layoutId,parent);

        if(interfaceAdapter!=null){

            interfaceAdapter.setData(mHolder,list.get(position));
        }

        return mHolder.getConvertView();
    }
}
