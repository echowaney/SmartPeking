package com.example.hashwaney.zhbj33.base;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by HashWaney on 2017/1/27.
 */

public class BaseActivity
        extends Activity
{

    //跳转界面
    public void startActivity(Class clazz,boolean isFinish){

        Intent intent =new Intent(this,clazz);
        startActivity(intent);
        if (isFinish){
            finish();
        }

    }


}
