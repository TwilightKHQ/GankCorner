package com.gankcorner.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gankcorner.Bean.WanAndroidArticle;
import com.gankcorner.R;

import java.util.List;

public class AdapterWanAndroid extends RecyclerView.Adapter<AdapterWanAndroid.ViewHolder>{

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private Context mContext;
    private View mHeaderView;
    private WanAndroidArticle wanAndroidArticle;
    private List<WanAndroidArticle> mWanAndroidArticleList;

    public AdapterWanAndroid(Context context, List<WanAndroidArticle> wanAndroidArticleList) {
        this.mContext = context;
        this.mWanAndroidArticleList = wanAndroidArticleList;
    }

    public void setHeaderView(View headerView) {
        this.mHeaderView = headerView;
    }

    //根据pos返回不同的ItemViewType
     @Override
     public int getItemViewType(int position) {
         if (mHeaderView == null) return TYPE_NORMAL;
         if (position == 0) return TYPE_HEADER;
         return TYPE_NORMAL;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView chapterName;
        TextView link;
        TextView niceDate;
        TextView superChapterName;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            if( itemView == mHeaderView ) return;

            author = (TextView) itemView.findViewById(R.id.author);
            chapterName = (TextView) itemView.findViewById(R.id.chapterName);
            link = (TextView) itemView.findViewById(R.id.link);
            niceDate = (TextView) itemView.findViewById(R.id.niceDate);
            superChapterName = (TextView) itemView.findViewById(R.id.superChapterName);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    @NonNull
    @Override
    public AdapterWanAndroid.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if ( mHeaderView != null && viewType == TYPE_HEADER ) {
            return new ViewHolder(mHeaderView);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wanandroid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWanAndroid.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_HEADER) return;

        final int pos = getRealPosition(holder); //获取正常Item的位置
        wanAndroidArticle = mWanAndroidArticleList.get(pos);
        holder.author.setText(wanAndroidArticle.getAuthor());
        holder.chapterName.setText(wanAndroidArticle.getChapterName());
        holder.link.setText(wanAndroidArticle.getLink());
        holder.niceDate.setText(wanAndroidArticle.getNiceDate());
        holder.superChapterName.setText(wanAndroidArticle.getChapterName());
        holder.title.setText(wanAndroidArticle.getTitle());
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mWanAndroidArticleList.size() : mWanAndroidArticleList.size() + 1;
    }

    public void refreshList(List<WanAndroidArticle> Data) {
        if (Data != null) {
            mWanAndroidArticleList.clear();
            mWanAndroidArticleList.addAll(Data);
        }
        notifyDataSetChanged();
    }

    public void updateList(List<WanAndroidArticle> Data) {
        if (Data != null) {
            mWanAndroidArticleList.addAll(Data);
        }
        notifyDataSetChanged();
    }
}
