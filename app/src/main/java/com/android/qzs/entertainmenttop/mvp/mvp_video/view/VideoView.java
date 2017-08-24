package com.android.qzs.entertainmenttop.mvp.mvp_video.view;

import com.android.qzs.entertainmenttop.mvp.mvp_video.model.VideoModel;

import java.util.List;

/**
 * Created by qzs on 2017/8/20.
 */

public interface VideoView {
    void GetVideoSuccess(List<VideoModel> videoModels);
}
