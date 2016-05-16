package com.blue339.lolive;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by zeminwang on 16/5/11.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
