package com.blue339.lolive.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.blue339.lolive.R;

import java.util.List;

/**
 * Created by Blue on 2016/5/22.
 */
public class WebViewActivity extends Activity {

    private WebView mWebView;
    private ProgressDialog mProgressDialog;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mWebView=(WebView)findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.getSettings().setPluginsEnabled(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.setBackgroundColor(0);
        String swfUrl = "http://material.mtty.com/201605/03/iayOcK.swf";

        try {
            Thread.sleep(500);// 主线程暂停下，否则容易白屏，原因未知
        } catch (InterruptedException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

        mProgressDialog=ProgressDialog.show(this, "请稍等...", "加载flash中...", true);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgressDialog.dismiss();
                        }
                    }, 500);
                }
            }
        });
        if(checkinstallornotadobeflashapk()){
            mWebView.loadUrl(swfUrl);
        }
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
