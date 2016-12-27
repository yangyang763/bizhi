package com.bane.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

import com.bane.url.DataStatic;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class BitmapRound {


    public static Bitmap getRoundBitmap(Bitmap source) {
        //角度
        int an = DataStatic.ROUND_ANGEL;
        //准备一张空白的Bitmap作为画布
        //准备空白的Bitmap
        Bitmap bitm = Bitmap.createBitmap(source.getWidth(),source.getHeight(), Bitmap.Config.ARGB_8888);
        //将bitm作为画布
        Canvas canvas = new Canvas(bitm);

        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setAntiAlias(true);  //抗锯齿效果

        /**
         * RectF为基准直角矩形
         * 1,2 直角矩形的左上角在画布范围的的坐标
         * 3,4，直角矩形的右下角在画布范围内的坐标
         */
        RectF rect = new RectF(0,0,source.getWidth(),source.getHeight());
        //绘制圆角矩形
        /**
         * 1.作为基准的直角矩形
         * 2.与x轴的角度
         * 3. 与y轴的角度
         * 4.画笔对象
         */
        canvas.drawRoundRect(rect,an,an,paint);

        //设置相交保留原则
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //绘制直角图片
        /**
         * 1.要绘制的图片
         * 2,3 图片的左上角在画布区域的坐标
         */
        canvas.drawBitmap(source,0,0,paint);

        return bitm;
    }
}
