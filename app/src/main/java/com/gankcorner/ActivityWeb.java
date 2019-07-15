package com.gankcorner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.gankcorner.Utils.BottomDialogFragment;

import static android.view.KeyEvent.KEYCODE_BACK;

public class ActivityWeb extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "========zzq";

    private ProgressBar progressBar;
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

        WebSettings mWebSettings = webView.getSettings();
        /* 设置支持Js,必须设置的,不然网页基本上不能看 */
        mWebSettings.setJavaScriptEnabled(true);
        /* 设置WebView是否可以由JavaScript自动打开窗口，默认为false，通常与JavaScript的window.open()配合使用。*/
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        /* 设置缓存模式,我这里使用的默认,不做多讲解 */
        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        /* 设置为true表示支持使用js打开新的窗口 */
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        /* 大部分网页需要自己保存一些数据,这个时候就的设置下面这个属性 */
        mWebSettings.setDomStorageEnabled(true);
        /* 设置为使用webview推荐的窗口 */
        mWebSettings.setUseWideViewPort(true);
        /* 设置网页自适应屏幕大小 ---这个属性应该是跟上面一个属性一起用 */
        mWebSettings.setLoadWithOverviewMode(true);
        /* HTML5的地理位置服务,设置为true,启用地理定位 */
        mWebSettings.setGeolocationEnabled(true);
        /* 设置是否允许WebView使用缩放的功能*/
        mWebSettings.setBuiltInZoomControls(true);
        /* 提高网页渲染的优先级 */
        mWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        /* 设置显示水平滚动条,就是网页右边的滚动条.我这里设置的不显示 */
        webView.setHorizontalScrollBarEnabled(false);
        /* 指定垂直滚动条是否有叠加样式 */
        webView.setVerticalScrollbarOverlay(true);
        /* 设置滚动条的样式 */
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }

        });

        /*重写WebViewClient可以监听网页的跳转和资源加载等等... */
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("scheme:") || url.startsWith("scheme:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(ActivityWeb.this, "加载失败", Toast.LENGTH_SHORT).show();
                super.onReceivedError(view, errorCode, description, failingUrl);
            }


        });
        webView.loadUrl(url);

    }


    private void initView() {
        //加载顶部的ToolBar//动态设置ToolBar标题
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

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

        progressBar = (ProgressBar) findViewById(R.id.web_progress);

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
