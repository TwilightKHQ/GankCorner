package com.gankcorner.WanAndroid;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gankcorner.R;
import com.gankcorner.WanAndroid.WanAndroidFragment.FragmentHome;


import java.util.ArrayList;
import java.util.List;

public class WanAndroidPage extends Fragment {

    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private List<String> titleList ;
    private List<Fragment> fragmentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_gank, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        tabLayout.addTab(tabLayout.newTab().setText("玩Android"));
        tabLayout.addTab(tabLayout.newTab().setText("知识体系"));
        tabLayout.addTab(tabLayout.newTab().setText("导航数据"));

        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        fragmentList.add(new FragmentHome());
        titleList.add("玩Android");
        fragmentList.add(new FragmentHome());
        titleList.add("知识体系");
        fragmentList.add(new FragmentHome());
        titleList.add("导航数据");

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
