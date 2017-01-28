package com.example.hashwaney.zhbj33.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.hashwaney.zhbj33.base.NewsCenterTabPager;
import com.example.hashwaney.zhbj33.bean.NewsCenterBean;
import com.example.hashwaney.zhbj33.constant.Contant;

import java.util.List;

/**
 * Created by HashWaney on 2017/1/28.
 */

public class NewsContentAdapter
        extends PagerAdapter
{
    //这是有指示器的adapter
    private List<NewsCenterBean.NewsTabBean> mNewsTabBeanList;
    private List<NewsCenterTabPager>                       mViews;


    public NewsContentAdapter(List<NewsCenterTabPager> mViews,
                              List<NewsCenterBean.NewsTabBean> newsTabBeenList)
    {
        this.mViews=mViews;
        mNewsTabBeanList = newsTabBeenList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position).mView;
        container.addView(view);
        //获取到newsContentTabpager实例
        NewsCenterTabPager newsCenterTabPager = mViews.get(position);
        String             url1               = mNewsTabBeanList.get(position).url;
        String url = Contant.REQUEST_DATA_HOST_URL+url1;
//        Log.e("aaaa", "instantiateItem: "+url1  );

        newsCenterTabPager.loadDataFormNet(url);


        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public int getCount() {
        return mViews != null
               ? mViews.size()
               : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    //将指示器的标题显示

    @Override
    public CharSequence getPageTitle(int position) {
        Log.e("bbbbb", "getPageTitle: "+ mNewsTabBeanList.get(position).title);
        return mNewsTabBeanList.get(position).title;
    }
}
