package com.example.hashwaney.zhbj33.utils.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by HashWaney on 2017/1/31.
 *
 * 这是加载图片的工具类
 */

public class BitmapUtils {
    private final static String TAG="BitmapUtils";
    static {

        bitmapFromNet = new BitmapFromNet();
        bitmapFromLocal = new BitmapFromLocal();
        bitmapFormCache = new BitmapFromCache();
    }

    private static BitmapFromNet bitmapFromNet;
    private static BitmapFromLocal bitmapFromLocal;
    private static BitmapFromCache bitmapFormCache;


    public static void loadBitmap(Context context,ImageView iv, String url){

        Bitmap bitmap=null;
        //从缓存中加载
        bitmap =bitmapFormCache.readCache(url);
        if (bitmap !=null){
            iv.setImageBitmap(bitmap);
            Log.e(TAG, "loadBitmap: 从内存中加载图片" );
            return;
        }

        //从本地中加载
         bitmap = bitmapFromLocal.readLocalCache(context, url);
        if (bitmap !=null){
            iv.setImageBitmap(bitmap);
            Log.e(TAG, "loadBitmap: 从磁盘加载图片" );
            return ;
        }

        //从网络中加载
        bitmapFromNet.loadBitmapFormNet(context,iv,url);


    }

}
