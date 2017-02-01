package com.example.hashwaney.zhbj33.utils.bitmap;

import android.graphics.Bitmap;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by HashWaney on 2017/2/1.
 * 这是从内存中获取图片的工具类
 * 键值对的形式存储图片
 *
 */

public class BitmapFromCache {

    static {

        cacheMap = new HashMap<>();

    }

    private static HashMap<String, SoftReference<Bitmap>> cacheMap;
    //    private static HashMap<String, Bitmap> cacheMap;

    //读缓存
    public static Bitmap readCache(String url) {

        SoftReference<Bitmap> softReference = cacheMap.get(url);
        if (softReference != null) {

            return softReference.get();
        }

        //        return bitmap;
        return null;


    }


    //写缓存
    public static void saveCache(String url, Bitmap bitmap) {
        SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(bitmap);
        cacheMap.put(url, softReference);


    }

}
