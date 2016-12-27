package com.bane.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class HttpURLUtils {

    public static String getDataOfString(String path){

        StringBuffer sb = null;
        try {
            URL url = new URL(path);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(500000);
            conn.connect();

            if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){

                InputStream is = conn.getInputStream();

                byte[] buf= new byte[1024*16];
                int len = 0 ;

                sb = new StringBuffer();

                while((len = is.read(buf))!= -1){

                    String info = new String(buf,0,len);
                    sb.append(info);
                }
            }

            Log.i("===", "getDataOfString: "+sb.toString());
            return sb.toString();

        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getDataOfByte(String path){

        ByteArrayOutputStream baos =  null;
        try {
            URL url =  new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(500000);
            conn.connect();

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){

                InputStream is = conn.getInputStream();

                byte[] buf = new byte[1024*16];

                int len =0 ;

                baos  = new ByteArrayOutputStream();

                while((len = is.read(buf))!=-1){

                    baos.write(buf,0,len);
                    baos.flush();
                }
            }
            return baos.toByteArray();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Bitmap getDataOfBitmap(String path){
        Bitmap bitmap =null;
        try {
            URL url =  new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(500000);
            conn.connect();

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){

                InputStream is = conn.getInputStream();

                bitmap= BitmapFactory.decodeStream(is);
            }

            return bitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
