package com.example.hashwaney.zhbj33;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity
        extends Activity
{

    @BindView(R.id.rl)
    RelativeLayout mRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Animation animation = creatAnimation();
        mRl.startAnimation(animation);

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
}
