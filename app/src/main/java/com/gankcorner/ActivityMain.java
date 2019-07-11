package com.gankcorner;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.gankcorner.Adapter.AdapterBottomFragment;
import com.gankcorner.Fragment.Fragment_Four;
import com.gankcorner.GankIO.GankIOPage;
import com.gankcorner.Fragment.Fragment_Two;
import com.gankcorner.Utils.CustomViewPager;
import com.gankcorner.WanAndroid.WanAndroidPage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityMain extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private TabLayout mBottomTab;
    private CustomViewPager mViewPager;
    private CircleImageView mHeadPic;
    private List<Fragment> fragmentList = new ArrayList<>();

    String[] titles = new String[]{"首页", "流量", "社区", "干货"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 状态栏透明， 使得沉浸式状态栏有效
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //状态栏字体设置为黑色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        initView();

    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mViewPager = (CustomViewPager) findViewById(R.id.view_pager);
        mBottomTab = (TabLayout) findViewById(R.id.bottom_tab);
        mHeadPic = (CircleImageView) findViewById(R.id.head_pic);

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
        mViewPager.setCanScroll(false);

        //绑定
        mBottomTab.setupWithViewPager(mViewPager);
        setFragmentLabel();

        mHeadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
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

}
