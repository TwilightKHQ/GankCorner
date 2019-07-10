package com.gankcorner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.gankcorner.Utils.BottomDialogFragment;

import static android.view.KeyEvent.KEYCODE_BACK;

public class ActivityWeb extends AppCompatActivity implements View.OnClickListener {

    private WebView webView;

    private String desc;
    private String url;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //获取到网页链接信息
        Intent intent = getIntent();
        desc = intent.getStringExtra("page_desc");
        url = intent.getStringExtra("page_url");

        initView();

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        settings.setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        settings.setSupportZoom(true);//是否可以缩放，默认true
        settings.setBuiltInZoomControls(false);//是否显示缩放按钮，默认false
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        settings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        settings.setAppCacheEnabled(true);//是否使用缓存
        settings.setDomStorageEnabled(true);//DOM Storage


        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }


    private void initView() {
        //加载顶部的ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //动态设置ToolBar标题
        getSupportActionBar().setTitle("");
        ActionBar actionBar = getSupportActionBar();

        ImageButton backButton = (ImageButton) findViewById(R.id.web_back);
        backButton.setOnClickListener(this);
        ImageButton closeButton = (ImageButton) findViewById(R.id.web_exit);
        closeButton.setOnClickListener(this);

        TextView webTitle = (TextView) findViewById(R.id.web_title);
        webTitle.setText(desc);

        ImageButton shareButton = (ImageButton) findViewById(R.id.web_share);
        shareButton.setOnClickListener(this);
        ImageButton moreButton = (ImageButton) findViewById(R.id.more_item);
        moreButton.setOnClickListener(this);

        webView = (WebView) findViewById(R.id.web_page);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.web_back:
                if (webView.canGoBack()) { //回退至上一个页面
                    webView.goBack();
                } else { //结束当前Activity
                    ActivityWeb.this.finish();
                }
                break;
            case R.id.web_exit:
                //结束当前Activity
                ActivityWeb.this.finish();
                break;
            case R.id.web_share:
                Toast.makeText(ActivityWeb.this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.more_item:
                BottomDialogFragment bottomDialogFragment = new BottomDialogFragment();
                bottomDialogFragment.show(ActivityWeb.this.getSupportFragmentManager(), "bottomDialogFragment");
                break;
        }
    }

}
