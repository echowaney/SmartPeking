package com.example.hashwaney.zhbj33.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.adapter.XWrapRecycleViewAdapter;

/**
 * Created by HashWaney on 2017/1/29.
 */

public class CostumeRecycleview
        extends RecyclerView
{

    private View mHeadView;
    private View mFootView;

    public CostumeRecycleview(Context context) {
        this(context,null);
    }

    public CostumeRecycleview(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public CostumeRecycleview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        this(context, attrs);

    }

    private void init() {
        //初始化头布局
        initHeadView();
        //初始化脚布局
        initFootView();


    }

    private void initFootView() {
        mFootView = inflate(getContext(), R.layout.foot_view, null);

    }

    private void initHeadView() {

        mHeadView = inflate(getContext(), R.layout.head_view, null);


    }


    //重写setAdapter 由父类进行重写,方能生效----自定义的并没有进行任何操作,因此父亲重写


    @Override
    public void setAdapter(Adapter adapter) {
        adapter =new XWrapRecycleViewAdapter(mHeadView,mFootView,adapter);
        super.setAdapter(adapter);
    }
}
