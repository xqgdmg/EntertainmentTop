package com.android.qzs.entertainmenttop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.qzs.entertainmenttop.R;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.model.TopModel;
import com.android.qzs.entertainmenttop.mvp.mvp_news.model.NewsModel;
import com.android.qzs.entertainmenttop.utils.LogUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/21.
 */

public class NewsRecycleViewAdapter extends RecyclerView.Adapter<NewsRecycleViewAdapter.ViewHolder> implements View.OnClickListener {
    private OnItemClickListener mOnItemClickListener = null;
    private Context mContext;
    private List<NewsModel.ResultBeanX.ResultBean.ListBean> newlist = new ArrayList<NewsModel.ResultBeanX.ResultBean.ListBean>();

    public void addAllNewsData(List<NewsModel.ResultBeanX.ResultBean.ListBean> newlist) {
        this.newlist.addAll(newlist);
        LogUtil.logConsole("秦子帅" + newlist.size());
        notifyDataSetChanged();
    }

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void clearNewData() {
        this.newlist.clear();
    }

    public NewsRecycleViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = (OnItemClickListener) listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, news_desc, news_tvtime;
        public ImageView news_photo;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            news_photo = (ImageView) itemView.findViewById(R.id.news_photo);
            news_desc = (TextView) itemView.findViewById(R.id.news_desc);
            news_tvtime = (TextView) itemView.findViewById(R.id.news_tvtime);
        }
    }

    @Override
    public NewsRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        v.setOnClickListener(this);
        return new NewsRecycleViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsRecycleViewAdapter.ViewHolder holder, final int position) {
        holder.title.setText(newlist.get(position).getTitle());
        Glide.with(mContext).load(newlist.get(position).getPic()).into(holder.news_photo);
        // holder.title.setBackgroundColor(Color.argb(20, 0, 0, 0));
        holder.news_desc.setText(newlist.get(position).getContent().substring(17, newlist.get(position).getContent().length()) + "");
        holder.news_tvtime.setText(newlist.get(position).getTime() + "");
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return newlist.size();
    }


}
