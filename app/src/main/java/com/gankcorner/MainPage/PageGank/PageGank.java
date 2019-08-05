package com.gankcorner.MainPage.PageGank;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gankcorner.Adapter.AdapterFragment;
import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageGank extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private List<Fragment> fragmentList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.page_gank, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);

        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentGank());
        fragmentList.add(new FragmentArticle());
        fragmentList.add(new FragmentKnowledge());
        fragmentList.add(new FragmentNavigation());
        final String[] titles = getResources().getStringArray(R.array.gank_title);
        AdapterFragment adapterFragment = new AdapterFragment(getChildFragmentManager(),
                getContext(), fragmentList, Arrays.asList(titles));

        //在Fragment当中
        mViewPager.setAdapter(adapterFragment);
        //将TabLayout与ViewPager联系起来
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(1);
    }
}
