package com.gankcorner.WanAndroid;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;
import com.gankcorner.WanAndroid.WanAndroidFragment.FragmentHome;
import com.gankcorner.WanAndroid.WanAndroidFragment.FragmentKnowledge;
import com.gankcorner.WanAndroid.WanAndroidFragment.FragmentNavigation;


import java.util.ArrayList;
import java.util.List;

public class WanAndroidPage extends BaseFragment {

    private String TAG = "=======zzq";

    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private List<String> titleList;
    private List<Fragment> fragmentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_gank, container, false);

        initView(view);
        boolean isVisibleToUser = WanAndroidPage.this.isResumed() && WanAndroidPage.this.getUserVisibleHint();
        Log.i(TAG, "WanAndroidPage_isVisibleToUser: " + isVisibleToUser);
        Log.i(TAG, "WanAndroidPage_isVisible: " + isVisible());
        Log.i(TAG, "WanAndroidPage_getUserVisibleHint: " + getUserVisibleHint());

        return view;
    }

    private void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        tabLayout.addTab(tabLayout.newTab().setText("玩安卓"));
        tabLayout.addTab(tabLayout.newTab().setText("知识体系"));
        tabLayout.addTab(tabLayout.newTab().setText("导航数据"));
//        tabLayout.addTab(tabLayout.newTab().setText("测试"));

        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        fragmentList.add(new FragmentHome());
        titleList.add("玩安卓");
        fragmentList.add(new FragmentKnowledge());
        titleList.add("知识体系");
        fragmentList.add(new FragmentNavigation());
        titleList.add("导航数据");
//        fragmentList.add(new FragmentNavigation());
//        titleList.add("测试");

        //在Fragment当中
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

            }
        });

        //将TabLayout与ViewPager联系起来
        tabLayout.setupWithViewPager(mViewPager);

    }

}
