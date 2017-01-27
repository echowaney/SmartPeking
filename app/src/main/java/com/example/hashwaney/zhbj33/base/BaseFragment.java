package com.example.hashwaney.zhbj33.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by HashWaney on 2017/1/27.
 */

public abstract class BaseFragment
        extends Fragment
{
    //加载布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        TextView textView =new TextView(container.getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(20);
        return textView;
    }

    //布局加载完成 ---有视图的时候进行方法的调用
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContent();
    }
    //将不同的交由子类去实现
    public abstract void setContent();
}
