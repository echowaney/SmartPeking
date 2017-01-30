package com.example.hashwaney.zhbj33.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.acitivity.NewsDetailActivity;
import com.example.hashwaney.zhbj33.bean.NewCenterTabBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HashWaney on 2017/1/29.
 */

public class RecycleViewNewsListAdapter
        extends RecyclerView.Adapter<RecycleViewNewsListAdapter.NewListsViewHolder>
{
//    @BindView(R.id.iv_news_pic)
//    ImageView mIvNewsPic;
//    @BindView(R.id.tv_title)
//    TextView  mTvTitle;
//    @BindView(R.id.tv_time)
//    TextView  mTvTime;
    private List<NewCenterTabBean.NewsBean> mNewsBeanList;
    private Context                         mContext;

    public RecycleViewNewsListAdapter(Context context, List<NewCenterTabBean.NewsBean> newsBeanList)
    {
        mNewsBeanList = newsBeanList;
        mContext = context;
    }

    @Override
    public NewListsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext)
                                  .inflate(R.layout.item_news_list, parent, false);
        NewListsViewHolder holder = new NewListsViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(NewListsViewHolder holder, int position)
    {
        final NewCenterTabBean.NewsBean newsBean = mNewsBeanList.get(position);

        Picasso.with(mContext).load(newsBean.listimage).into(holder.mIvPic);
        holder.mTvTime.setText(newsBean.pubdate);
        holder.mTvTitle.setText(newsBean.title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到新闻详情界面
                Intent  intent =new Intent(mContext,NewsDetailActivity.class);
                intent.putExtra("url",newsBean.url);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNewsBeanList != null
               ? mNewsBeanList.size()
               : 0;
    }

    public class NewListsViewHolder
            extends RecyclerView.ViewHolder
    {

         TextView mTvTitle;
         TextView mTvTime;
         ImageView mIvPic;

        public NewListsViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this,itemView); 绑定的还是不行
            mIvPic = (ImageView) itemView.findViewById(R.id.iv_news_pic);
            mTvTime = (TextView) itemView.findViewById(R.id.tv_time);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);

        }
    }
}
