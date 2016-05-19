package com.blue339.lolive.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.blue339.lolive.R;
import com.blue339.lolive.fragment.TvLiveFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {

    private long mExitTime = 0;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    //private RadioButton rbs[0], rbs[1], rbs[2], rbs[3];
    private RadioButton[] rbs;
    private ScrollView svNavigation;
    private LinearLayout lvAdviceChannel;

    //private ViewPager mViewPager;
    private View.OnClickListener rbClickListener;
    private Handler mHandler;

    private static final int RB_TV_LIVE = 0;
    private static final int RB_VIDEO = 1;
    private static final int RB_INFORMATION = 2;
    private static final int RB_SHOP = 3;

    private int currentFragmentIndex = 0;

    private Fragment tvLiveFragment, videoFragment, informationFragment, shopFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbClickListener = new RBOnClickListener();
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                handleBaseMessage(msg);
                return false;
            }
        });
        initView();
        setDefaultFragment();
    }

    private void handleBaseMessage(Message msg) {

    }

    protected void initView() {
        initBaseView();
        mToolbar.setTitle(R.string.live);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.channel,
                R.string.follow);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        rbs = new RadioButton[4];
        rbs[0] = (RadioButton) findViewById(R.id.rb_tv_live);
        Drawable[] tvTvLiveDrawables = rbs[0].getCompoundDrawables();
        rbs[0].setCompoundDrawables(tintDrawable(tvTvLiveDrawables[0], 1f), null,
                tintDrawable(tvTvLiveDrawables[2], 1f), null);

        rbs[1] = (RadioButton) findViewById(R.id.rb_video);
        Drawable[] tvVideoDrawables = rbs[1].getCompoundDrawables();
        rbs[1].setCompoundDrawables(tintDrawable(tvVideoDrawables[0], 1f), null,
                tintDrawable(tvVideoDrawables[2], 1f), null);

        rbs[2] = (RadioButton) findViewById(R.id.rb_information);
        Drawable[] tvInformationDrawables = rbs[2].getCompoundDrawables();
        rbs[2].setCompoundDrawables(tintDrawable(tvInformationDrawables[0], 1f), null,
                tintDrawable(tvInformationDrawables[2], 1f), null);

        rbs[3] = (RadioButton) findViewById(R.id.rb_shop);
        Drawable[] tvShopDrawables = rbs[3].getCompoundDrawables();
        rbs[3].setCompoundDrawables(tintDrawable(tvShopDrawables[0], 1f), null,
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

        svNavigation = (ScrollView) findViewById(R.id.sv_navigation);
        svNavigation.smoothScrollTo(0, 0);

        rbs[0].setOnClickListener(rbClickListener);
        rbs[1].setOnClickListener(rbClickListener);
        rbs[2].setOnClickListener(rbClickListener);
        rbs[3].setOnClickListener(rbClickListener);
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        tvLiveFragment = new TvLiveFragment(0);
        transaction.replace(R.id.main_content, tvLiveFragment);
        transaction.commit();
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

    class RBOnClickListener  implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            FragmentManager fm = getFragmentManager();
            // 开启Fragment事务
            FragmentTransaction transaction = fm.beginTransaction();
            int id = v.getId();
            if(id == currentFragmentIndex) return;
            switch (id) {
                case R.id.rb_tv_live:
                    tvLiveFragment = new TvLiveFragment(0);
                    transaction.replace(R.id.main_content, tvLiveFragment);
                    break;
                case R.id.rb_video:
                    videoFragment = new TvLiveFragment(1);
                    transaction.replace(R.id.main_content, videoFragment);
                    break;
                case R.id.rb_information:
                    informationFragment = new TvLiveFragment(2);
                    transaction.replace(R.id.main_content, informationFragment);
                    break;
                case R.id.rb_shop:
                    shopFragment = new TvLiveFragment(3);
                    transaction.replace(R.id.main_content, shopFragment);
                    break;
            }
            currentFragmentIndex = id;
            transaction.commit();
            mToolbar.setTitle(((RadioButton) v).getText().toString());
            mDrawerLayout.closeDrawers();
        }
    }

}
