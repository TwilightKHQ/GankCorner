package com.gankcorner.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class AdapterFragment extends FragmentPagerAdapter {

    private Context context;
    private List<String> titleList;
    private List<Fragment> fragmentList;

    public AdapterFragment(FragmentManager fragmentManager, Context context,
                           List<Fragment> fragmentList, List<String> titleList) {
        super(fragmentManager);
        this.context = context;
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    /**
     * 描述：获取索引位置的Fragment.
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if (position < fragmentList.size()) {
            fragment = fragmentList.get(position);
        } else {
            fragment = fragmentList.get(0);
        }
        return fragment;
    }

    /**
     * 返回 viewpager对应的 title
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        if (titleList != null && titleList.size() > 0) {
            return titleList.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }
}