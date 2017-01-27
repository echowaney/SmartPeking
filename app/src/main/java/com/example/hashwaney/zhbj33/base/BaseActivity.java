package com.example.hashwaney.zhbj33.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by HashWaney on 2017/1/27.
 */

public class BaseActivity
        extends AppCompatActivity
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
