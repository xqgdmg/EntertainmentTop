package com.android.qzs.entertainmenttop.mvp.mvp_cardtop.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.qzs.entertainmenttop.R;
import com.android.qzs.entertainmenttop.adapter.CardRecyclerViewAdapter;
import com.android.qzs.entertainmenttop.base.BaseFragment;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.model.TopModel;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.presenter.CardTopPresenter;
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
 * Created by qzs on 2017/8/17.  卡片式头条
 */

@SuppressLint("ValidFragment")
public class CardTopFragment extends BaseFragment implements CardTopView {


    @InjectView(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;

    @InjectView(R.id.fab)
    FloatingActionButton  fab;


    private RecyclerView mRecyclerView;
    private CardRecyclerViewAdapter mRecyclerViewAdapter;
    private CardTopPresenter cardTopPresenter=new CardTopPresenter(this);
    private int mCount = 0;
    private  List<TopModel.ResultBeanX.ResultBean.ListBean> dataList = new ArrayList<>();



    public CardTopFragment(){}
    public static CardTopFragment newInstance(String title) {
        CardTopFragment fragment = new CardTopFragment();
        Bundle b = new Bundle();
        b.putString("msg", title);
        fragment.setArguments(b);
        return fragment;
    }


    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_cardtop, null);
        ButterKnife.inject(this,view);
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
        cardTopPresenter.loadTopData("头条","10",0,"747b4744b764f5b6dc5021e72ff9ea4c");

        mRecyclerViewAdapter = new CardRecyclerViewAdapter(context);
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
      //  getData();
        return view;
    }

    @Override
    public void initData() {

    }

@OnClick(R.id.fab)
public void onClick(View view) {
    mPullLoadMoreRecyclerView.scrollToTop();
}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cardTopPresenter != null) {
            cardTopPresenter.detachView();
        }
    }

    @Override
    public void GetcardTopSuccess(TopModel topModel) {
      if (topModel.getCode().equals("10000")){
          dataList.clear();
          dataList.addAll(topModel.getResult().getResult().getList());
          LogUtil.logConsole(topModel.getResult().getResult().getList().size()+"   "+topModel.getResult().getResult().getList().get(0).getTime());

                  try {
                      mRecyclerViewAdapter.addAllData(dataList);
                      mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
//          new Handler().postDelayed(new Runnable() {
//              @Override
//              public void run() {
//              }
//          }, 100);


      }

    }

    @Override
    public void GetcardTopError(Throwable e) {
        Snackbar.make(view,e.getMessage(),Snackbar.LENGTH_SHORT).show();
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }


    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            setRefresh();
            cardTopPresenter.loadTopData("头条","10",0,"747b4744b764f5b6dc5021e72ff9ea4c");
        }



        @Override
        public void onLoadMore() {
            mCount = mCount + 10;

            cardTopPresenter.loadTopData("头条","10",mCount,"747b4744b764f5b6dc5021e72ff9ea4c");
            LogUtil.logConsole("mcount  "+mCount);
        }
    }

    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerViewAdapter.addAllData(setList());
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        }, 1000);
    }

    private void setRefresh() {
        mRecyclerViewAdapter.clearData();
        mCount = 0;

    }
    private List<TopModel.ResultBeanX.ResultBean.ListBean> setList() {
        List<TopModel.ResultBeanX.ResultBean.ListBean> dataList = new ArrayList<>();

        return dataList;

    }
}
