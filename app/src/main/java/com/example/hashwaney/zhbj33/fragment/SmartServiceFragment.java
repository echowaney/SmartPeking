package com.example.hashwaney.zhbj33.fragment;

import android.widget.TextView;

import com.example.hashwaney.zhbj33.base.BaseFragment;

/**
 * Created by HashWaney on 2017/1/27.
 */

public class SmartServiceFragment
        extends BaseFragment
{
    @Override
    public void setContent() {
        TextView textView = (TextView) getView();
        textView.setText("智慧服务");
    }
}
