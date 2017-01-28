package com.example.hashwaney.zhbj33.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.hashwaney.zhbj33.bean.NewsCenterBean;

import java.util.List;

/**
 * Created by HashWaney on 2017/1/28.
 */

public class NewsContentAdapter
        extends PagerAdapter
{
    //这是有指示器的adapter
    private List<NewsCenterBean.NewsTabBean> mNewsTabBeanList;

    public NewsContentAdapter(List<NewsCenterBean.NewsTabBean> newsTabBeenList) {mNewsTabBeanList = newsTabBeenList;}

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
    //将指示器的标题显示

    @Override
    public CharSequence getPageTitle(int position) {
        return mNewsTabBeanList.get(position).title;
    }
}
