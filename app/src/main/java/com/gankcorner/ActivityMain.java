package com.gankcorner;

import android.annotation.SuppressLint;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.gankcorner.Adapter.AdapterBottomFragment;
import com.gankcorner.Fragment.Fragment_Four;
import com.gankcorner.GankIO.GankIOPage;
import com.gankcorner.Fragment.Fragment_Two;
import com.gankcorner.Utils.CustomViewPager;
import com.gankcorner.WanAndroid.WanAndroidPage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout mBottomTab;
    private CustomViewPager mViewPager;
    private List<Fragment> fragmentList = new ArrayList<>();

    String[] titles = new String[]{"首页", "流量", "社区", "干货"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mViewPager = (CustomViewPager) findViewById(R.id.view_pager);
        mBottomTab = (TabLayout) findViewById(R.id.bottom_tab);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        for (int i = 0; i < mBottomTab.getTabCount(); i++) {
            TabLayout.Tab tab = mBottomTab.getTabAt(i);
            Drawable d = null;
            switch (i) {
                case 0:
                    d = getResources().getDrawable(R.mipmap.home);
                    break;
                case 1:
                    d = getResources().getDrawable(R.mipmap.know);
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
