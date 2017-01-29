package com.example.hashwaney.zhbj33.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.adapter.XWrapRecycleViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HashWaney on 2017/1/29.
 */

public class CostumeRecycleview
        extends RecyclerView
{

    private static final String TAG = "CostumeRecycleview";
    @BindView(R.id.pb_refresh)
    ProgressBar  mPbRefresh;
    @BindView(R.id.tv_refresh)
    TextView     mTvRefresh;
    @BindView(R.id.tv_time)
    TextView     mTvTime;
    @BindView(R.id.iv_arrow)
    ImageView    mIvArrow;
    @BindView(R.id.ll_refresh)
    LinearLayout mLlRefresh;

    //    @BindView(R.id.ll_loadmore)
    LinearLayout mLlLoadmore;

    private ViewGroup mHeadView;
    private View      mFootView;
    private int       mMHeadMeasuredHeight;
    private int       mMFootMeasuredHeight;
    private              int HeadState             = DOWN_REFRESH_STATE;
    private final static int DOWN_REFRESH_STATE    = 0;
    private final static int RELEASE_REFRESH_STATE = 1;
    private final static int REFRESHING_STATE      = 2;
    private Animation mDownAnimation;
    private Animation mUpAnimation;
    private float mDisY;


    public CostumeRecycleview(Context context) {
        this(context, null);
    }

    public CostumeRecycleview(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public CostumeRecycleview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        this(context, attrs);

    }

    private void init() {
        //初始化头布局
        initHeadView();
        //初始化脚布局
        initFootView();
        //创建动画
        initAnimation();


    }

    private void initAnimation() {
        mDownAnimation = creatDownAnimation();
        mUpAnimation = creatUpAnimation();


    }

    private Animation creatDownAnimation() {
        // -180 到 -360
        RotateAnimation rotateAnimation = new RotateAnimation(-180,
                                                              -360,
                                                              Animation.RELATIVE_TO_SELF,
                                                              0.5f,
                                                              Animation.RELATIVE_TO_SELF,
                                                              0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillAfter(true);


        return rotateAnimation;
    }

    private Animation creatUpAnimation() {
        //0 - -180
        RotateAnimation rotateAnimation = new RotateAnimation(0,
                                                              -180,
                                                              Animation.RELATIVE_TO_SELF,
                                                              0.5f,
                                                              Animation.RELATIVE_TO_SELF,
                                                              0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillAfter(true); //维持状态不变

        return rotateAnimation;
    }

    private void initFootView() {
        mFootView = inflate(getContext(), R.layout.foot_view, null);
        //ButterKnife.bind(this,mFootView);

        mLlLoadmore = (LinearLayout) mFootView.findViewById(R.id.ll_loadmore);
        mLlLoadmore.measure(0, 0);
        mMFootMeasuredHeight = mLlLoadmore.getMeasuredHeight();
        mLlLoadmore.setPadding(0, -mMFootMeasuredHeight, 0, 0);

    }

    private void initHeadView() {
        mHeadView = (ViewGroup) inflate(getContext(), R.layout.head_view, null);
        ButterKnife.bind(this, mHeadView);
        //隐藏进度条
        mPbRefresh.setVisibility(INVISIBLE);
        //隐藏头布局
        mLlRefresh.measure(0, 0);
        mMHeadMeasuredHeight = mLlRefresh.getMeasuredHeight();
        mLlRefresh.setPadding(0, -mMHeadMeasuredHeight, 0, 0);

    }


    //重写setAdapter 由父类进行重写,方能生效----自定义的并没有进行任何操作,因此父亲重写


    @Override
    public void setAdapter(Adapter adapter) {
        adapter = new XWrapRecycleViewAdapter(mHeadView, mFootView, adapter);
        super.setAdapter(adapter);
    }

    public void addSwitchImage(View view) {
        mHeadView.addView(view);

    }
    //处理事件滑出头布局
    //这里采用是dispathontouchevent ,因为dispathtouch 使用的频率更高

    private LinearLayoutManager lm;

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        lm = (LinearLayoutManager) layout;

    }

    private float startX;
    private float startY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchToucEvent:  按下");
                startX = ev.getX();
                startY = ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent:  移动");
                float moveX = ev.getX();

                float moveY = ev.getY();
                //条件 是recycleview的第一个条目的角标为0  并且 向下移动
                mDisY = moveY - startY;
                int dis = (int) (-mMHeadMeasuredHeight + mDisY);
                int firstVisibleItemPosition = lm.findFirstVisibleItemPosition();
                if (firstVisibleItemPosition == 0 && mDisY > 0) {
                    //当前状态是下拉刷新 并且 dis ==0 了, 就切换到释放刷新
                    if ( HeadState == DOWN_REFRESH_STATE    && dis == 0) {
                        HeadState = RELEASE_REFRESH_STATE;
                        //修改文字
                        mTvRefresh.setText("释放刷新");
                        //动画 0 - -180 旋转动画
                        mIvArrow.startAnimation(mUpAnimation);


                    } else if (HeadState == RELEASE_REFRESH_STATE && dis < 0) {
                        //当前状态是释放刷新 并且dis<0 就切换到下拉刷新
                        HeadState = DOWN_REFRESH_STATE;
                        mTvRefresh.setText("下拉刷新");
                        mIvArrow.startAnimation(mDownAnimation);
                    }

                    mLlRefresh.setPadding(0, dis, 0, 0);

                }

                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent:  弹起");
                //处理条件 : 条目角标同样也是为0 ,并且y方向的距离大于0 有向下移动
                int visibleItemPosition = lm.findFirstVisibleItemPosition();
                if (visibleItemPosition==0 && mDisY >0){
                    if (HeadState == DOWN_REFRESH_STATE){
                        //直接让他不显示
                        mLlRefresh.setPadding(0,-mMHeadMeasuredHeight,0,0);
                    }else if (HeadState ==RELEASE_REFRESH_STATE){
                        //更改状态
                        HeadState = REFRESHING_STATE;
                        //隐藏箭头 ,清除动画
                        mIvArrow.clearAnimation();
                        mIvArrow.setVisibility(INVISIBLE);
                        //显示进度条
                        mPbRefresh.setVisibility(VISIBLE);
                        //更改文字
                        mTvRefresh.setText("加载更多数据...");
                        //停留在
                        mLlRefresh.setPadding(0,0,0,0);
                        //加载数据 TODO
                    }
                }

                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
