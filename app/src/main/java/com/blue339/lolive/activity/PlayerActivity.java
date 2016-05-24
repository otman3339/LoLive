package com.blue339.lolive.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.blue339.lolive.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by Blue on 2016/5/22.
 */
public class PlayerActivity extends Activity {

//    private WebView mWebView;
    private VideoView videoView;
    private ProgressDialog mProgressDialog;
    private String urlStream;
    private RelativeLayout controlBarTop, controlBarBottom;

    private ImageView start, pause, back;
    private SeekBar seekBar;

    private View transparent;

    private long time = 0;
    private long videoTime = 0;
    private Timer mTimer;

    private static final int SHOW_CONTROL_BAR = 0x001;
    private static final int HIDE_CONTROL_BAR = 0x002;
    private Handler mHandler;
    /**
     * control bar是否显示
     */
    private boolean isShow = false;

    private Animation showAnimTop, showAnimBottom, hideAnimTop, hideAnimBottom;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
            return;*/
        setContentView(R.layout.activity_player);
        mTimer = new Timer();

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case SHOW_CONTROL_BAR:
                        showControlBar();
                        break;
                    case HIDE_CONTROL_BAR:
                        hideControlBar();
                        break;
                }
                return true;
            }
        });

        videoView = (VideoView) findViewById(R.id.surface_view);

        //urlStream = "http://125.88.150.67:525/12560616_2299629432_10057.m3u8?uuid=a6f8488bbf2c4620a4f88ea1ba08a366&org=huya&m=b68cb5aca365b3688c51536ea09a334c&r=1207063681&v=1&t=1464013581&uid=0";
        urlStream = "http://14.17.89.15/youku/657148A4BBC3574C8C92E39FB/030008060056C1DF4921F12F960AD6A54A9A00-FF04-B5DA-48E0-5B8A3A7C875D.mp4";

        showAnimBottom = AnimationUtils.loadAnimation(this, R.anim.move_in_from_bottom);
        showAnimTop = AnimationUtils.loadAnimation(this, R.anim.move_in_from_top);
        hideAnimBottom = AnimationUtils.loadAnimation(this, R.anim.move_out_from_bottom);
        hideAnimTop = AnimationUtils.loadAnimation(this, R.anim.move_out_from_top);
        controlBarTop = (RelativeLayout) findViewById(R.id.control_bar_top);
        controlBarBottom = (RelativeLayout) findViewById(R.id.control_bar_bottom);
        transparent = findViewById(R.id.transparent);
        transparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = System.currentTimeMillis();
                if(isShow) return;
                mHandler.sendEmptyMessage(SHOW_CONTROL_BAR);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        long now = System.currentTimeMillis();
                        if(now - time > 3000) {
                            mHandler.sendEmptyMessage(HIDE_CONTROL_BAR);
                            this.cancel();
                        }
                    }
                }, 0, 100);
            }
        });


        start = (ImageView) findViewById(R.id.start);
        pause = (ImageView) findViewById(R.id.pause);
        back = (ImageView) findViewById(R.id.back);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.start();
                start.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.pause();
                start.setVisibility(View.VISIBLE);
                pause.setVisibility(View.GONE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.stopPlayback();
                finish();
            }
        });

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //videoView.seekTo(600000);
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                videoView.setVideoURI(Uri.parse(urlStream));
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoTime = videoView.getDuration();
            }
        });
        videoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                //seekBar.setSecondaryProgress(percent);
            }
        });

    }

    private void showControlBar() {
        controlBarTop.setVisibility(View.VISIBLE);
        controlBarBottom.setVisibility(View.VISIBLE);
        controlBarTop.setAnimation(showAnimTop);
        controlBarBottom.setAnimation(showAnimBottom);
        isShow = true;
    }

    private void hideControlBar() {
        controlBarTop.setVisibility(View.GONE);
        controlBarBottom.setVisibility(View.GONE);
        controlBarTop.setAnimation(hideAnimTop);
        controlBarBottom.setAnimation(hideAnimBottom);
        isShow = false;
    }

    private boolean checkinstallornotadobeflashapk() {
        PackageManager pm = getPackageManager();
        List<PackageInfo> infoList = pm
                .getInstalledPackages(PackageManager.GET_SERVICES);
        for (PackageInfo info : infoList) {
            if ("com.adobe.flashplayer".equals(info.packageName)) {
                return true;
            }
        }
        return false;
    }
}
