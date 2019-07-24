package com.gankcorner.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gankcorner.Entity.WanArticle;
import com.gankcorner.R;
import com.gankcorner.View.CharAvatarView;

import java.util.List;

public class AdapterWanArticle extends BaseQuickAdapter<WanArticle, BaseViewHolder> {


    public AdapterWanArticle(int layoutResId, @Nullable List<WanArticle> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WanArticle item) {
        helper.setText(R.id.author, item.getAuthor());
        helper.setText(R.id.chapterName, item.getChapterName());
        helper.setText(R.id.niceDate, item.getNiceDate());
        helper.setText(R.id.title, item.getTitle());
        CharAvatarView headView = (CharAvatarView) helper.getView(R.id.circle_image_view);
        headView.setText(item.getAuthor());

        helper.addOnClickListener(R.id.card_view, R.id.home_share,
                R.id.more_item, R.id.chapterName);
    }

}
