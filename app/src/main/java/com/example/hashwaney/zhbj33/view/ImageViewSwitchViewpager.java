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

    //拦事件的处理,向右侧滑动,会将slidingmenu滑出

    float startX;
    float startY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下时停止轮播
                startX = ev.getX();
                startY =ev.getY();
                tabPager.stopSwitch();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX =ev.getX();
                float moveY =ev.getY();
                 float disX = moveX-startX;
                float disY =moveY -startY;
                //如果X方向距离大于y方向距离,并且是向右侧滑动,就请求父容器不拦截事件
                if (Math.abs(disX)>Math.abs(disY) && moveX>startX){
                    requestDisallowInterceptTouchEvent(true);
                }

                break;
            case MotionEvent.ACTION_UP:
                //弹开时开始轮播
                tabPager.startSwitch();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
