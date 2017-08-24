package com.android.qzs.entertainmenttop.mvp.mvp_video.presenter;

import com.android.qzs.entertainmenttop.base.BasePresenter;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.view.CardTopView;
import com.android.qzs.entertainmenttop.mvp.mvp_video.view.VideoView;
import com.android.qzs.entertainmenttop.utils.DataUtil;

/**
 * Created by qzs on 2017/8/20.
 */

public class VideoPresenter extends BasePresenter<VideoView> {

    public VideoPresenter(VideoView view) {
        attachView(view);
    }
    public void loadVideoData(){

        mvpView.GetVideoSuccess( DataUtil.getVideoListData());
    }


}
