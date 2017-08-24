package com.android.qzs.entertainmenttop.mvp.mvp_photo.view;

import android.content.Intent;
import android.os.Bundle;

import com.android.qzs.entertainmenttop.R;
import com.android.qzs.entertainmenttop.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.*;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/8/23.
 */

public class PhotoDetailsActivity extends BaseActivity {
    @InjectView(R.id.photo_view)
    com.github.chrisbanes.photoview.PhotoView photoView;

    private String  img_url;

    @Override
    protected void initVariables() {
        Intent intent=getIntent();
if (intent.getStringExtra("img_url")!=null){
    img_url=intent.getStringExtra("img_url");
}

    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_photodetails);
        ButterKnife.inject(this);

        Glide.with(this)
                .load(img_url)
                .placeholder(R.mipmap.girls)
                .into(photoView);
    }

    @Override
    protected void loadData() {

    }


}
