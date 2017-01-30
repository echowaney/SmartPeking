package com.example.hashwaney.zhbj33.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.acitivity.MainActivity;
import com.example.hashwaney.zhbj33.base.BaseFragment;
import com.example.hashwaney.zhbj33.bean.NewsCenterBean;
import com.example.hashwaney.zhbj33.fragment.NewsCenterFragment;
import com.example.hashwaney.zhbj33.utils.ToastUtils;

import java.util.List;

/**
 * Created by HashWaney on 2017/1/28.
 */

public class MenuAdapter
        extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>
{
    private List<NewsCenterBean.NewsMenuBean> mNewsMenuBeenLists;
    private int                               checkPosition;
    private Context                           mContext;

    public MenuAdapter(Context context, List<NewsCenterBean.NewsMenuBean> newsMenuBeenLists) {
        this.mNewsMenuBeenLists = newsMenuBeenLists;
        this.mContext = context;
    }

    //用来接收从activity中传过来的数据
    public void setMenuBeanLists(List<NewsCenterBean.NewsMenuBean> newsMenuBeenLists) {
        mNewsMenuBeenLists = newsMenuBeenLists;
        notifyDataSetChanged();

    }


    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.slidingmenu_item, parent, false);
        MenuViewHolder holder = new MenuViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(final MenuViewHolder holder, final int position) {
        final NewsCenterBean.NewsMenuBean newsMenuBean = mNewsMenuBeenLists.get(position);
        String                            title        = newsMenuBean.title;
        holder.mTvMenuTitle.setText(title);
        //        if (checkPosition==position){
        //            holder.mTvMenuTitle.setPressed(true);
        //
        //        }else {
        //

        //    }
        if (checkPosition == position) {
            holder.mTvMenuTitle.setTextColor(Color.RED);
            holder.mIvArr.setImageResource(R.drawable.menu_arr_select);

        } else {
            holder.mTvMenuTitle.setTextColor(Color.WHITE);
            holder.mIvArr.setImageResource(R.drawable.menu_arr_normal);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击了条目 改变颜色 以及 关闭 侧滑菜单
                //点击条目直接关闭侧滑菜单
                /**
                 * 1 .改变颜色 ---被点击的改变颜色
                 * 2.关闭 侧滑 ---Slidingmenu
                 *
                 */
                if (checkPosition != position) {
                    checkPosition = position;
                    //刷新界面 ,因为界面有发生变化,字体颜色改变了
                    notifyDataSetChanged();

                    //点击条目.切换fragment的标题
                    /**
                     * 1. 首先获取到newsenterfragment的实例 -- MainActivity
                     *
                     * 2. 通过实例对象上的setTitle 方法来设置标题
                     */
                    BaseFragment baseFragment = ((MainActivity) mContext).getCurrentFragment();
                    baseFragment.setTitle(newsMenuBean.title);
//
//                    boolean b = NewsCenterFragment instanceof baseFragment;
                    boolean b = baseFragment instanceof NewsCenterFragment;
                    ToastUtils.showToast(mContext,b +"");

                }

                //                notifyDataSetChanged();
                //              if (checkPosition == position){
                ////                  holder.mTvMenuTitle.setPressed(true);
                //                  holder.mTvMenuTitle.setTextColor(Color.RED);
                //                  //这样做其实也是不行的,只能选中第一个


                //              }else{
                ////                  holder.mTvMenuTitle.setPressed(false);
                //                  holder.mTvMenuTitle.setTextColor(Color.WHITE);
                //                  checkPosition =position;

                //              }

                //关闭菜单
                ((MainActivity) mContext).mSlidingMenu.toggle();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNewsMenuBeenLists == null
               ? 0
               : mNewsMenuBeenLists.size();
    }

    class MenuViewHolder
            extends RecyclerView.ViewHolder
    {

        TextView  mTvMenuTitle;
        ImageView mIvArr;

        public MenuViewHolder(View itemView) {
            super(itemView);
            mTvMenuTitle = (TextView) itemView.findViewById(R.id.tv_menu_title);
            mIvArr = (ImageView) itemView.findViewById(R.id.iv_menu_arr);
        }
    }

}
