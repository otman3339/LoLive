package com.blue339.lolive.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.blue339.lolive.R;

import java.util.List;

/**
 * Created by Blue on 2016/5/22.
 */
public class WebViewActivity extends Activity {

    private WebView mWebView;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mWebView=(WebView)findViewById(R.id.webview);
        String swfUrl = getIntent().getStringExtra("swfUrl");

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
