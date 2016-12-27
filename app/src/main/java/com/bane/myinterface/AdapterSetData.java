package com.bane.myinterface;

import com.bane.adapter.ViewHolder;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public interface AdapterSetData<T> {

    public void setData(ViewHolder mHolder,T t);
}
