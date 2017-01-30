package com.example.hashwaney.zhbj33.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.bean.NewsCenterGroupViewBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HashWaney on 2017/1/30.
 */

public class NewsCenterGroupViewAdapter
        extends RecyclerView.Adapter
{
    private Context                                               mContext;
    private List<NewsCenterGroupViewBean.NewsCenterGroupNewsBean> mList;

    public NewsCenterGroupViewAdapter(Context context,
                                      List<NewsCenterGroupViewBean.NewsCenterGroupNewsBean> list)
    {
        mContext = context;
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                                  .inflate(R.layout.newscenter_group_view_news, parent, false);
        ViewHolder holder =new ViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsCenterGroupViewBean.NewsCenterGroupNewsBean groupNewsBean = mList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        Picasso.with(mContext).load( groupNewsBean.listimage).into(viewHolder.mIvPic);
        viewHolder.mTvTitle.setText(groupNewsBean.title);


    }

    @Override
    public int getItemCount() {
        return mList != null
                         ?mList.size():0;
    }

    static class ViewHolder
            extends RecyclerView.ViewHolder
    {
        @BindView(R.id.iv_pic)
        ImageView mIvPic;
        @BindView(R.id.tv_title)
        TextView  mTvTitle;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
