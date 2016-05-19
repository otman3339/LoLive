package com.blue339.lolive.activity;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.blue339.lolive.R;

public class MainActivity extends BaseActivity {

    private long mExitTime = 0;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private TextView tvTvLive, tvVideo, tvInformation, tvShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    protected void initView() {
        initBaseView();
        mToolbar.setTitle(R.string.game);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTvLive = (TextView) findViewById(R.id.tv_tv_live);
        Drawable[] tvTvLiveDrawables = tvTvLive.getCompoundDrawables();
        tvTvLive.setCompoundDrawables(tintDrawable(tvTvLiveDrawables[0], 0.8f), null,
                tintDrawable(tvTvLiveDrawables[2], 1f), null);

        tvVideo = (TextView) findViewById(R.id.tv_video);
        Drawable[] tvVideoDrawables = tvVideo.getCompoundDrawables();
        tvVideo.setCompoundDrawables(tintDrawable(tvVideoDrawables[0], 0.8f), null,
                tintDrawable(tvVideoDrawables[2], 1f), null);

        tvInformation = (TextView) findViewById(R.id.tv_information);
        Drawable[] tvInformationDrawables = tvInformation.getCompoundDrawables();
        tvInformation.setCompoundDrawables(tintDrawable(tvInformationDrawables[0], 0.8f), null,
                tintDrawable(tvInformationDrawables[2], 1f), null);

        tvShop = (TextView) findViewById(R.id.tv_shop);
        Drawable[] tvShopDrawables = tvShop.getCompoundDrawables();
        tvShop.setCompoundDrawables(tintDrawable(tvShopDrawables[0], 0.8f), null,
                tintDrawable(tvShopDrawables[2], 1f), null);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.channel,
                R.string.follow);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

    }

    private Drawable tintDrawable(Drawable drawable, float scale) {

        Drawable warpDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintMode(warpDrawable, PorterDuff.Mode.SRC_IN);
        DrawableCompat.setTintList(warpDrawable,
                ColorStateList.valueOf(getResources().getColor(R.color.gray)));

        warpDrawable.setBounds(0, 0, (int) (warpDrawable.getMinimumWidth() * scale),
                (int) (warpDrawable.getMinimumHeight() * scale));

        return warpDrawable;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, getString(R.string.tip_exit_app), Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}
