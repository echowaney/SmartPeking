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
    @BindView(R.id.ll_refresh)
    LinearLayout mLlRefresh;
    //    @BindView(R.id.progress_load_more)
    ProgressBar  mProgressLoadMore;
    //    @BindView(R.id.ll_loadmore)
    LinearLayout mLlLoadmore;
    private ViewGroup mHeadView;
    private View      mFootView;
    private int       mMHeadMeasuredHeight;
    private int       mMFootMeasuredHeight;


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
                Log.e(TAG, "dispatchToucEvent:  按下" );
                startX = ev.getX();
                startY=ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent:  移动" );
                float moveX =ev.getX();

                float moveY =ev.getY();
                //条件 是recycleview的第一个条目的角标为0  并且 向下移动
                float disY = moveY -startY;
                int dis = (int) (-mMHeadMeasuredHeight +disY);
                int firstVisibleItemPosition = lm.findFirstVisibleItemPosition();
                if (firstVisibleItemPosition==0  && disY>0){

                    mLlRefresh.setPadding(0,dis,0,0);

                }

                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent:  弹起" );
                break;
        }


        return super.dispatchTouchEvent(ev);
    }
}
