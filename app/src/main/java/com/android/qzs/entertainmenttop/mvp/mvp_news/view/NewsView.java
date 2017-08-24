package com.android.qzs.entertainmenttop.mvp.mvp_news.view;

import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.model.TopModel;
import com.android.qzs.entertainmenttop.mvp.mvp_news.model.NewsModel;

/**
 * Created by Administrator on 2017/8/21.
 */

public interface NewsView {

    void GetnewsSuccess(NewsModel newsModel);
    void GetnewsError(Throwable e);
}
