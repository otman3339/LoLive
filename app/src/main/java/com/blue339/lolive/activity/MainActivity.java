package com.blue339.lolive.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blue339.lolive.R;

public class MainActivity extends BaseActivity {

    private long mExitTime = 0;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private TextView tvTvLive, tvVideo, tvInformation, tvShop;

    private View.OnClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickListener = new ClickListener();

        initView();
    }

    protected void initView() {
        initBaseView();
        mToolbar.setTitle(R.string.game);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTvLive = (TextView) findViewById(R.id.tv_tv_live);
        Drawable[] tvTvLiveDrawables = tvTvLive.getCompoundDrawables();
        tvTvLive.setCompoundDrawables(tintDrawable(tvTvLiveDrawables[0], 1f), null,
                tintDrawable(tvTvLiveDrawables[2], 1f), null);

        tvVideo = (TextView) findViewById(R.id.tv_video);
        Drawable[] tvVideoDrawables = tvVideo.getCompoundDrawables();
        tvVideo.setCompoundDrawables(tintDrawable(tvVideoDrawables[0], 1f), null,
                tintDrawable(tvVideoDrawables[2], 1f), null);

        tvInformation = (TextView) findViewById(R.id.tv_information);
        Drawable[] tvInformationDrawables = tvInformation.getCompoundDrawables();
        tvInformation.setCompoundDrawables(tintDrawable(tvInformationDrawables[0], 1f), null,
                tintDrawable(tvInformationDrawables[2], 1f), null);

        tvShop = (TextView) findViewById(R.id.tv_shop);
        Drawable[] tvShopDrawables = tvShop.getCompoundDrawables();
        tvShop.setCompoundDrawables(tintDrawable(tvShopDrawables[0], 1f), null,
                tintDrawable(tvShopDrawables[2], 1f), null);

        tvTvLive.setOnClickListener(clickListener);
        tvVideo.setOnClickListener(clickListener);
        tvInformation.setOnClickListener(clickListener);
        tvShop.setOnClickListener(clickListener);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.channel,
                R.string.follow);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

    }

    private Drawable tintDrawable(Drawable drawable, float scale) {

        Drawable warpDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintMode(warpDrawable, PorterDuff.Mode.SRC_IN);
        DrawableCompat.setTintList(warpDrawable,getResources().getColorStateList(R.color.default_tint_selector));

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

    class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.tv_tv_live:
                    break;
                case R.id.tv_video:
                    break;
                case R.id.tv_information:
                    break;
                case R.id.tv_shop:
                    break;
            }
        }
    }
}
