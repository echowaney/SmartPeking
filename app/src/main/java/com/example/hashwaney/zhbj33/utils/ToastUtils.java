package com.example.hashwaney.zhbj33.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by HashWaney on 2017/1/27.
 */

public class ToastUtils {
    private static Toast mToast;
    public static void showToast(Context context,String msg){
        //Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        if (mToast==null){
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        mToast.show();

    }


}
