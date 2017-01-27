package com.example.hashwaney.zhbj33.acitivity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.adapter.GuideAdapter;
import com.example.hashwaney.zhbj33.base.BaseActivity;
import com.example.hashwaney.zhbj33.constant.Contant;
import com.example.hashwaney.zhbj33.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity
        extends BaseActivity
        implements ViewPager.OnPageChangeListener
{
    private static final String TAGG = "GuideActivity";
    @BindView(R.id.btn_start)
    Button       mBtnStart;
    @BindView(R.id.point_container)
    LinearLayout mPointContainer;
    @BindView(R.id.iv_red_point)
    View         mIvRedPoint;

    //准备资源视图
    private int[] Images = {R.drawable.guide_1,
                            R.drawable.guide_2,
                            R.drawable.guide_3

    };


    //viewpager的页面的集合
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
        initPoint();
    }

    //动态添加小圆点
    private void initPoint() {
        //        mPointLists =new ArrayList<>();
/*

        for (int i = 0; i < Images.length; i++) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.point_gray_shape);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            params.rightMargin = 10;
            mPointContainer.addView(view, params);
        }
*/
        for (int image : Images) {
            View view =new View(this);
            view.setBackgroundResource(R.drawable.point_gray_shape);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10,10);
            params.rightMargin = 20;//设置右边距
            mPointContainer.addView(view,params);
        }



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
    private int width;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //在页面滚动的时候,让红色小圆点随着页面一起滚动

        //如果是角标为0,那么就覆盖在第一个小灰点上
        if (width==0){
            //就可以拿到第一个孩子和第二个孩子的间距
            width = mPointContainer.getChildAt(1)
                                   .getLeft() - mPointContainer.getChildAt(0)
                                                               .getLeft();
            Log.d(TAGG, "onPageScrolled:  width"+width);

        }

        //计算出两个点之间的距离,是不是就可以得到偏移量
        //计算两点之间的距离


        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mIvRedPoint.getLayoutParams();
        params.leftMargin=(int) (width*positionOffset + width*position);
        mIvRedPoint.setLayoutParams(params);


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
    public void onClick() {
        //进入到主界面 ，并将状态进行一次保存
        SPUtils.saveBoolean(this, Contant.CONTANT_KEY_FISRT_TO_GUDIE,true);
        startActivity(MainActivity.class,true);
    }
}
