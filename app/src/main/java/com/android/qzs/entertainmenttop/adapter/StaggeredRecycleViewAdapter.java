package com.android.qzs.entertainmenttop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.qzs.entertainmenttop.R;
import com.android.qzs.entertainmenttop.mvp.mvp_news.model.NewsModel;
import com.android.qzs.entertainmenttop.mvp.mvp_photo.model.PhotoModel;
import com.android.qzs.entertainmenttop.mvp.mvp_photo.view.PhotoActivity;
import com.android.qzs.entertainmenttop.mvp.mvp_photo.view.PhotoDetailsActivity;
import com.android.qzs.entertainmenttop.utils.LogUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WuXiaolong on 2015/9/14.
 */
public class StaggeredRecycleViewAdapter extends RecyclerView.Adapter<StaggeredRecycleViewAdapter.ViewHolder>  implements View.OnClickListener{
    private OnItemClickListener mOnItemClickListener = null;
    private Context mContext;
    private List<PhotoModel.DataBean> photolist=new ArrayList<PhotoModel.DataBean>();;



    public StaggeredRecycleViewAdapter(Context context) {

        mContext = context;
    }
    public void clearNewData() {
        this.photolist.clear();
    }
    public void addAllNewsData(List<PhotoModel.DataBean> photolist) {
        this.photolist.addAll(photolist);
        LogUtil.logConsole("秦子帅"+photolist.size());
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = (OnItemClickListener) listener;
    }
    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_photo;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_photo = (ImageView) itemView.findViewById(R.id.iv_photo);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.staggered_recycler_view_item, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
      //  Glide.with(mContext).load(photolist.get(position).getPicUrl()).into(holder.iv_photo);
        Glide.with(mContext)
                .load(photolist.get(position).getImage_url())
                .placeholder(R.mipmap.girls)
               // .crossFade()
                .into(holder.iv_photo);

holder.iv_photo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(mContext,PhotoDetailsActivity.class);
        intent.putExtra("img_url",photolist.get(position).getImage_url());
        mContext.startActivity(intent);
    }
});

    }

    @Override
    public int getItemCount() {
        return photolist.size();
    }
}
