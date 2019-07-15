package com.gankcorner;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.gankcorner.Adapter.AdapterBottomFragment;
import com.gankcorner.Fragment.Fragment_Four;
import com.gankcorner.GankIO.GankIOPage;
import com.gankcorner.Fragment.Fragment_Two;
import com.gankcorner.Utils.AppUtil;
import com.gankcorner.Utils.BaseFragment;
import com.gankcorner.Utils.CustomViewPager;
import com.gankcorner.Utils.DrawableTextView;
import com.gankcorner.WanAndroid.WanAndroidPage;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityMain extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "=======zzq";

    private DrawerLayout mDrawerLayout;
    private TabLayout mBottomTab;
//    private CustomViewPager mViewPager;
    private ViewPager mViewPager;
    private List<Fragment> fragmentList = new ArrayList<>();

    String[] titles = new String[]{"首页", "流量", "社区", "干货"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeStatusBar();

        initView();

        initOnClickEvents();
    }



    private void changeStatusBar() {
        // 状态栏透明， 使得沉浸式状态栏有效
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //状态栏字体设置为黑色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void initOnClickEvents() {
        CircleImageView mHeadPic = (CircleImageView) findViewById(R.id.head_pic);
        mHeadPic.setOnClickListener(this);
        TextView mHome = (TextView) findViewById(R.id.home_page);
        mHome.setOnClickListener(this);
        TextView mQuestion = (TextView) findViewById(R.id.question);
        mQuestion.setOnClickListener(this);
        TextView mAboutMe = (TextView) findViewById(R.id.about_me);
        mAboutMe.setOnClickListener(this);
        TextView mStar = (TextView) findViewById(R.id.star);
        mStar.setOnClickListener(this);
        TextView mLocation = (TextView) findViewById(R.id.location);
        mLocation.setOnClickListener(this);
        TextView mScanCode = (TextView) findViewById(R.id.scan);
        mScanCode.setOnClickListener(this);
        DrawableTextView mSetting = (DrawableTextView) findViewById(R.id.settings);
        mSetting.setOnClickListener(this);
        DrawableTextView mExit = (DrawableTextView) findViewById(R.id.exit);
        mExit.setOnClickListener(this);
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mBottomTab = (TabLayout) findViewById(R.id.bottom_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        fragmentList.add(new WanAndroidPage());
        fragmentList.add(new Fragment_Two());
        fragmentList.add(new Fragment_Four());
        fragmentList.add(new GankIOPage());
        AdapterBottomFragment adapterBottomFragment = new
                AdapterBottomFragment(getSupportFragmentManager(),
                this, fragmentList, titles);
        mViewPager.setAdapter(adapterBottomFragment);
        mViewPager.setCurrentItem(0);
//        mViewPager.setCanScroll(false);

        //绑定
        mBottomTab.setupWithViewPager(mViewPager);
        setFragmentLabel();
    }

    private void setFragmentLabel() {
        for (int i = 0; i < mBottomTab.getTabCount(); i++) {
            TabLayout.Tab tab = mBottomTab.getTabAt(i);
            Drawable d = null;
            switch (i) {
                case 0:
                    d = getResources().getDrawable(R.mipmap.home);
                    break;
                case 1:
                    d = getResources().getDrawable(R.mipmap.navigation);
                    break;
                case 2:
                    d = getResources().getDrawable(R.mipmap.know);
                    break;
                case 3:
                    d = getResources().getDrawable(R.mipmap.project);
                    break;
            }
            tab.setIcon(d);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_pic:
                mDrawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.home_page:
                mDrawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.question:
                Toast.makeText(this, "问题反馈", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_me:
                Toast.makeText(this, "关于我", Toast.LENGTH_SHORT).show();
                break;
            case R.id.star:
                Toast.makeText(this, "我的收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.location:
                Toast.makeText(this, "定位", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scan:
                Toast.makeText(this, "扫码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                //退出应用
                Intent intent = new Intent(AppUtil.getContext(), ActivityMain.class);
                intent.putExtra("Exit_TAG", "SingleTASK");
                startActivity(intent);
                break;

        }
    }

    //退出App
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String tag = intent.getStringExtra("Exit_TAG");
        if (tag != null && !TextUtils.isEmpty(tag)) {
            if ("SingleTASK".equals(tag)) {
                //退出程序
                finish();
            }
        }
    }

}
