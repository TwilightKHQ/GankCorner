package com.gankcorner.Adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gankcorner.Bean.WanNavigation;
import com.gankcorner.R;

import java.util.List;

public class AdapterWanNavigationLeft extends BaseQuickAdapter<WanNavigation, BaseViewHolder> {

    public AdapterWanNavigationLeft(int layoutResId, @Nullable List<WanNavigation> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WanNavigation item) {
        helper.setText(R.id.title, item.getName());
    }
}
