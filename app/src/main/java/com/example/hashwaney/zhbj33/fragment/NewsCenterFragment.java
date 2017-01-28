package com.example.hashwaney.zhbj33.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.acitivity.MainActivity;
import com.example.hashwaney.zhbj33.adapter.NewsContentAdapter;
import com.example.hashwaney.zhbj33.base.BaseFragment;
import com.example.hashwaney.zhbj33.base.OnLoadDataOperator;
import com.example.hashwaney.zhbj33.bean.NewsCenterBean;
import com.example.hashwaney.zhbj33.constant.Contant;
import com.example.hashwaney.zhbj33.utils.ToastUtils;
import com.google.gson.Gson;
import com.viewpagerindicator.TabPageIndicator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by HashWaney on 2017/1/27.
 */

public class NewsCenterFragment
        extends BaseFragment
        implements OnLoadDataOperator
{

    private static final String TA = "NewsCenterFragment";
    private ViewPager                        mViewpagerNewContent;
    private ImageButton                      mIbNext;
    private TabPageIndicator                 mTabPageIndicator;
    private List<NewsCenterBean.NewsTabBean> mNewsTabBeanList =new ArrayList<>();
    private List<NewsCenterBean.NewsMenuBean> mNewsMenuBeen;
    private List<View>  mViewList;

    @Override
    public void initTitle() {
        setIbMenu(true);
        setIbPic(false);
        setTitle("新闻");
    }

    @Override
    public View initContent() {
        //        TextView tv =new TextView(getContext());
        //        tv.setText("我是新闻中心界面");
        //        return tv;
        View view = LayoutInflater.from(getContext())
                                  .inflate(R.layout.news_tab_content, (ViewGroup) getView(), false);
        mViewpagerNewContent = (ViewPager) view.findViewById(R.id.viewpager);
        mIbNext = (ImageButton) view.findViewById(R.id.iv_next);
        mTabPageIndicator = (TabPageIndicator) view.findViewById(R.id.tab_pageindicator);
        initViewpager();
        return view;

    }

    private void initViewpager() {
        List<NewsCenterBean.NewsTabBean> children = mNewsMenuBeen.get(0).children;
        //创建一个集合
        mViewList =new ArrayList<>();
        for (NewsCenterBean.NewsTabBean newsTabBean : children) {
            TextView tv =new TextView(getContext());
            tv.setText(newsTabBean.title);
            tv.setTextColor(Color.RED);
            tv.setTextSize(20);
            tv.setGravity(Gravity.CENTER);
            mViewList.add(tv);
        }
        mNewsTabBeanList.clear();
        mNewsTabBeanList.addAll(children);

        //        viewpager去设置adapter
        NewsContentAdapter adapter = new NewsContentAdapter(mViewList,mNewsTabBeanList);

        mViewpagerNewContent.setAdapter(adapter);
        mTabPageIndicator.setViewPager(mViewpagerNewContent);

    }

    @Override
    public void onLoadNetData() {
        //此处进行数据的加载 加载json数据 回调就采用StringCallBack
        String url = Contant.NEWCENTER_REQUEST_URL;
        OkHttpUtils.get()
                   .url(url)
                   .build()
                   .execute(new StringCallback() {
                       @Override
                       public void onError(Call call, Exception e, int id) {
                           //请求失败
                           Log.d(TA, "onError:  e " + e.getMessage());
                           ToastUtils.showToast(getActivity(), e.getMessage());
                       }

                       @Override
                       public void onResponse(String response, int id) {
                           //请求成功
                           processData(response);

                       }
                   });

    }

    //解析从网络上请求回来的数据
    private void processData(String response) {
        Gson           gson           = new Gson();
        NewsCenterBean newsCenterBean = gson.fromJson(response, NewsCenterBean.class);
        //将数据传递给menu
        //menu是属于MainActivity的-----
        //创建一个方法用来接收Fragment传递过来的数据
        mNewsMenuBeen = newsCenterBean.data;
        ((MainActivity) getActivity()).setNewsMenuBeenLists(mNewsMenuBeen);


        //创建布局
        View view = initContent();
        //加载布局
        addView(view);


    }
}
