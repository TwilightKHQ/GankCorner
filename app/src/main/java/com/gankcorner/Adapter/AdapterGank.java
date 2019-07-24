package com.gankcorner.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gankcorner.Entity.GankArticle;
import com.gankcorner.R;

import java.util.List;

public class AdapterGank extends BaseQuickAdapter<GankArticle, BaseViewHolder> {


    public AdapterGank(int layoutResId, @Nullable List<GankArticle> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankArticle item) {
        helper.setText(R.id.who, item.getWho());
        helper.setText(R.id.desc, item.getDesc());
        helper.setText(R.id.publishedAt, item.getPublishedAt().substring(5, 10));
        if (item.getImage() != null && item.getImage().size() != 0) {
            Glide.with(mContext)
                    .load(item.getImage().get(0))
                    .placeholder(R.mipmap.loading)
                    .into((ImageView) helper.getView(R.id.images));
        } else {
            //防止图片复用错误
            //当ViewHolder复用的时候，如果当前返回的图片url为null，为了防止上一个复用的viewHolder图片
            //遗留，要clear并且将图片设置为空。
            Glide.with(mContext)
                    .clear((ImageView) helper.getView(R.id.images));
            helper.setImageDrawable(R.id.images, null);
        }
    }
}
