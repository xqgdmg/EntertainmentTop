package com.android.qzs.entertainmenttop.mvp.mvp_cardtop.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.android.qzs.entertainmenttop.R;
import com.android.qzs.entertainmenttop.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by qzs on 2017/8/22.
 */

public class CardTopDetailActivity extends BaseActivity {


    @InjectView(R.id.iv_background)
    ImageView iv_background;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @InjectView(R.id.app_bar)
    AppBarLayout appBar;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.webView)
    WebView webView;
    private String imgurl;
    private String weburl;

    @Override
    protected void initVariables() {
        Intent intent = getIntent();
        imgurl = intent.getStringExtra("top_pic");
        weburl = intent.getStringExtra("top_weburl");

    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_cardtop_details);
        ButterKnife.inject(this);

    }

    @Override
    protected void loadData() {
        //toolbar的设置
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        Glide.with(this).load(imgurl).asBitmap()
                .placeholder(R.mipmap.android)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .error(R.mipmap.android)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_background);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.getSettings().setJavaScriptEnabled(false);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);
     //   webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.getSettings().setDefaultFontSize(111);
        webView.loadUrl(weburl);



    }

@OnClick(R.id.fab)
public void onClick() {
   fab_share();
}

    private void fab_share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "娱乐TOP");
        intent.putExtra(Intent.EXTRA_TEXT, weburl);
        startActivity(Intent.createChooser(intent, getTitle()));
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }



}
