package com.example.hashwaney.zhbj33.base;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.adapter.SwitchImageAdapter;
import com.example.hashwaney.zhbj33.bean.NewCenterTabBean;
import com.example.hashwaney.zhbj33.view.ImageViewSwitchViewpager;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by HashWaney on 2017/1/28.
 * 这是新闻中心的tab页面的封装
 * 每一个tab页面的布局都是差不多的 都是一个viewpager的轮播图 +recycleview
 *
 */

public class NewsCenterTabPager
        implements ViewPager.OnPageChangeListener
{
    private final static String TAG = "NewsCenterTabPager";
    @BindView(R.id.viewpager)
    ImageViewSwitchViewpager mViewpager;
    @BindView(R.id.ll_poitn_container)
    LinearLayout             mLlPoitnContainer;
    @BindView(R.id.recycleview)
    RecyclerView             mRecycleview;
    @BindView(R.id.tv_title)
    TextView                 mTextView;
    private Context mContext;
    public  View    mView;// 提取这个view是为了方便外面可以调用这个成员变量

    private List<ImageView>                    mImageViewList;
    private List<NewCenterTabBean.NewsTopBean> mTopnews;

    private Handler mHandler =new Handler();
    private NewCenterTabBean mNewCenterTabBean;


    //开始轮播
    public  void  startSwitch(){
        //延时两秒进行
        mHandler.postDelayed(new SwitchTask(),2000);
    }
    //结束轮播
    public void stopSwitch(){
        mHandler.removeCallbacksAndMessages(null);
    }

    //进行无限轮播的任务----处理轮播的逻辑为什么要放在run方法中
    class SwitchTask implements  Runnable{

        @Override
        public void run() {
            //处理轮播的逻辑
            int currentItem = mViewpager.getCurrentItem();
            if (currentItem==mNewCenterTabBean.data.topnews.size()-1){
                currentItem =0;
            }else {
                currentItem++;
            }
            mViewpager.setCurrentItem(currentItem);
            mHandler.postDelayed(this,2000);
        }
    }
    public NewsCenterTabPager(Context context) {
        mContext = context;
        //加载一个视图
        mView = initView();
    }

    private View initView() {
        View view = LayoutInflater.from(mContext)
                                  .inflate(R.layout.news_tab_base, null);
        //        ButterKnife.bind(view); 注意要将视图绑定在目标视图不然会找不到相应的试图
        ButterKnife.bind(this, view);
        return view;

    }

    //暴露一个方法让外部实现网络加载数据,当页面被点击切换了,就去加载网络数据,并且去执行初始化轮播图,小圆点
    public void loadDataFormNet(String url) {
        OkHttpUtils.get()
                   .url(url)
                   .build()
                   .execute(new StringCallback() {
                       @Override
                       public void onError(Call call, Exception e, int id) {
                           //请求数据失败
                           Log.d(TAG, "onError: " + e.getMessage());
                       }

                       @Override
                       public void onResponse(String response, int id) {
                           //请求网络数据成功
                           Log.e(TAG, "onResponse: " + response);
                           //处理网络数据
                           processData(response);
                       }
                   });

    }

    //处理从网络上加载的数据
    private void processData(String response) {
        //解析数据
        Gson             gson             = new Gson();
        mNewCenterTabBean = gson.fromJson(response, NewCenterTabBean.class);
        mTopnews = mNewCenterTabBean.data.topnews;
        //初始化轮播图
        initSwitchPage();
        //初始化标题
        initPageTitle();
        initPoint();
//        startSwitch();

        //将当前对象传递给imageviewswitchviewpager
        mViewpager.setTabPager(this);


    }
    //初始化小圆点
    private void initPoint() {
        mLlPoitnContainer.removeAllViews();
//        int currentItem = mViewpager.getCurrentItem();
        for (int i = 0; i <mTopnews.size() ; i++) {
            View view =new View(mContext);
            LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(5,5);
            params.rightMargin =10;
//            if (i==0){
//                view.setBackgroundResource(R.drawable.point_red_shape);
//            }
//            if (currentItem==i){
//                view.setBackgroundResource(R.drawable.point_red_shape);
//            }else {
                view.setBackgroundResource(R.drawable.point_gray_shape);
//            }

            mLlPoitnContainer.addView(view, params);
        }
        //让第一个孩子进行选中
        mLlPoitnContainer.getChildAt(0).setBackgroundResource(R.drawable.point_red_shape);
    }

    private void initPageTitle() {
        //初始化第一页标题
        String title = mTopnews.get(0).title;
        mTextView.setText(title);

    }

    private void initSwitchPage() {
        mImageViewList = new ArrayList<>();

        for (int i = -1; i <mTopnews.size()+1; i++) {
            if (i==-1){
                i=mTopnews.size()-1;

            }else if (i==mTopnews.size()){
                i=0;
            }

            ImageView imageView = new ImageView(mContext);
            String    topimage  = mTopnews.get(i).topimage;
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(mContext)
                   .load(topimage)
                   .into(imageView);
            mImageViewList.add(imageView);



        }

//        for (NewCenterTabBean.NewsTopBean topnew : mTopnews) {
//            ImageView imageView = new ImageView(mContext);
//            String    topimage  = topnew.topimage;
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            Picasso.with(mContext)
//                   .load(topimage)
//                   .into(imageView);
//            mImageViewList.add(imageView);
//        }
        SwitchImageAdapter adapter = new SwitchImageAdapter(mImageViewList);
        mViewpager.setAdapter(adapter);
        mViewpager.addOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    //当页面被选中时
    @Override
    public void onPageSelected(int position) {
        String title = mTopnews.get(position).title;
        mTextView.setText(title);
//        int currentItem = mViewpager.getCurrentItem();
//        View childAt    = mLlPoitnContainer.getChildAt(position);
//        if (currentItem==position){
//            childAt.setBackgroundResource(R.drawable.point_red_shape);
//        }else{
//            childAt.setBackgroundResource(R.drawable.point_gray_shape);
//        }
        int childCount = mLlPoitnContainer.getChildCount();
        for (int i = 0; i <childCount ; i++) {
            View childAt = mLlPoitnContainer.getChildAt(i);
            if (i==position){
                childAt.setBackgroundResource(R.drawable.point_red_shape);
            }else {
                childAt.setBackgroundResource(R.drawable.point_gray_shape);
            }


        }


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
