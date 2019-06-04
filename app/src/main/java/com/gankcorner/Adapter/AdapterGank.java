package com.gankcorner.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gankcorner.Bean.GankArticle;
import com.gankcorner.R;

import java.util.List;

public class AdapterGank extends RecyclerView.Adapter<AdapterGank.ViewHolder> {

    private Context mContext;
    private List<GankArticle> mGankArticleList;

    public AdapterGank (Context context, List<GankArticle> gankArticleList) {
        this.mContext = context;
        this.mGankArticleList = gankArticleList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView who;
        TextView desc;
        TextView publishedAt;
        ImageView image;

        ViewHolder(View view) {
            super(view);
            who = (TextView) view.findViewById(R.id.who);
            desc = (TextView) view.findViewById(R.id.desc);
            publishedAt = (TextView) view.findViewById(R.id.publishedAt);
            image = (ImageView) view.findViewById(R.id.images);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_gank, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        GankArticle gankArticle = mGankArticleList.get(position);
        viewHolder.who.setText(gankArticle.getWho());
        viewHolder.desc.setText(gankArticle.getDesc());
        viewHolder.publishedAt.setText(gankArticle.getPublishedAt().substring(5, 10) + " " + gankArticle.getPublishedAt().substring(11, 16));
        if (gankArticle.getImage() != null && gankArticle.getImage().size() != 0) {
            //获取屏幕的实际宽
            DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
//            System.out.println("width : " + dm.widthPixels);
            RequestOptions options = new RequestOptions()
                    .override( dm.widthPixels, (dm.widthPixels)/2);
            Glide.with(mContext)
                    .load(gankArticle.getImage().get(0))
                    .apply(options)
                    .into(viewHolder.image);
//            Log.d("Image", "onBindViewHolder: " + gankArticle.getImage().get(0));
        } else {
            //防止图片复用错误
            //当ViewHolder复用的时候，如果当前返回的图片url为null，为了防止上一个复用的viewHolder图片
            //遗留，要clear并且将图片设置为空。
            Glide.with(mContext)
                    .clear(viewHolder.image);
            viewHolder.image.setImageDrawable(null);
            viewHolder.image.setTag(R.id.images, position);
        }
    }

    @Override
    public int getItemCount() {
        return mGankArticleList.size();
    }

    public void updateList(List<GankArticle> newData) {
        if (newData != null) {
            mGankArticleList.addAll(newData);
        }
        notifyDataSetChanged();
    }

}
