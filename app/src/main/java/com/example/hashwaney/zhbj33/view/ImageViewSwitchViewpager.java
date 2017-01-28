package com.example.hashwaney.zhbj33.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.hashwaney.zhbj33.base.NewsCenterTabPager;

/**
 * Created by HashWaney on 2017/1/29.
 */

public class ImageViewSwitchViewpager
        extends ViewPager
{
    //由外部传入这个对象
    public void setTabPager(NewsCenterTabPager tabPager) {
        this.tabPager = tabPager;
    }

    //声明一个NewsCenterTabPager对象
    private NewsCenterTabPager tabPager;

    public ImageViewSwitchViewpager(Context context) {
        super(context);
    }

    public ImageViewSwitchViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下时停止轮播
                tabPager.stopSwitch();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                //弹开时开始轮播
                tabPager.startSwitch();
                break;
        } return super.dispatchTouchEvent(ev);
    }
}
