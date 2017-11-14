package com.android.qzs.entertainmenttop.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/4/5.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariables();
        initView();
        loadData();
    }
    /**
     * 初始化变量
     **/
    protected abstract   void   initVariables();

    /**
     * 初始化界面
     **/
    protected  abstract    void   initView();

    /**
     * 加载数据
     **/
    protected  abstract void   loadData();

}
