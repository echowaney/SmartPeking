package com.example.hashwaney.zhbj33.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.hashwaney.zhbj33.R;

/**
 * Created by HashWaney on 2017/1/28.
 * 这是新闻中心的tab页面的封装
 * 每一个tab页面的布局都是差不多的 都是一个viewpager的轮播图 +recycleview
 *
 */

public class NewsCenterTabPager {

   private Context mContext;
   public  View mView;// 提取这个view是为了方便外面可以调用这个成员变量

    public NewsCenterTabPager(Context context) {
        mContext = context;
        //加载一个视图
        mView = initView();
    }

    private View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_tab_base,null);
        return view;

    }


}
