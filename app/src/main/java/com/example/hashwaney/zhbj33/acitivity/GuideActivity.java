package com.example.hashwaney.zhbj33.acitivity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.adapter.GuideAdapter;
import com.example.hashwaney.zhbj33.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity
        extends BaseActivity
        implements ViewPager.OnPageChangeListener
{
    @BindView(R.id.btn_start)
    Button mBtnStart;
    //准备资源视图
    private int[] Images = {R.drawable.guide_1,
                            R.drawable.guide_2,
                            R.drawable.guide_3

    };
    private List<ImageView> mImageViewList;

    @BindView(R.id.gudieViepager)
    ViewPager mGudieViepager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_guide);

        ButterKnife.bind(this);

        initViewPager();
    }

    //初始化viewpager
    private void initViewPager() {
        mImageViewList = new ArrayList<>();
        for (int image : Images) {
            ImageView img = new ImageView(this);
            img.setImageResource(image);
            img.setScaleType(ImageView.ScaleType.FIT_XY);//图片铺满整个屏幕
            mImageViewList.add(img);
        }

        GuideAdapter guideAdapter = new GuideAdapter(mImageViewList);
        mGudieViepager.setAdapter(guideAdapter);
        mGudieViepager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == Images.length - 1) {
            //显示
            mBtnStart.setVisibility(View.VISIBLE);
        } else {
            //隐藏
            mBtnStart.setVisibility(View.GONE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.btn_start)
    public void onClick() {}
}
