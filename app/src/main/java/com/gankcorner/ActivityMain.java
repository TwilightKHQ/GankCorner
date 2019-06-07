package com.gankcorner;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private CustomViewPager mViewPager;
    private BottomNavigationView bottomNavigationView;
    private List<Fragment> fragmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mViewPager = (CustomViewPager) findViewById(R.id.view_pager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentList.add(new WanAndroidPage());
        fragmentList.add(new Fragment_Two());
        fragmentList.add(new Fragment_Four());
        fragmentList.add(new GankIOPage());
        AdapterBottomFragment adapterBottomFragment = new AdapterBottomFragment(getSupportFragmentManager(),
                this, fragmentList);
        mViewPager.setAdapter(adapterBottomFragment);
        mViewPager.setCanScroll(false);

        disableShiftMode();

        //导航栏点击事件和ViewPager滑动事件,让两个控件相互关联
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //这里设置为：当点击到某子项，ViewPager就滑动到对应位置
                switch (item.getItemId()) {
                    case R.id.wan_android:
                        mViewPager.setCurrentItem(0);
                        return true;
                    case R.id.page_2:
                        mViewPager.setCurrentItem(1);
                        return true;
                    case R.id.page_3:
                        mViewPager.setCurrentItem(2);
                        return true;
                    case R.id.gank_io:
                        mViewPager.setCurrentItem(3);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    //解决底部导航栏大小不均的问题
    @SuppressLint("RestrictedApi")
    private void disableShiftMode() {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
