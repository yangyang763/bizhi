package com.bane.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.TextView;

import com.bane.myinterface.BitmapCallBack;
import com.example.administrator.android32_asynctask_json.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class MyBitmapThreebleSave {

    private static final String TAG = "===";
    private static MyBitmapThreebleSave mbts;
    private LruCache<String,Bitmap> lruCache ;
    private HashMap<String,SoftReference<Bitmap>> map = new HashMap<>();

    private String path_save = Environment.getExternalStorageDirectory().getAbsolutePath()+"/zcr_jpbz";
    private  MyBitmapThreebleSave(){
        initLruCache();
    }

    private void initLruCache() {
        int maxSize  = (int )Runtime.getRuntime().maxMemory()/8;

        lruCache = new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);

                if(evicted){
                    map.put(key,new SoftReference<Bitmap>(oldValue));

                    Log.i(TAG, "entryRemoved: 往SoftReference中存储数据");
                }
            }
        };
    }

    public static MyBitmapThreebleSave getInstance() {

        if(mbts==null){
            synchronized (MyBitmapThreebleSave.class) {
                if(mbts==null){
                    mbts = new MyBitmapThreebleSave();
                }
            }
        }
        return mbts;
    }

    public void loadBitmap(String url, final ImageView imageview, final boolean isRound){

        imageview.setBackgroundResource(R.mipmap.ic_launcher);

        imageview.setTag(url);

        new MyAsyncTaskOfBitmap(new BitmapCallBack() {
            @Override
            public void getDataOfBitmap(String url, Bitmap bitmap) {
                if(imageview.getTag()!=null&&imageview.getTag().toString().equals(url)){
                    if(isRound){
                        imageview.setImageBitmap(BitmapRound.getRoundBitmap(bitmap));
                    }else{
                        imageview.setImageBitmap(bitmap);
                    }

                }
                saveBitmap(url,bitmap);
            }
        }).execute(url);

    }

    public void loadBitmapOfTextView(String url, final TextView textView,final boolean isRound){

        textView.setBackgroundResource(R.mipmap.ic_launcher);

        textView.setTag(url);

        new MyAsyncTaskOfBitmap(new BitmapCallBack() {
            @Override
            public void getDataOfBitmap(String url, Bitmap bitmap) {
                if(textView.getTag()!=null&&textView.getTag().toString().equals(url)){

                    if(isRound){
                        textView.setBackground(new BitmapDrawable(BitmapRound.getRoundBitmap(bitmap)));
                    }else {
                        textView.setBackground(new BitmapDrawable(bitmap));
                    }
                }
                saveBitmap(url,bitmap);
            }
        }).execute(url);

    }

    public void saveBitmap(String url, Bitmap bitmap){

        //首先是存入到强引用lrucache中，lrucache本身可以存储多组键值对
        lruCache.put(url,bitmap);

        Log.i(TAG, "saveBitmap: 往lruCache中存储数据！！！！");

        //由于网址对应的 / 会造成多级文件夹所以替换为 _
        String urlName = url.replace("/","_");

        //创建文件夹
        File file = new File(path_save);

        if(!file.exists()){
            file.mkdirs();
        }

        try {
            FileOutputStream fos = new FileOutputStream(file+"/"+urlName);

            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            Log.i(TAG, "saveBitmap: 往内存卡中存储数据！！！！");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getBitmap(String url){

        Bitmap result =  lruCache.get(url);

        if(result ==null){
            SoftReference<Bitmap> soft = map.get(url);
            if(soft !=null){
                result = soft.get();
                Log.i(TAG, "getBitmap：SoftReference中取出数据: ");
            }else {
                result = BitmapFactory.decodeFile(path_save+"/"+url.replace("/","_"));
                if(result!=null) {
                    Log.i(TAG, "getBitmap：从内存卡中取出数据");
                }
            }
        }else{
            Log.i(TAG, "getBitmap：lrucache中取出数据");
        }
        return result;
    }
}
