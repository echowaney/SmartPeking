package com.example.hashwaney.zhbj33.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by HashWaney on 2017/1/27.
 */

public class SPUtils {

    //保存boolean状态值
    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit()
          .putBoolean(key, value)
          .commit();
    }
    //保存int状态值
    public static void saveInt(Context context,String key,int value){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();

    }
    //存String
    public static  void  saveString(Context context,String key,String value){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }


    //取出boolean状态值
    public static boolean getBoolean(Context context, String key, boolean defaultVal) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultVal);
    }
    //取出int状态值
    public static int getInt(Context context,String key,int defaultVal){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt(key,defaultVal);
    }

    //取出String
    public static String getString(Context context,String key, String deval){
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(key,deval);

    }
}
