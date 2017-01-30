package com.example.hashwaney.zhbj33.fragment;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.acitivity.MainActivity;
import com.example.hashwaney.zhbj33.adapter.NewsContentAdapter;
import com.example.hashwaney.zhbj33.base.BaseFragment;
import com.example.hashwaney.zhbj33.base.NewsCenterTabPager;
import com.example.hashwaney.zhbj33.base.OnLoadDataOperator;
import com.example.hashwaney.zhbj33.bean.NewsCenterBean;
import com.example.hashwaney.zhbj33.constant.Contant;
import com.example.hashwaney.zhbj33.utils.ToastUtils;
import com.google.gson.Gson;
import com.viewpagerindicator.TabPageIndicator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by HashWaney on 2017/1/27.
 */

public class NewsCenterFragment
        extends BaseFragment
        implements OnLoadDataOperator, ViewPager.OnPageChangeListener
{

    private static final String TA = "NewsCenterFragment";
    private ViewPager mViewpagerNewContent;
    private ImageButton              mIbNext;
    private TabPageIndicator         mTabPageIndicator;
    private List<NewsCenterBean.NewsTabBean> mNewsTabBeanList =new ArrayList<>();
    private List<NewsCenterBean.NewsMenuBean> mNewsMenuBeen;
    private List<NewsCenterTabPager>  mViewList;
    private List<NewsCenterBean.NewsTabBean> mNewsTabBeen;
    private NewsCenterTabPager mNewsCenterTabPager;

    private Map<Integer,View> cashViewMap =new HashMap<>();
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
        //给imagebutton设置点击监听事件
        mIbNext = (ImageButton) view.findViewById(R.id.iv_next);
            mIbNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentItem = mViewpagerNewContent.getCurrentItem();
                    if (currentItem !=mNewsTabBeen.size()-1){
                        currentItem+=1;
                        mViewpagerNewContent.setCurrentItem(currentItem);
                    }
                }
            });


        mTabPageIndicator = (TabPageIndicator) view.findViewById(R.id.tab_pageindicator);
        initViewpager();
        return view;

    }

    private void initViewpager() {
        mNewsTabBeen = mNewsMenuBeen.get(0).children;
        //创建一个集合
        mViewList =new ArrayList<>();
        for (NewsCenterBean.NewsTabBean newsTabBean : mNewsTabBeen) {
            mNewsCenterTabPager = new NewsCenterTabPager(getContext());
            mViewList.add(mNewsCenterTabPager);

        }
        mNewsTabBeanList.clear();
        mNewsTabBeanList.addAll(mNewsTabBeen);

        //        viewpager去设置adapter
        NewsContentAdapter adapter = new NewsContentAdapter(mViewList,mNewsTabBeanList);

        mViewpagerNewContent.setAdapter(adapter);
        mTabPageIndicator.setViewPager(mViewpagerNewContent);
        //一上来让第一个页面进行轮播
        mViewList.get(0).startSwitch();
        //对指示器进行监听 ，让其他页面也进行轮播
        mTabPageIndicator.setOnPageChangeListener(this);

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
        ((MainActivity) getActivity()).setNewsMenuBeenLists(newsCenterBean.data);


        //创建布局
        View view = initContent();
        //加载布局
        addView(view);
        //添加视图
        cashViewMap.put(0,view);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {



    }

    @Override
    public void onPageSelected(int position) {
        //页面被选中，进行无限轮播
        //未被选中 ，就停止轮播
        for (int i = 0; i <mViewList.size() ; i++) {
            NewsCenterTabPager newsCenterTabPager = mViewList.get(i);
            //开启轮播
            if (i==position){
                newsCenterTabPager.startSwitch();

            }else {
                //停止轮播
                newsCenterTabPager.stopSwitch();

            }
        }


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //切换内容
    public void switchContent(int position) {
        // 新闻 ,专题 ,组图,互动
        //组图有一个 menu的按钮
        if (position ==2){
            mIbPic.setVisibility(View.VISIBLE);

        }else{
            mIbPic.setVisibility(View.GONE);

        }
        //创建视图 ----请求数据成功的时候进行视图的创建
        //根据角标添加视图
        View view = cashViewMap.get(position);
        //判断视图是否为空
        if (view==null){
            //创建视图
            mFlContainer.removeAllViews();//TODO  这行代码的添加确实是可以看出效果 ,完全是一个空视图,那么当创建了视图,然后添加到了map集合中,这行代码不加会怎样??
        }else if (view !=null){
            //移除视图 并且添加视图

//            添加视图到BaseFragment
            addView(view);
        }


    }
}
