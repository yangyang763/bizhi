package com.bane.utils;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class ColorRandom {

    private static Random random =  new Random();

    public static int getRandomColor(){

        int colotRandom = Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256));

        return colotRandom;
    }
}
