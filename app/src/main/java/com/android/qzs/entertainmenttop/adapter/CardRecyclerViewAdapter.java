package com.android.qzs.entertainmenttop.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.qzs.entertainmenttop.R;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.model.TopModel;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.view.CardTopDetailActivity;
import com.android.qzs.entertainmenttop.mvp.mvp_news.model.NewsModel;
import com.android.qzs.entertainmenttop.utils.LogUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WuXiaolong
 * on 2015/7/2.
 */
public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<TopModel.ResultBeanX.ResultBean.ListBean> dataList = new ArrayList<TopModel.ResultBeanX.ResultBean.ListBean>();

    public void addAllData(List<TopModel.ResultBeanX.ResultBean.ListBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }


    public CardRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, news_desc;
        public ImageView news_photo;
        public Button btn_more, btn_share;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            news_photo = (ImageView) itemView.findViewById(R.id.news_photo);
            news_desc = (TextView) itemView.findViewById(R.id.news_desc);
            btn_more = (Button) itemView.findViewById(R.id.btn_more);
            btn_share = (Button) itemView.findViewById(R.id.btn_share);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(dataList.get(position).getTitle());
        Glide.with(mContext).load(dataList.get(position).getPic()).into(holder.news_photo);
        holder.title.setBackgroundColor(Color.argb(20, 0, 0, 0));
        holder.news_desc.setText(dataList.get(position).getContent().substring(17, dataList.get(position).getContent().length()) + "");
        holder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CardTopDetailActivity.class);
                intent.putExtra("top_weburl", dataList.get(position).getWeburl() + "");
                intent.putExtra("top_pic", dataList.get(position).getPic() + "");
                mContext.startActivity(intent);

            }
        });
        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "分享持续更新中", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}