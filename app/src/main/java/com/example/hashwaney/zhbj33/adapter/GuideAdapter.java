package com.example.hashwaney.zhbj33.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by HashWaney on 2017/1/27.
 */

public class GuideAdapter
        extends PagerAdapter
{
    private List<ImageView> mImageViewList;

    public GuideAdapter(List<ImageView> imageViewList) {mImageViewList = imageViewList;}

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = mImageViewList.get(position);
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mImageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
