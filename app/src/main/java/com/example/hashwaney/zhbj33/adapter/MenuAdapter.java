package com.example.hashwaney.zhbj33.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.bean.NewsCenterBean;

import java.util.List;

/**
 * Created by HashWaney on 2017/1/28.
 */

public class MenuAdapter
        extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>
{
    private List<NewsCenterBean.NewsMenuBean> mNewsMenuBeenLists;

    public MenuAdapter(List<NewsCenterBean.NewsMenuBean> newsMenuBeenLists) {
        this.mNewsMenuBeenLists = newsMenuBeenLists;
    }

    //用来接收从activity中传过来的数据
    public void setMenuBeanLists(List<NewsCenterBean.NewsMenuBean> newsMenuBeenLists) {
        mNewsMenuBeenLists =newsMenuBeenLists;

    }


    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                                     .inflate(R.layout.slidingmenu_item, parent, false);
        MenuViewHolder holder =new MenuViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        NewsCenterBean.NewsMenuBean newsMenuBean = mNewsMenuBeenLists.get(position);
        String                      title        = newsMenuBean.title;
        holder.mTvMenuTitle.setText(title);

    }

    @Override
    public int getItemCount() {
        return mNewsMenuBeenLists==null?0:mNewsMenuBeenLists.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder{

     TextView mTvMenuTitle;

        public MenuViewHolder(View itemView) {
            super(itemView);
            mTvMenuTitle = (TextView) itemView.findViewById(R.id.tv_menu_title);
        }
    }

}
