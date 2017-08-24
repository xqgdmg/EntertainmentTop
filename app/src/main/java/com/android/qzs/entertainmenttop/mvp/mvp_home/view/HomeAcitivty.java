package com.android.qzs.entertainmenttop.mvp.mvp_home.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.qzs.entertainmenttop.AboutActivity;
import com.android.qzs.entertainmenttop.R;
import com.android.qzs.entertainmenttop.adapter.HomePagerAdapter;
import com.android.qzs.entertainmenttop.base.BaseActivity;
import com.android.qzs.entertainmenttop.mvp.mvp_cardtop.view.CardTopFragment;
import com.android.qzs.entertainmenttop.mvp.mvp_news.view.NewsFragment;
import com.android.qzs.entertainmenttop.mvp.mvp_photo.view.PhotoActivity;
import com.android.qzs.entertainmenttop.mvp.mvp_video.view.VideoFragment;
import com.android.qzs.entertainmenttop.retrofit.ApiClient;
import com.bumptech.glide.load.engine.Resource;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/17.
 */

public class HomeAcitivty extends BaseActivity {
    public static final int DEFAULT_PAGES = 1;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tabs)
    TabLayout tabs;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.nav_view)
    NavigationView navigationView;
    @InjectView(R.id.drawlayout)
    DrawerLayout drawlayout;


    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_home);
        ButterKnife.inject(this);


    }



    @Override
    protected void loadData() {
        //toolbar的设置
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
            ab.setTitle("QZS");
            ab.setDisplayHomeAsUpEnabled(true);
        }
        //侧滑MUNU
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        //viewpager
        if (viewpager != null) {
            setupViewPager(viewpager);
            tabs.setupWithViewPager(viewpager);

//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//适合很多tab
            tabs.setTabMode(TabLayout.MODE_FIXED);//tab均分,适合少的tab
        }

    }

    private void setupViewPager(ViewPager viewpager) {
        HomePagerAdapter adapter=new HomePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(CardTopFragment.newInstance("头条"),"头条");
        adapter.addFragment(VideoFragment.newInstance("视频"),"视频");
        adapter.addFragment(NewsFragment.newInstance("体育"),"体育");
        adapter.addFragment(NewsFragment.newInstance("娱乐"),"娱乐");
        adapter.addFragment(NewsFragment.newInstance("科技"),"科技");
        adapter.addFragment(NewsFragment.newInstance("NBA"),"NBA");
        adapter.addFragment(NewsFragment.newInstance("股票"),"股票");
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(DEFAULT_PAGES);
    }

    @OnClick(R.id.toolbar)
    public void onClick(View view) {
        // 处理onClick事件
     //   Snackbar.make(view, "Here's a Snackbar1111", Snackbar.LENGTH_LONG).show();
    }



//侧滑的item点击
    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.nav_photo:
                                startActivity(new Intent(HomeAcitivty.this, PhotoActivity.class));
                                break;
                            case R.id.nav_video:
                                 Snackbar.make(navigationView,"视频持续更新中....",Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.nav_music:
                                Snackbar.make(navigationView,"音乐持续更新中....",Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.nav_about:
                       startActivity(new Intent(HomeAcitivty.this, AboutActivity.class));
                                break;
                        }
                        drawlayout.closeDrawers();
                        drawlayout.setSelected(true);
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawlayout.openDrawer(GravityCompat.START);
                break;
            case R.id.action_about:
                startActivity(new Intent(HomeAcitivty.this, AboutActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
