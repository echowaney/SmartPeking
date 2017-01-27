package com.example.hashwaney.zhbj33.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.hashwaney.zhbj33.base.BaseFragment;
import com.example.hashwaney.zhbj33.base.OnLoadDataOperator;

/**
 * Created by HashWaney on 2017/1/27.
 */

public class NewsCenterFragment
        extends BaseFragment implements OnLoadDataOperator
{
    @Override
    public void onLoadNewData(String url) {

    }

    @Override
    public void onLoadMoreData(String url) {

    }

    @Override
    public void initTitle() {
        setIbMenu(true);
        setIbPic(false);
        setTitle("新闻中心");
    }

    @Override
    public View initContent() {
        TextView tv =new TextView(getContext());
        tv.setText("我是新闻中心界面");
        return tv;
    }
}
