package com.android.qzs.entertainmenttop.mvp.mvp_cardtop.presenter;

import android.content.Context;

import com.android.qzs.entertainmenttop.base.BasePresenter;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.model.TopModel;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.view.CardTopView;
import com.android.qzs.entertainmenttop.retrofit.ApiClient;
import com.android.qzs.entertainmenttop.retrofit.ApiService;
import com.android.qzs.entertainmenttop.retrofit.HostType;
import com.android.qzs.entertainmenttop.retrofit.ProgressSubscriber;
import com.android.qzs.entertainmenttop.retrofit.SubscriberOnNextListener;
import com.android.qzs.entertainmenttop.utils.LogUtil;
import com.android.qzs.entertainmenttop.utils.RetrofitUtil;

import rx.Subscriber;

/**
 * Created by qzs on 2017/8/17.
 */

public class CardTopPresenter extends BasePresenter<CardTopView> {

    public CardTopPresenter(CardTopView view) {
        attachView(view);
        apiStores = ApiClient.retrofit(HostType.New_Top).create(ApiService.class);
    }

    public void loadTopData(String type, String num, int start, String key) {

        addSubscription(apiStores.LoadTopData_RxJava(type, num, start, key), new Subscriber<TopModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.logConsole("请求失败");
                mvpView.GetcardTopError(e);
            }

            @Override
            public void onNext(TopModel topModel) {
                LogUtil.logConsole("请求成功");
                mvpView.GetcardTopSuccess(topModel);
            }
        });

    }

}
