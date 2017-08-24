package com.android.qzs.entertainmenttop;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.android.qzs.entertainmenttop.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/8/24.
 */

public class AboutActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_about);
        ButterKnife.inject(this);
        initToobar();
    }

    @Override
    protected void loadData() {

    }
    private void initToobar() {
        //toolbar的设置
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
            ab.setTitle("关于作者");
            ab.setDisplayHomeAsUpEnabled(true);
        }
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
