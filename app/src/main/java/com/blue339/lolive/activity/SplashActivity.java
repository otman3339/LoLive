package com.blue339.lolive.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.blue339.lolive.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zeminwang on 16/5/16.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, 1500);
    }
}
