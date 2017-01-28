package com.example.hashwaney.zhbj33.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by HashWaney on 2017/1/28.
 */

public class SwitchImageAdapter
        extends PagerAdapter
{
    public SwitchImageAdapter(List<ImageView> imageViews) {
        mImageViews = imageViews;
    }

    private List<ImageView> mImageViews;

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = mImageViews.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);


    }

    @Override
    public int getCount() {
        return mImageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
