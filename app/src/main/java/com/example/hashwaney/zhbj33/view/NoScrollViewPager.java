package com.example.hashwaney.zhbj33.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by HashWaney on 2017/1/27.
 * 该控件是用来viewpager的滑动事件, 默认viewpager是可以滑动,当出现tab页面也有可以滑动的时候,那么就要禁用viewpager的滑动事件了
 *
 * onTouchEvent " 不处理事件
 * onInteceptTouchEvent : 不拦截事件,
 */

public class NoScrollViewPager
        extends ViewPager
{
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;//不拦截事件
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;//不处理事件
    }
}
