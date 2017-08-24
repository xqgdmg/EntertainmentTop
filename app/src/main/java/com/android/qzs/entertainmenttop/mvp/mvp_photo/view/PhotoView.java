package com.android.qzs.entertainmenttop.mvp.mvp_photo.view;

import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.model.TopModel;
import com.android.qzs.entertainmenttop.mvp.mvp_photo.model.PhotoModel;

/**
 * Created by qzs on 2017/8/23.
 */

public interface PhotoView {
    void GetPhotoSuccess(PhotoModel photoModel);
    void GetPhotoError(Throwable e);
}
