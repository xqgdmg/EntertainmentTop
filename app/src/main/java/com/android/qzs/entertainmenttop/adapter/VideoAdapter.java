package com.android.qzs.entertainmenttop.adapter;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.qzs.entertainmenttop.R;
import com.android.qzs.entertainmenttop.adapter.holder.VideoViewHolder;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.model.TopModel;
import com.android.qzs.entertainmenttop.mvp.mvp_video.model.VideoModel;
import com.xiao.nicevideoplayer.TxVideoPlayerController;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoJianjun on 2017/5/21.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    private Context mContext;
    private List<VideoModel> mVideoList=new ArrayList<VideoModel>();

    public VideoAdapter(Context context) {
        mContext = context;
     //   mVideoList = videoList;
    }
    public void addAllData(List<VideoModel> mVideoList) {
        this.mVideoList.addAll(mVideoList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.mVideoList.clear();
    }
    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
        VideoViewHolder holder = new VideoViewHolder(itemView);
        TxVideoPlayerController controller = new TxVideoPlayerController(mContext);
        holder.setController(controller);
        return holder;
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
       VideoModel video = mVideoList.get(position);
        holder.bindData(video);
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }
}
