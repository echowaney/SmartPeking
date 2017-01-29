package com.example.hashwaney.zhbj33.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
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
    private int mMFootMeasuredHeight;

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

        mLlLoadmore= (LinearLayout) mFootView.findViewById(R.id.ll_loadmore);
        mLlLoadmore.measure(0,0);
        mMFootMeasuredHeight = mLlLoadmore.getMeasuredHeight();
        mLlLoadmore.setPadding(0,-mMFootMeasuredHeight,0,0);

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

}
