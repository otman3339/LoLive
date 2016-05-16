package com.blue339.lolive.view.activity;

import android.os.Bundle;

import com.blue339.lolive.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    protected void initView() {
        initBaseView();
//        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }
}
