package com.example.hashwaney.zhbj33.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hashwaney.zhbj33.R;
import com.example.hashwaney.zhbj33.acitivity.NewsDetailActivity;
import com.example.hashwaney.zhbj33.bean.NewCenterTabBean;
import com.example.hashwaney.zhbj33.constant.Contant;
import com.example.hashwaney.zhbj33.utils.SPUtils;
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
    public void onBindViewHolder(final NewListsViewHolder holder, int position)
    {
        final NewCenterTabBean.NewsBean newsBean = mNewsBeanList.get(position);

        Picasso.with(mContext).load(newsBean.listimage).into(holder.mIvPic);
        holder.mTvTime.setText(newsBean.pubdate);
        holder.mTvTitle.setText(newsBean.title);

        String readContentStr = SPUtils.getString(mContext, Contant.HAS_READ, "");
        if (readContentStr.contains(newsBean.id)){
            //设置为灰色
            //已读
            holder.mTvTitle.setTextColor(Color.GRAY);
        }else {
            //未读
            holder.mTvTitle.setTextColor(Color.BLACK);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到新闻详情界面
                Intent  intent =new Intent(mContext,NewsDetailActivity.class);
                intent.putExtra("url",newsBean.url);
                mContext.startActivity(intent);
                // 将读过的新闻颜色字体变为灰色
                //拿到 新闻的唯一标识
                String newId = newsBean.id;
                String readStr = SPUtils.getString(mContext, Contant.HAS_READ, "");
                //如果这个新闻id没有包含在这个字符串中,那么就保存
                if (!readStr.contains(newId)){

                    //没有读过就添加到读取过的新闻id字符中,这样下次进来就可以判断当前的新闻id是否包含在这个新闻id字符串
                    SPUtils.saveString(mContext,Contant.HAS_READ,newId);
                    //点击改变字体颜色 ,因此在这里可以改变字体的颜色
                    holder.mTvTitle.setTextColor(Color.GRAY);
                }


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
