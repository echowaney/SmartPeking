package com.example.hashwaney.zhbj33.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by HashWaney on 2017/1/27.
 */

public class ThreadUtils {
    private static Executor mExecutor =Executors.newSingleThreadExecutor();
    private  static Handler mHandler =new Handler(Looper.getMainLooper());
    //子线程中进行
    public static void runOnSubThread(Runnable runnable){
        mExecutor.execute(runnable);
    }
    //主线程中进行
    public static void runOnMainThread(Runnable runnable ,long delay){
        mHandler.postDelayed(runnable,delay);
    }


}
