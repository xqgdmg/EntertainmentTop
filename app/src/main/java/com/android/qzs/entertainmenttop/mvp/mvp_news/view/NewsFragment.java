package com.android.qzs.entertainmenttop.mvp.mvp_news.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.qzs.entertainmenttop.R;
import com.android.qzs.entertainmenttop.adapter.CardRecyclerViewAdapter;
import com.android.qzs.entertainmenttop.adapter.NewsRecycleViewAdapter;
import com.android.qzs.entertainmenttop.base.BaseFragment;

import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.model.TopModel;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.presenter.CardTopPresenter;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.view.CardTopDetailActivity;
import com.android.qzs.entertainmenttop.mvp.mvp_news.model.NewsModel;
import com.android.qzs.entertainmenttop.mvp.mvp_news.presenter.NewsPresenter;
import com.android.qzs.entertainmenttop.utils.DividerItemDecoration;
import com.android.qzs.entertainmenttop.utils.LogUtil;
import com.melnykov.fab.FloatingActionButton;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by qzs on 2017/8/21.
 */

public class NewsFragment extends BaseFragment implements NewsView{

    @InjectView(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    private RecyclerView mRecyclerView;
    private NewsRecycleViewAdapter mRecyclerViewAdapter;
    private NewsPresenter newsPresenter=new NewsPresenter(this);
    private int mCount = 0;
    private String  strtype="";
    private List<NewsModel.ResultBeanX.ResultBean.ListBean> newlist = new ArrayList<>();
    public NewsFragment() {

    }
    public static NewsFragment newInstance(String title) {
        NewsFragment fragment = new NewsFragment();
        Bundle b = new Bundle();
        b.putString("msg", title);
        fragment.setArguments(b);
        return fragment;
    }
    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_news, null);
        ButterKnife.inject(this,view);
        // 通过getArguments--->取值
        Bundle b = getArguments();
        if (b != null) {
            strtype = b.getString("msg");
        }
        //获取mRecyclerView对象
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        fab.attachToRecyclerView(mRecyclerView);
        //代码设置scrollbar无效？未解决！
        mRecyclerView.setVerticalScrollBarEnabled(true);
        //设置下拉刷新是否可见
        //mPullLoadMoreRecyclerView.setRefreshing(true);
        //设置是否可以下拉刷新
        //mPullLoadMoreRecyclerView.setPullRefreshEnable(true);
        //设置是否可以上拉刷新
        //mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
        //显示下拉刷新
        mPullLoadMoreRecyclerView.setRefreshing(true);
        //设置上拉刷新文字
        mPullLoadMoreRecyclerView.setFooterViewText("loading");
        //设置上拉刷新文字颜色
        //mPullLoadMoreRecyclerView.setFooterViewTextColor(R.color.white);
        //设置加载更多背景色
        //mPullLoadMoreRecyclerView.setFooterViewBackgroundColor(R.color.colorBackground);
        mPullLoadMoreRecyclerView.setLinearLayout();
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());

        newsPresenter.loadNewData(strtype,"10",0,"747b4744b764f5b6dc5021e72ff9ea4c");
        mRecyclerViewAdapter = new NewsRecycleViewAdapter(context);
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        return view;
    }

    @Override
    public void initData() {
        mRecyclerViewAdapter.setOnItemClickListener(new NewsRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(context, CardTopDetailActivity.class);
                intent.putExtra("top_weburl",newlist.get(position).getWeburl()+"");
                intent.putExtra("top_pic",newlist.get(position).getPic()+"");
                context.startActivity(intent);
            }
        });

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (newsPresenter != null) {
            newsPresenter.detachView();
        }
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        mPullLoadMoreRecyclerView.scrollToTop();
    }

    @Override
    public void GetnewsSuccess(NewsModel newsModel) {
        if (newsModel.getCode().equals("10000")){
            newlist.clear();
            newlist.addAll(newsModel.getResult().getResult().getList());
            LogUtil.logConsole(newsModel.getResult().getResult().getList().size()+"   "+newsModel.getResult().getResult().getList().get(0).getTime());

            try {
                mRecyclerViewAdapter.addAllNewsData(newlist);
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void GetnewsError(Throwable e) {
        Snackbar.make(view,e.getMessage(),Snackbar.LENGTH_SHORT).show();
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }
    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            setRefresh();
            newsPresenter.loadNewData(strtype,"10",0,"747b4744b764f5b6dc5021e72ff9ea4c");
        }



        @Override
        public void onLoadMore() {
            mCount = mCount + 10;

            newsPresenter.loadNewData("头条","10",mCount,"747b4744b764f5b6dc5021e72ff9ea4c");
            LogUtil.logConsole("mcount  "+mCount);
        }
    }
    private void setRefresh() {
        mRecyclerViewAdapter.clearNewData();
        mCount = 0;
    }

}
