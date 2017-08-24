package com.android.qzs.entertainmenttop.mvp.mvp_photo.presenter;

import com.android.qzs.entertainmenttop.base.BasePresenter;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.view.CardTopView;
import com.android.qzs.entertainmenttop.mvp.mvp_photo.model.PhotoModel;
import com.android.qzs.entertainmenttop.mvp.mvp_photo.view.PhotoView;
import com.android.qzs.entertainmenttop.retrofit.ApiClient;
import com.android.qzs.entertainmenttop.retrofit.ApiService;
import com.android.qzs.entertainmenttop.retrofit.HostType;
import com.android.qzs.entertainmenttop.utils.LogUtil;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/8/23.
 */

public class PhotoPresenter extends BasePresenter<PhotoView> {

    public PhotoPresenter(PhotoView view) {
        attachView(view);
        apiStores = ApiClient.retrofit(HostType.GIRL_PHOTO).create(ApiService.class);
    }

    public void loadPhotoData(int page, int number, String type1,String type2) {

  addSubscription(apiStores.LoadPhoto_RxJava(page, number, type1,type2), new Subscriber<PhotoModel>() {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        LogUtil.logConsole("请求失败"+e);
        mvpView.GetPhotoError(e);
    }

    @Override
    public void onNext(PhotoModel photoModel) {
        LogUtil.logConsole("请求成功"+photoModel);
        mvpView.GetPhotoSuccess(photoModel);
    }
});

    }
}