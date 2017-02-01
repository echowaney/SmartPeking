package com.example.hashwaney.zhbj33.utils.bitmap;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by HashWaney on 2017/2/1.
 * 这是从内存中获取图片的工具类
 * 键值对的形式存储图片
 *
 */

public class BitmapFromCache {

    static {

//        cacheMap = new HashMap<>();
        int maxSize = (int) Runtime.getRuntime().maxMemory();    //获取虚拟机的内存大小
        //指定内存缓存集合大小
        //            return super.sizeOf(key, value);
        lruCache = new LruCache<String,Bitmap>((maxSize/8)){    //指定内存缓存集合大小
           @Override
           protected int sizeOf(String key, Bitmap value) {
               //            return super.sizeOf(key, value);
               return value.getWidth() * value.getHeight();

           }
       };

    }

//    private static HashMap<String, SoftReference<Bitmap>> cacheMap;     //当图片数量变得多,内存也就不够了, 从2.3之后 gc 更加倾向于回收软应用弱引用,因此使用软引用是不可靠的



    private static LruCache<String, Bitmap> lruCache;



    //读缓存
    public static Bitmap readCache(String url) {

//        SoftReference<Bitmap> softReference = cacheMap.get(url);
//        if (softReference != null) {
//
//            return softReference.get();
//        }
//
//        //        return bitmap;



        return lruCache.get(url);


    }


    //写缓存
    public static void saveCache(String url, Bitmap bitmap) {
//        SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(bitmap);
//        cacheMap.put(url, softReference);
        lruCache.put(url,bitmap);

    }

}
