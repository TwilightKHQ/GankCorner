package com.gankcorner.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gankcorner.Bean.GankArticle;
import com.gankcorner.R;

import java.util.List;

public class AdapterGankTest extends BaseQuickAdapter<GankArticle, BaseViewHolder> {


    public AdapterGankTest(int layoutResId, @Nullable List<GankArticle> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankArticle item) {
        helper.setText(R.id.who, item.getWho());
        helper.setText(R.id.desc, item.getDesc());
        helper.setText(R.id.publishedAt, item.getPublishedAt().substring(5, 10));
        Glide.with(mContext)
                .load(item.getImage().get(0))
                .into((ImageView) helper.getView(R.id.images));
    }
}
