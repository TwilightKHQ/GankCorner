package com.gankcorner.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gankcorner.ActivityWeb;
import com.gankcorner.Bean.WanArticle;
import com.gankcorner.R;
import com.gankcorner.Utils.ContextUtil;

import java.util.List;

public class AdapterWanArticle extends RecyclerView.Adapter<AdapterWanArticle.ViewHolder>{

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private Context mContext;
    private View mHeaderView;
    private WanArticle wanArticle;
    private List<WanArticle> mWanArticleList;

    public AdapterWanArticle(Context context, List<WanArticle> wanArticleList) {
        this.mContext = context;
        this.mWanArticleList = wanArticleList;
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
        TextView niceDate;
        TextView title;

        CardView cardView;
        ImageButton btnShare;
        ImageButton btnMore;

        ViewHolder(View itemView) {
            super(itemView);
            if( itemView == mHeaderView ) return;

            author = (TextView) itemView.findViewById(R.id.author);
            chapterName = (TextView) itemView.findViewById(R.id.chapterName);
            niceDate = (TextView) itemView.findViewById(R.id.niceDate);
            title = (TextView) itemView.findViewById(R.id.title);

            cardView = (CardView) itemView.findViewById(R.id.card_view);
            btnShare = (ImageButton) itemView.findViewById(R.id.home_share);
            btnMore = (ImageButton) itemView.findViewById(R.id.more_item);
        }
    }

    @NonNull
    @Override
    public AdapterWanArticle.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if ( mHeaderView != null && viewType == TYPE_HEADER ) {
            return new ViewHolder(mHeaderView);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wanandroid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWanArticle.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_HEADER) return;

        final int pos = getRealPosition(holder); //获取正常Item的位置
        wanArticle = mWanArticleList.get(pos);
        holder.author.setText(wanArticle.getAuthor());
        holder.chapterName.setText(wanArticle.getChapterName());
        holder.niceDate.setText( "⋅   "+ wanArticle.getNiceDate());
        holder.title.setText(wanArticle.getTitle());
        initClickEvent(holder, pos);
    }

    private void initClickEvent(ViewHolder holder, final int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityWeb.class);
                //在滑动gankArticle会的值会变化，导致传递的字符串不对
//                Log.d("Url", "Position"+ position + " Desc0: " + gankArticle.getDesc());
//                Log.d("Url", "Position"+ position + " Desc: " + mGankArticleList.get(position).getDesc());
                intent.putExtra("page_desc",
                        mWanArticleList.get(position).getTitle());
                intent.putExtra("page_url",
                        mWanArticleList.get(position).getLink());
                mContext.startActivity(intent);
            }
        });

        holder.chapterName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContextUtil.getContext(),
                        mWanArticleList.get(position).getChapterName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContextUtil.getContext(),
                        "分享：" + mWanArticleList.get(position).getTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContextUtil.getContext(),
                        "更多：" + mWanArticleList.get(position).getAuthor(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mWanArticleList.size() : mWanArticleList.size() + 1;
    }

    public void refreshList(List<WanArticle> Data) {
        if (Data != null) {
            mWanArticleList.clear();
            mWanArticleList.addAll(Data);
        }
        notifyDataSetChanged();
    }

    public void updateList(List<WanArticle> Data) {
        if (Data != null) {
            mWanArticleList.addAll(Data);
        }
        notifyDataSetChanged();
    }
}
