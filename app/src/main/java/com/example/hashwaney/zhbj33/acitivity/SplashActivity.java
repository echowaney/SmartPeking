package com.example.hashwaney.zhbj33.acitivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.base.BaseActivity;
import com.example.hashwaney.zhbj33.constant.Contant;
import com.example.hashwaney.zhbj33.utils.SPUtils;
import com.example.hashwaney.zhbj33.utils.ThreadUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity
        extends BaseActivity
        implements Animation.AnimationListener
{

    @BindView(R.id.rl)
    RelativeLayout mRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        Animation animation = creatAnimation();
        mRl.startAnimation(animation);
        //动画结束后延时两秒进入到主界面
        animation.setAnimationListener(this);

    }


    //创建一个动画集合
    private Animation creatAnimation() {
        AnimationSet set = new AnimationSet(true);
        //旋转
        RotateAnimation rotateAnimation = new RotateAnimation(0,
                                                              360,
                                                              Animation.RELATIVE_TO_SELF,
                                                              0.5f,
                                                              Animation.RELATIVE_TO_SELF,
                                                              0.5f);
        rotateAnimation.setDuration(2000);
        //缩放
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,
                                                           1,
                                                           0,
                                                           1,
                                                           Animation.RELATIVE_TO_SELF,
                                                           0.5f,
                                                           Animation.RELATIVE_TO_SELF,
                                                           0.5f);
        scaleAnimation.setDuration(2000);
        //透明度 --有不可见到可见 0 代表是透明 1 代表是不透明
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1000);



        //添加动画到集合
        set.addAnimation(rotateAnimation);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);

        return set;

    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
            //主线程不能进行耗时操作
        //动画结束后.判断是否是第一次进入到向导界面
        boolean hasGuide = SPUtils.getBoolean(this, Contant.CONTANT_KEY_FISRT_TO_GUDIE, false);
        if (!hasGuide){
            //handler
            ThreadUtils.runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    //进入到向导界面
                    startActivity(GuideActivity.class,true);

                }
            },2000);

        }else{
            //进入主界面
            startActivity(MainActivity.class,true);

        }



    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
