package com.example.hashwaney.zhbj33.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.acitivity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HashWaney on 2017/1/27.
 */

public abstract class BaseFragment
        extends Fragment
{
    @BindView(R.id.ib_menu)
    ImageButton mIbMenu;
    @BindView(R.id.tv_title)
    TextView    mTvTitle;
    @BindView(R.id.ib_pic)
    public ImageButton mIbPic;
    @BindView(R.id.fl_container)
    public FrameLayout mFlContainer;
    //定义一个标志位
    public boolean     hasLoadData;

    //加载布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_tab_base, container, false);


        ButterKnife.bind(this, view);
        return view;


    }

    //设置标题 子类必须实现
    public abstract void initTitle();


    //初始化内容
    public abstract View initContent();

    //添加内容
    public void addView(View view) {

        //清空容器
        mFlContainer.removeAllViews();
        //将内容添加到容器中
        mFlContainer.addView(view);
    }


    //设置标题
    public void setTitle(String title) {
        mTvTitle.setText(title);

    }

    //设置菜单按钮
    public void setIbMenu(boolean isShow)
    {
        mIbMenu.setVisibility(isShow
                              ? View.VISIBLE
                              : View.GONE);

    }

    //设置组图按钮
    public void setIbPic(boolean isShow) {

        mIbPic.setVisibility(isShow
                             ? View.VISIBLE
                             : View.GONE);
    }


    //布局加载完成 ---有视图的时候进行方法的调用
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setContent();
        initTitle();


    }

    //将不同的交由子类去实现
    //    public abstract void setContent();


    @OnClick(R.id.ib_menu)
    public void onClick() {
        //控制slidingmenu的切换
        //Slidingmenu ----MainActivity
        ((MainActivity) getActivity()).mSlidingMenu.toggle();


    }
}
