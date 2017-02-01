package com.example.hashwaney.zhbj33.utils.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.hashwaney.zhbj33.utils.Md5Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by HashWaney on 2017/2/1.
 * 这是进行磁盘缓存的工具类
 *  缓存到应用程序的cache目录（smartpeking）下
 *
 */

public class BitmapFromLocal {

    private final  static  String TAG ="BitmapFromLocal";
    //读缓存
    public static Bitmap readLocalCache(Context context, String url) {
        Bitmap bitmap = null;
        //在cache目录下创建一个smartpeking的文件夹
        File file = new File(context.getCacheDir(), "smartpeking");
        if (!file.exists()) {
            //文件都不存在了 ，返回一个空对象
            return null;
        }

        //在文件夹下创建一个文件
        File cacheFile = new File(file, Md5Utils.encode(url));
        if (!cacheFile.exists()) {
            //说明这个文件也不存在
            return null;

        }


        //解析这个文件了,返回一个bitmap对象
        bitmap = BitmapFactory.decodeFile(cacheFile.getAbsolutePath());
        //读取磁盘的图片的时候 保存图片到缓存中
        BitmapFromCache.saveCache(url,bitmap);

        return bitmap;


    }


    //写缓存
    public static void saveLocalFile(Context context, String url, Bitmap bitmap) {
        //在cache目录下创建一个smartpeking的文件夹
        File file = new File(context.getCacheDir(), "smartpeking");
        if (!file.exists()) {
          //文件夹不存在就去创建这个文件夹
            file.mkdir();
        }


        File             cacheFile    = new File(file, Md5Utils.encode(url));
        FileOutputStream outputStream = null;
        //将图片缓存到缓存目录中
        try {
            outputStream = new FileOutputStream(cacheFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //通过外界传来的图片bitmap 进行压缩存储到我们的缓存目录中来
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

    }


}
