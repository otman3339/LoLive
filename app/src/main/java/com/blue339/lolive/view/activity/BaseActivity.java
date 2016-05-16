package com.blue339.lolive.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.blue339.lolive.R;

/**
 * Created by zeminwang on 16/5/11.
 */
public class BaseActivity extends AppCompatActivity{

    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initBaseView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

}
