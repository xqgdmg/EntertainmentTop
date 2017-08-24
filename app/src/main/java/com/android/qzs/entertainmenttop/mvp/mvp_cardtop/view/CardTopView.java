package com.android.qzs.entertainmenttop.mvp.mvp_cardtop.view;

import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.model.TopModel;

/**
 * Created by qzs on 2017/8/17.
 */

public interface CardTopView {

    void GetcardTopSuccess(TopModel topModel);
    void GetcardTopError(Throwable e);
}
