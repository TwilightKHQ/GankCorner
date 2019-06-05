package com.gankcorner;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.KeyEvent.KEYCODE_BACK;

public class ActivityWeb extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton closeButton;
    private TextView webTitle;
    private WebView webView;

    private String desc;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //获取到网页链接信息
        Intent intent = getIntent();
        desc = intent.getStringExtra("page_desc");
        url = intent.getStringExtra("page_url");

        initView();

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回上一个Activity
                ActivityWeb.this.finish();
            }
        });

    }

    private void initView() {
        //加载顶部的ToolBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //动态设置ToolBar标题
        getSupportActionBar().setTitle("");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        closeButton = (ImageButton) findViewById(R.id.web_exit);

        webTitle = (TextView) findViewById(R.id.web_title);
        webTitle.setText(desc);

        webView = (WebView) findViewById(R.id.web_page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.web_share:
                Toast.makeText(ActivityWeb.this, "分享", Toast.LENGTH_SHORT).show();
            case android.R.id.home:
                if (webView.canGoBack()){ //回退至上一个页面
                    webView.goBack();
                    return true;
                } else { //返回上一个Activity
                    ActivityWeb.this.finish();
                }
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
