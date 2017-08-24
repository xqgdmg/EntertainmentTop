package com.android.qzs.entertainmenttop.mvp.mvp_video.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.qzs.entertainmenttop.R;
import com.android.qzs.entertainmenttop.adapter.CardRecyclerViewAdapter;
import com.android.qzs.entertainmenttop.adapter.VideoAdapter;
import com.android.qzs.entertainmenttop.adapter.holder.VideoViewHolder;
import com.android.qzs.entertainmenttop.base.BaseFragment;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.model.TopModel;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.presenter.CardTopPresenter;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.view.CardTopFragment;
import com.android.qzs.entertainmenttop.mvp.mvp_video.model.VideoModel;
import com.android.qzs.entertainmenttop.mvp.mvp_video.presenter.VideoPresenter;
import com.android.qzs.entertainmenttop.utils.DataUtil;
import com.android.qzs.entertainmenttop.utils.LogUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by qzs on 2017/8/18.
 */

public class VideoFragment extends BaseFragment  implements VideoView{

    @InjectView(R.id.Video_pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView mVideo_pullLoadMoreRecyclerView;

    private RecyclerView mRecyclerView;
    private List<VideoModel> videolist = new ArrayList<VideoModel>();
    private VideoAdapter videoAdapter;
    private VideoPresenter videoPresenter=new VideoPresenter(this);

    public VideoFragment( ) {

    }
    public static VideoFragment newInstance(String title) {
        VideoFragment fragment = new VideoFragment();
        Bundle b = new Bundle();
        b.putString("msg", title);
        fragment.setArguments(b);
        return fragment;
    }
    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_video, null);
        ButterKnife.inject(this,view);

        //获取mRecyclerView对象
        mRecyclerView = mVideo_pullLoadMoreRecyclerView.getRecyclerView();
        mRecyclerView.setHasFixedSize(true);

        //代码设置scrollbar无效？未解决！
        mRecyclerView.setVerticalScrollBarEnabled(true);
        //设置下拉刷新是否可见
        //mPullLoadMoreRecyclerView.setRefreshing(true);
        //设置是否可以下拉刷新
        //mPullLoadMoreRecyclerView.setPullRefreshEnable(true);
        //设置是否可以上拉刷新
        //mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
        //显示下拉刷新
        mVideo_pullLoadMoreRecyclerView.setRefreshing(true);
        //设置上拉刷新文字
        mVideo_pullLoadMoreRecyclerView.setFooterViewText("loading");
        //设置上拉刷新文字颜色
        //mPullLoadMoreRecyclerView.setFooterViewTextColor(R.color.white);
        //设置加载更多背景色
        //mPullLoadMoreRecyclerView.setFooterViewBackgroundColor(R.color.colorBackground);
        mVideo_pullLoadMoreRecyclerView.setLinearLayout();
        mVideo_pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());



        videoAdapter = new VideoAdapter(context);
        mVideo_pullLoadMoreRecyclerView.setAdapter(videoAdapter);
//主要一步
        videoPresenter.loadVideoData();

        mRecyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                NiceVideoPlayer niceVideoPlayer = ((VideoViewHolder) holder).mVideoPlayer;
                if (niceVideoPlayer == NiceVideoPlayerManager.instance().getCurrentNiceVideoPlayer()) {
                    NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
                }
            }
        });

        return view;
    }



    @Override
    public void initData() {

    }

    @Override
    public void GetVideoSuccess(List<VideoModel> videoModels) {

        videoAdapter.addAllData(videoModels);
        mVideo_pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            setRefresh();
         //   cardTopPresenter.loadWeatherData("头条","10",0,"747b4744b764f5b6dc5021e72ff9ea4c");
        }



        @Override
        public void onLoadMore() {
            mVideo_pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
//            mCount = mCount + 10;
//
//            cardTopPresenter.loadWeatherData("头条","10",mCount,"747b4744b764f5b6dc5021e72ff9ea4c");
//            LogUtil.logConsole("mcount  "+mCount);
        }
    }

    private void setRefresh() {
      //  videoAdapter.clearData();
        mVideo_pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
     //   mCount = 0;

    }

    @Override
    public void onStop() {
        super.onStop();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

}
