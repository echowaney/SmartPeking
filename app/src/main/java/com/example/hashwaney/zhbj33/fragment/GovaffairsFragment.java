package com.example.hashwaney.zhbj33.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.hashwaney.zhbj33.base.BaseFragment;
import com.example.hashwaney.zhbj33.base.OnLoadDataOperator;

/**
 * Created by HashWaney on 2017/1/27.
 */

public class GovaffairsFragment
        extends BaseFragment implements OnLoadDataOperator
{





    @Override
    public void initTitle() {
        setIbMenu(true);
        setTitle("政务");
        setIbPic(false);
    }

    @Override
    public View initContent() {
        TextView tv =new TextView(getContext());
        tv.setText("我是政务界面");
        return tv;
    }

    @Override
    public void onLoadNetData() {

    }
}
