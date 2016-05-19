package com.blue339.lolive.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.blue339.lolive.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {

    private long mExitTime = 0;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private TextView tvTvLive, tvVideo, tvInformation, tvShop;
    private ScrollView svNavigation;
    private LinearLayout lvAdviceChannel;

    private View.OnClickListener clickListener;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickListener = new ClickListener();
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                handleBaseMessage(msg);
                return false;
            }
        });

        initView();
    }

    private void handleBaseMessage(Message msg) {

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

        lvAdviceChannel = (LinearLayout) findViewById(R.id.lv_advice_channel);

        List<Map<String, Object>> data = new ArrayList<>();
        for(int i=0; i<5; i++) {
            Map<String, Object> map = new HashMap<>();
            data.add(map);
            View view = LayoutInflater.from(this).inflate(R.layout.listview_item_navigation, null);

            TextView title = (TextView) view.findViewById(R.id.tv_title);
            Drawable[] drawables = title.getCompoundDrawables();
            title.setCompoundDrawables(null, null, tintDrawable(drawables[2], 1f), null);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            lvAdviceChannel.addView(view);
        }
        /*SimpleAdapter adapter = new SimpleAdapter(this, data,
                R.layout.listview_item_navigation, new String[]{}, new int[]{});
        lvAdviceChannel.setAdapter(adapter);
        Utility.setListViewHeightBasedOnChildren(lvAdviceChannel);*/


        svNavigation = (ScrollView) findViewById(R.id.sv_navigation);
        svNavigation.smoothScrollTo(0, 0);


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
