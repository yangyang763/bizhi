package com.bane.clickfragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bane.myinterface.BitmapCallBack;
import com.bane.utils.MyAsyncTaskOfBitmap;
import com.example.administrator.android32_asynctask_json.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClickOneFragment extends Fragment {

    private static final String TAG = "---===";

    private ImageView imageView;
    private Bitmap bit;

    public ClickOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_click_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = (ImageView) view.findViewById(R.id.fragment_click_one_iamgeview_1);

        Bundle b =getArguments();

        String url = b.getString("url");


        Log.i(TAG, "onViewCreated: ---------"+url);

        new MyAsyncTaskOfBitmap(new BitmapCallBack() {
            @Override
            public void getDataOfBitmap(String url, Bitmap bitmap) {
                bit = bitmap;
            }
        }).execute(url);

        if(bit!=null){
            imageView.setImageBitmap(bit);
        }else {
            imageView.setImageResource(R.mipmap.ic_launcher);

        }
    }
}
