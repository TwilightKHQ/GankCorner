package com.gankcorner.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gankcorner.ActivityWeb;
import com.gankcorner.Bean.WanKnowledge;
import com.gankcorner.Bean.WanNavigation;
import com.gankcorner.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class AdapterWanNavigation extends RecyclerView.Adapter<AdapterWanNavigation.ViewHolder> {

    private Context mContext;
    private WanNavigation wanNavigation;
    private List<WanNavigation> mWanNavigationList;
    private List<List<String>> mTags = new ArrayList<>();
    private List<List<String>> mUrls = new ArrayList<>();

    public AdapterWanNavigation(Context context, List<WanNavigation> mWanNavigationList) {
        this.mContext = context;
        this.mWanNavigationList = mWanNavigationList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tagTitle;
        TagFlowLayout tagFlowLayout;

        ViewHolder(View itemView) {
            super(itemView);
            tagTitle = (TextView) itemView.findViewById(R.id.title);
            tagFlowLayout = (TagFlowLayout) itemView.findViewById(R.id.flow_layout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_knowledge, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWanNavigation.ViewHolder holder, final int position) {
        wanNavigation = mWanNavigationList.get(position);
        holder.tagTitle.setText(wanNavigation.getName());
        List<String> tagList = wanNavigation.getTagList();
        List<String> urlList = wanNavigation.getUrlList();
        mTags.add(position, tagList);
        mUrls.add(position, urlList);
        Log.d("mTags", "Size: " + mTags.size());
        if (mTags.size() > (position + 1)) {
            mTags.remove(position + 1);
            mUrls.remove(position + 1);
        }
        Log.d("mTags", "mTags: " + mTags.get(mTags.size() - 1));
        holder.tagFlowLayout.setAdapter(new TagAdapter<String>(tagList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View mInflater = LayoutInflater.from(mContext).inflate(R.layout.item_tag, parent, false);
                final TextView textView = (TextView) mInflater.findViewById(R.id.tag_text);
                textView.setText(s);
                return textView;
            }
        });
        holder.tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int pos, FlowLayout parent) {
                Intent intent = new Intent(mContext, ActivityWeb.class);
                intent.putExtra("page_desc", mTags.get(position).get(pos));
                intent.putExtra("page_url", mUrls.get(position).get(pos));
                mContext.startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWanNavigationList.size();
    }

    public void refreshList(List<WanNavigation> Data) {
        if (Data != null) {
            mWanNavigationList.clear();
            mWanNavigationList.addAll(Data);
        }
        notifyDataSetChanged();
    }

    public void updateList(List<WanNavigation> Data) {
        if (Data != null) {
            mWanNavigationList.addAll(Data);
        }
        notifyDataSetChanged();
    }
}
