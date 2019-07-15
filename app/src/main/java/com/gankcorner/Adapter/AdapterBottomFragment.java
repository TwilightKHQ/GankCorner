package com.gankcorner.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class AdapterBottomFragment extends FragmentPagerAdapter {

    Context context;
    private String[] titles;
    List<Fragment> listFragment;

    public AdapterBottomFragment(FragmentManager fragmentManager, Context context,
                                 List<Fragment> listFragment, String[] titles) {
        super(fragmentManager);
        this.context = context;
        this.listFragment = listFragment;
        this.titles = titles;
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
        if (position < listFragment.size()) {
            fragment = listFragment.get(position);

        } else {
            fragment = listFragment.get(0);

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
        if (titles != null && titles.length > 0) {
            return titles[position];
        }
        return null;
    }

    @Override
    public int getCount() {
        return listFragment == null ? 0 : listFragment.size();
    }

    @Override
    public void  destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }
}