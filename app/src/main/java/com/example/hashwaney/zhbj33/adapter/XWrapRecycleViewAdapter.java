package com.example.hashwaney.zhbj33.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HashWaney on 2017/1/29.
 */

public class XWrapRecycleViewAdapter
        extends RecyclerView.Adapter
{
    public XWrapRecycleViewAdapter(View headView, View footView, RecyclerView.Adapter adapter) {
        mHeadView = headView;
        mFootView = footView;
        mAdapter = adapter;
    }

    //头布局
    private View                 mHeadView;
    //脚布局
    private View                 mFootView;
    //适配器
    private RecyclerView.Adapter mAdapter;

    //通过构造获取上面的三个参数


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            // 说明是头布局
            return RecyclerView.INVALID_TYPE;

     }
//        else if (position == mAdapter.getItemCount() + 1) {
//            return -2;
//        }
        //正常布局
                int adjPosition = position -1;
                int adapterCount = mAdapter.getItemCount();
                if (adjPosition<adapterCount){

                    return mAdapter.getItemViewType(adjPosition);
                }


        return RecyclerView.INVALID_TYPE-1;//默认的返回0
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType ==RecyclerView.INVALID_TYPE) {
            //头布局
            return new HeadViewHolder(mHeadView);

        } else if (viewType == RecyclerView.INVALID_TYPE-1) {
            //脚布局
            return new FootViewHolder(mFootView);
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }


    //绑定数据 只有正常的条目才会绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            //头布局  不进行处理
            return;

        }

        //正常布局
        int adjPostion   = position - 1;
        int adapterCount = mAdapter.getItemCount();
        if (adjPostion < adapterCount) {
            mAdapter.onBindViewHolder(holder, adjPostion);
        }
        //              if (position < mAdapter.getItemCount() + 1) {
        //                  mAdapter.onBindViewHolder(holder, position); //角标越界
        //              }
    }

    @Override
    public int getItemCount() {
        return mAdapter != null
               ? mAdapter.getItemCount() + 2
               : 0;
    }

    //头布局的viewholder
    class HeadViewHolder
            extends RecyclerView.ViewHolder
    {
        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    //脚布局的viewholder
    class FootViewHolder
            extends RecyclerView.ViewHolder
    {
        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }
}
