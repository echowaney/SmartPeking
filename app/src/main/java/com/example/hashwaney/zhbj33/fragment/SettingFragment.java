package com.example.hashwaney.zhbj33.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.hashwaney.zhbj33.base.BaseFragment;


/**
 * Created by HashWaney on 2017/1/27.
 */

public class SettingFragment
        extends BaseFragment
{


    @Override
    public void initTitle() {
        setIbPic(false);
        setTitle("设置");
        setIbMenu(false);
    }

    @Override
    public View initContent() {
        TextView tv =new TextView(getContext());
        tv.setText("我是设置界面");
        return tv;
    }
}
