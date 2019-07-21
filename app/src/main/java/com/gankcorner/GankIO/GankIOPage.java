package com.gankcorner.GankIO;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gankcorner.GankIO.GankioFragment.FragmentAndroid;
import com.gankcorner.GankIO.GankioFragment.FragmentiOS;
import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;
import com.gankcorner.GankIO.WanAndroidFragment.FragmentArticle;
import com.gankcorner.GankIO.WanAndroidFragment.FragmentKnowledge;
import com.gankcorner.GankIO.WanAndroidFragment.FragmentNavigation;

import java.util.ArrayList;
import java.util.List;

public class GankIOPage extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private List<String> titleList ;
    private List<Fragment> fragmentList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_gank, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab().setText("玩安卓"));
        tabLayout.addTab(tabLayout.newTab().setText("知识体系"));
        tabLayout.addTab(tabLayout.newTab().setText("导航数据"));

        tabLayout.addTab(tabLayout.newTab().setText("A"));
        tabLayout.addTab(tabLayout.newTab().setText("iOS"));

        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        fragmentList.add(new FragmentArticle());
        titleList.add("玩安卓");
        fragmentList.add(new FragmentKnowledge());
        titleList.add("知识体系");
        fragmentList.add(new FragmentNavigation());
        titleList.add("导航数据");
        fragmentList.add(new FragmentAndroid());
        titleList.add("A");
        fragmentList.add(new FragmentiOS());
        titleList.add("iOS");

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
        });
        //将TabLayout与ViewPager联系起来
        tabLayout.setupWithViewPager(mViewPager);
    }
}
