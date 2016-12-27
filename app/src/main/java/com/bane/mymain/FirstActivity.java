package com.bane.mymain;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.bane.fragment.FourFragment;
import com.bane.fragment.OneFragment;
import com.bane.fragment.ThreeFragment;
import com.bane.fragment.TwoFragment;
import com.example.administrator.android32_asynctask_json.R;


import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends FragmentActivity {

    private RelativeLayout mRelativeFragment;
    private RadioGroup mRadiogroup;
    private List<Fragment> list = new ArrayList<>();

    private FragmentManager manager;
    private int lastIndex=0;

    private void assignViews() {
        mRelativeFragment = (RelativeLayout) findViewById(R.id.relative_fragment);
        mRadiogroup = (RadioGroup) findViewById(R.id.radiogroup);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        assignViews();

        initData();

        getSupportFragmentManager().beginTransaction().add(R.id.relative_fragment,list.get(0)).commit();
    }

    private void initData() {

        list.add(new OneFragment());
        list.add(new TwoFragment());
        list.add(new ThreeFragment());
        list.add(new FourFragment());

    }


    public void onclick(View view) {


        int index = Integer.parseInt(view.getTag().toString());
        Log.i("==", "onclick: "+index);
        getSupportFragmentManager().beginTransaction().hide(list.get(lastIndex)).commit();
        if(!list.get(index).isAdded()){
            getSupportFragmentManager().beginTransaction().add(R.id.relative_fragment,list.get(index)).commit();

        }else{

            getSupportFragmentManager().beginTransaction().show(list.get(index)).commit();

        }
        lastIndex = index;
//        getSupportFragmentManager().beginTransaction()
    }

}
