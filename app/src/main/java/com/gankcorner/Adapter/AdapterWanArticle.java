package com.gankcorner.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gankcorner.ActivityWeb;
import com.gankcorner.Bean.WanArticle;
import com.gankcorner.R;
import com.gankcorner.Utils.ContextUtil;

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

        helper.addOnClickListener(R.id.card_view, R.id.home_share,
                R.id.more_item, R.id.chapterName);
    }

}
