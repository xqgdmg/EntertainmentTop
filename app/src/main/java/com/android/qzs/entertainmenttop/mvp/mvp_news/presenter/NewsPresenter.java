package com.android.qzs.entertainmenttop.mvp.mvp_news.presenter;

import com.android.qzs.entertainmenttop.base.BasePresenter;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.model.TopModel;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.view.CardTopView;
import com.android.qzs.entertainmenttop.mvp.mvp_news.model.NewsModel;
import com.android.qzs.entertainmenttop.mvp.mvp_news.view.NewsView;
import com.android.qzs.entertainmenttop.retrofit.ApiClient;
import com.android.qzs.entertainmenttop.retrofit.ApiService;
import com.android.qzs.entertainmenttop.retrofit.HostType;
import com.android.qzs.entertainmenttop.utils.LogUtil;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/8/21.
 */

public class NewsPresenter extends BasePresenter<NewsView> {

    public NewsPresenter(NewsView view) {
        attachView(view);
        apiStores = ApiClient.retrofit(HostType.New_Top).create(ApiService.class);
    }

    public void loadNewData( String type, String num, int start, String key) {

        addSubscription(apiStores.LoadNewData_RxJava(type, num, start, key), new Subscriber<NewsModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.logConsole("请求失败");
                mvpView.GetnewsError(e);
            }

            @Override
            public void onNext(NewsModel newsModel) {
                LogUtil.logConsole("请求成功");
                mvpView.GetnewsSuccess(newsModel);
            }


        });

    }
}
