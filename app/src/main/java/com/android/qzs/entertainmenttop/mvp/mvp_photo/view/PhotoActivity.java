package com.android.qzs.entertainmenttop.mvp.mvp_photo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.android.qzs.entertainmenttop.R;
import com.android.qzs.entertainmenttop.adapter.NewsRecycleViewAdapter;
import com.android.qzs.entertainmenttop.adapter.StaggeredRecycleViewAdapter;
import com.android.qzs.entertainmenttop.base.BaseActivity;
import com.android.qzs.entertainmenttop.mvp.mvp_news.model.NewsModel;
import com.android.qzs.entertainmenttop.mvp.mvp_news.presenter.NewsPresenter;
import com.android.qzs.entertainmenttop.mvp.mvp_news.view.NewsFragment;
import com.android.qzs.entertainmenttop.mvp.mvp_photo.model.PhotoModel;
import com.android.qzs.entertainmenttop.mvp.mvp_photo.presenter.PhotoPresenter;
import com.android.qzs.entertainmenttop.retrofit.ApiClient;
import com.android.qzs.entertainmenttop.utils.DividerItemDecoration;
import com.android.qzs.entertainmenttop.utils.LogUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/8/22.
 */

public class PhotoActivity extends BaseActivity  implements PhotoView{
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private PhotoPresenter photoPresenter=new PhotoPresenter(this);
    private List<PhotoModel.DataBean> photolist = new ArrayList<PhotoModel.DataBean>();
    private int mCount = 0;
    private RecyclerView mRecyclerView;
    private StaggeredRecycleViewAdapter mRecyclerViewAdapter;
    private  String [] arr={"长腿","素颜","气质","诱惑","唯美"};
    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_photo);
        ButterKnife.inject(this);
        //LogUtil.logConsole(ApiClient.SELECT_BASE + " " + ApiClient.BaseURL);
        initToobar();
        initRecycleView();
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mPullLoadMoreRecyclerView.setRefreshing(false);
        mPullLoadMoreRecyclerView.setPullRefreshEnable(false);
        mPullLoadMoreRecyclerView.setFooterViewText("loading");
        mPullLoadMoreRecyclerView.setStaggeredGridLayout(2);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        photoPresenter.loadPhotoData(0,20,"美女","性感");
        mRecyclerViewAdapter = new StaggeredRecycleViewAdapter(PhotoActivity.this);
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);


    }

    private void initRecycleView() {


    }

    private void initToobar() {
        //toolbar的设置
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
            ab.setTitle("图片");
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void loadData() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
              finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void GetPhotoSuccess(PhotoModel photoModel) {
        if (photoModel.getData()!=null){

            try {
                photolist.clear();
                photolist.addAll(photoModel.getData());
                mRecyclerViewAdapter.addAllNewsData(photolist);
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            //Snackbar.make(view,photoModel.getMsg(),Snackbar.LENGTH_SHORT).show();
        }

    }

    @Override
    public void GetPhotoError(Throwable e) {
        LogUtil.logConsole("哈哈1111哈哈"+e);
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }
    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {

        }



        @Override
        public void onLoadMore() {
            if (mCount<3){
                photoPresenter.loadPhotoData(0,20,"美女",arr[mCount]);
                mCount=mCount+1;
            }else {
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                Snackbar.make(mRecyclerView.getRootView(),"无数据加载...",Snackbar.LENGTH_SHORT).show();
            }
            LogUtil.logConsole("mcount  "+mCount);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (photoPresenter != null) {
            photoPresenter.detachView();
        }
    }
}
