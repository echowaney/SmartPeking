package com.example.hashwaney.zhbj33.utils;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by HashWaney on 2017/1/30.
 * 缓存的工具类:缓存 文件名,缓存从服务器接收的json字符串, 以及读取缓存
 */

public class CashUtils {

    public static void saveCash(Context context,String url,String json) throws Exception{
        //文件名
        String savePath = Md5Utils.encode(url);

        //文件路径
        FileOutputStream     outputStream = context.openFileOutput(savePath, Context.MODE_PRIVATE);

       //将json写入
        outputStream.write(json.getBytes());
         //关流
        outputStream.close();


    }
    public static String readCash(Context context,String url) throws Exception{
        String filePath = Md5Utils.encode(url);
        FileInputStream inputStream = context.openFileInput(filePath);
        byte[] buffer =new byte[1024];
        int len;
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        while((len =inputStream.read(buffer))!=-1)
        {
            bos.write(buffer,0,len);

        }
        bos.close();
        inputStream.close();

        return bos.toString();

    }


}
