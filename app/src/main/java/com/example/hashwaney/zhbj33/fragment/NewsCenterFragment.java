package com.example.hashwaney.zhbj33.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.acitivity.MainActivity;
import com.example.hashwaney.zhbj33.adapter.NewsCenterGroupViewAdapter;
import com.example.hashwaney.zhbj33.adapter.NewsContentAdapter;
import com.example.hashwaney.zhbj33.base.BaseFragment;
import com.example.hashwaney.zhbj33.base.NewsCenterTabPager;
import com.example.hashwaney.zhbj33.base.OnLoadDataOperator;
import com.example.hashwaney.zhbj33.bean.NewsCenterBean;
import com.example.hashwaney.zhbj33.bean.NewsCenterGroupViewBean;
import com.example.hashwaney.zhbj33.constant.Contant;
import com.example.hashwaney.zhbj33.utils.CashUtils;
import com.example.hashwaney.zhbj33.utils.RecycleViewDivider;
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
        implements OnLoadDataOperator, ViewPager.OnPageChangeListener, View.OnClickListener
{

    private static final String TAG = "NewsCenterFragment";
    private ViewPager        mViewpagerNewContent;
    private ImageButton      mIbNext;
    private TabPageIndicator mTabPageIndicator;
    private List<NewsCenterBean.NewsTabBean> mNewsTabBeanList = new ArrayList<>();
    private List<NewsCenterBean.NewsMenuBean> mNewsMenuBeen;
    private List<NewsCenterTabPager>          mViewList;
    private List<NewsCenterBean.NewsTabBean>  mNewsTabBeen;
    private NewsCenterTabPager                mNewsCenterTabPager;

    private Map<Integer, View> cashViewMap = new HashMap<>();
    private NewsCenterBean mNewsCenterBean;
    private RecyclerView   mRecycleGroup;


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
                if (currentItem != mNewsTabBeen.size() - 1) {
                    currentItem += 1;
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
        mViewList = new ArrayList<>();
        for (NewsCenterBean.NewsTabBean newsTabBean : mNewsTabBeen) {
            mNewsCenterTabPager = new NewsCenterTabPager(getContext());
            mViewList.add(mNewsCenterTabPager);

        }
        mNewsTabBeanList.clear();
        mNewsTabBeanList.addAll(mNewsTabBeen);

        //        viewpager去设置adapter
        NewsContentAdapter adapter = new NewsContentAdapter(mViewList, mNewsTabBeanList);

        mViewpagerNewContent.setAdapter(adapter);
        mTabPageIndicator.setViewPager(mViewpagerNewContent);
        //一上来让第一个页面进行轮播
        mViewList.get(0)
                 .startSwitch();
        //对指示器进行监听 ，让其他页面也进行轮播
        mTabPageIndicator.setOnPageChangeListener(this);

    }

    @Override
    public void onLoadNetData() {
        //此处进行数据的加载 加载json数据 回调就采用StringCallBack
        final String url = Contant.NEWCENTER_REQUEST_URL;
        OkHttpUtils.get()
                   .url(url)
                   .build()
                   .execute(new StringCallback() {
                       @Override
                       public void onError(Call call, Exception e, int id) {
                           //请求失败
                           // Log.d(TAG, "onError:  e " + e.getMessage());
                           ToastUtils.showToast(getActivity(), e.getMessage());
                           try {
                               String json = CashUtils.readCash(getContext(), url);
                               if (!TextUtils.isEmpty(json))
                                        processData(json);
                           } catch (Exception e1) {
                               e1.printStackTrace();
                           }
                       }

                       @Override
                       public void onResponse(String response, int id) {
                           //请求成功
                           processData(response);
                           Log.e(TAG, "aaaonResponse: "+response );
                           //进行数据的保存
                           try {
                               CashUtils.saveCash(getContext(),url,response);
                           } catch (Exception e) {
                               e.printStackTrace();
                           }


                       }
                   });

    }

    //解析从网络上请求回来的数据
    private void processData(String response) {
//        hasLoadData=true;
        //hasLoadData=true;

        Gson gson = new Gson();
        mNewsCenterBean = gson.fromJson(response, NewsCenterBean.class);
        //将数据传递给menu
        //menu是属于MainActivity的-----
        //创建一个方法用来接收Fragment传递过来的数据
        mNewsMenuBeen = mNewsCenterBean.data;
        if (mNewsMenuBeen != null) {
            ((MainActivity) getActivity()).setNewsMenuBeenLists(mNewsCenterBean.data);
        }


        //创建布局
        View view = initContent();
        //加载布局
        addView(view);
        //添加视图
        cashViewMap.put(0, view);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
        //页面被选中，进行无限轮播
        //未被选中 ，就停止轮播
        for (int i = 0; i < mViewList.size(); i++) {
            NewsCenterTabPager newsCenterTabPager = mViewList.get(i);
            //开启轮播
            if (i == position) {
                newsCenterTabPager.startSwitch();

            } else {
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
        if (position == 2) {
            mIbPic.setVisibility(View.VISIBLE);

        } else {
            mIbPic.setVisibility(View.GONE);

        }
        //创建视图 ----请求数据成功的时候进行视图的创建
        //根据角标添加视图
        View view = cashViewMap.get(position);
        //判断视图是否为空
        if (view == null) {
            //创建视图
            mFlContainer.removeAllViews();//TODO  这行代码的添加确实是可以看出效果 ,完全是一个空视图,那么当创建了视图,然后添加到了map集合中,这行代码不加会怎样??
            if (position==2){
                //加载组图布局
                //创建一个视图
               view = creatGroupImageView();
                //添加到容器中
                addView(view);
                //将数据添加到我们的集合中,避免反复加载数据
                cashViewMap.put(position, view);

                //加载数据
                loadGroupViewData(position);
                //设置点击事件
                mIbPic.setOnClickListener(this);
            }


        } else if (view != null) {
            //移除视图 并且添加视图
            mFlContainer.removeAllViews();
            //            添加视图到BaseFragment
            addView(view);
        }


    }

    private void loadGroupViewData(int position) {
        final String url = Contant.REQUEST_DATA_HOST_URL + mNewsMenuBeen.get(position).url;
        Log.e(TAG, "loadGroupViewData:  url " + url);


        OkHttpUtils.get()
                   .url(url)
                   .build()
                   .execute(new StringCallback() {
                       @Override
                       public void onError(Call call, Exception e, int id) {
                           //请求失败
                           Log.e(TAG, "onError: 请求数据失败" + e.getMessage());
                           try {
                               String json = CashUtils.readCash(getContext(), url);
                               //进行数据的展示
                               if (!TextUtils.isEmpty(json))
                                     processGroupData(json);

                           } catch (Exception e1) {
                               e1.printStackTrace();
                           }

                       }

                       @Override
                       public void onResponse(String response, int id) {
                           //请求成功
                           Log.e(TAG, "onResponse: " + response);
                           processGroupData(response);
                           try {
                               CashUtils.saveCash(getContext(),url,response);
                           } catch (Exception e) {
                               e.printStackTrace();
                           }


                       }
                   });

    }
    //用来加载组图数据
    private void processGroupData(String response) {
        Gson gson = new Gson();
        NewsCenterGroupViewBean centerGroupViewBean = gson.fromJson(response,
                                                                    NewsCenterGroupViewBean.class);
        mRecycleGroup.setAdapter(new NewsCenterGroupViewAdapter(getContext(),
                                                                centerGroupViewBean.data.news));
    }

    private View creatGroupImageView() {
        //        getDecorView : 最顶层的view
        View view = LayoutInflater.from(getContext())
                                  .inflate(R.layout.
                                                   newscenter_group_view,
                                           (ViewGroup) getActivity().getWindow()
                                                                    .getDecorView(),
                                           false);
        mRecycleGroup = (RecyclerView) view.findViewById(R.id.recycleview_group);
        //设置布局管理器
        mRecycleGroup.setLayoutManager(new LinearLayoutManager(getContext()));
        // mRecycleGroup.setLayoutManager(new GridLayoutManager(getContext(), 2));

        return view;


    }

    private              int GroupImageState = LISTSTATE;
    private final static int LISTSTATE       = 0;
    private final static int GRIDSTATE       = 1;

    //点击按钮实现网格和列表组图的切换
    @Override
    public void onClick(View v) {
        //如果当前是列表组图 切换到 网格组图
        if (GroupImageState == LISTSTATE) {
            GroupImageState = GRIDSTATE;
            mIbPic.setBackgroundResource(R.drawable.icon_pic_grid_type);
            mRecycleGroup.setLayoutManager(new GridLayoutManager(getContext(),
                                                                 2,
                                                                 GridLayoutManager.VERTICAL,
                                                                 false));
            mRecycleGroup.addItemDecoration(new RecycleViewDivider(getContext(),
                                                                   GridLayoutManager.VERTICAL,
                                                                   2,
                                                                   Color.parseColor("#33ffffff")));

        } else {
            GroupImageState = LISTSTATE;
            mIbPic.setBackgroundResource(R.drawable.icon_pic_list_type);
            mRecycleGroup.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecycleGroup.addItemDecoration(new RecycleViewDivider(getContext(),
                                                                   LinearLayoutManager.HORIZONTAL,
                                                                   2,
                                                                   Color.parseColor("#33ffffff")));
        }


    }

}
