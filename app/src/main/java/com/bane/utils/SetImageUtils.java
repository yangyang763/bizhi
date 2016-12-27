package com.bane.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.android32_asynctask_json.R;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class SetImageUtils {

    public static MyBitmapThreebleSave mbts = MyBitmapThreebleSave.getInstance();

    public static  void setImage(ImageView iv, String url,boolean isRound){

        iv.setImageResource(R.mipmap.ic_launcher);

        Bitmap bit1 = mbts.getBitmap(url);

        if(bit1 == null){
            mbts.loadBitmap(url,iv,isRound);
        }else{
            iv.setTag(url);
            if(isRound){
                iv.setImageBitmap(BitmapRound.getRoundBitmap(bit1));
            }else{
                iv.setImageBitmap(bit1);
            }
        }
    }

    public static  void setImageText(TextView tv, String url,boolean isRound) {

        Bitmap bit1 = mbts.getBitmap(url);

        if(bit1 == null){
            mbts.loadBitmapOfTextView(url,tv,isRound);

        }else {
            tv.setTag(url);
            if(isRound){
                tv.setBackground(new BitmapDrawable(BitmapRound.getRoundBitmap(bit1)));
            }else{
                tv.setBackground(new BitmapDrawable(bit1));
            }
        }
    }
}
