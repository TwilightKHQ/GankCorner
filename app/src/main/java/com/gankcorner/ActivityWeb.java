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

import com.gankcorner.Utils.BottomDialogFragment;

import static android.view.KeyEvent.KEYCODE_BACK;

public class ActivityWeb extends AppCompatActivity implements View.OnClickListener {

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

//        closeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //返回上一个Activity
//                ActivityWeb.this.finish();
//            }
//        });

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

        webTitle = (TextView) findViewById(R.id.web_title);
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
                if (webView.canGoBack()){ //回退至上一个页面
                    webView.goBack();
                } else { //结束当前Activity
                    ActivityWeb.this.finish();
                }
                break;
            case R.id.web_exit:
                //结束当前Activity
                Toast.makeText(ActivityWeb.this, "结束", Toast.LENGTH_SHORT).show();
                ActivityWeb.this.finish();
                break;
            case R.id.web_share:
                Toast.makeText(ActivityWeb.this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.more_item:
                BottomDialogFragment bottomDialogFragment = new BottomDialogFragment();
                bottomDialogFragment.show(ActivityWeb.this.getFragmentManager(), "bottomDialogFragment");
                break;
        }
    }
}
