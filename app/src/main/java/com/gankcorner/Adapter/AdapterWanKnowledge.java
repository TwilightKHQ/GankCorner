package com.gankcorner.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gankcorner.Entity.WanKnowledge;
import com.gankcorner.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class AdapterWanKnowledge extends RecyclerView.Adapter<AdapterWanKnowledge.ViewHolder> {

    private String TAG = "=======zzq";

    private Context mContext;
    private WanKnowledge wanKnowledge;
    private List<WanKnowledge> mWanKnowledgeList;
    private List<List<String>> mTags = new ArrayList<>();

    public AdapterWanKnowledge(Context context, List<WanKnowledge> wanKnowledgeList) {
        this.mContext = context;
        this.mWanKnowledgeList = wanKnowledgeList;
        for (WanKnowledge wanKnowledge : wanKnowledgeList) {
            this.mTags.add(wanKnowledge.getTagList());
        }
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
    public void onBindViewHolder(@NonNull AdapterWanKnowledge.ViewHolder holder, final int position) {
        wanKnowledge = mWanKnowledgeList.get(position);
        holder.tagTitle.setText(wanKnowledge.getName());
        List<String> tagList = wanKnowledge.getTagList();
        Log.d("mTags", "Size: " + mTags.size());
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
                Log.i(TAG, "pos: " + pos);
                Toast.makeText(mContext, mTags.get(position).get(pos), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWanKnowledgeList.size();
    }

    public void refreshList(List<WanKnowledge> Data) {
        if (Data != null) {
            mWanKnowledgeList.clear();
            mWanKnowledgeList.addAll(Data);
            mTags.clear();
            for (WanKnowledge wanKnowledge : Data) {
                mTags.add(wanKnowledge.getTagList());
            }
        }
        notifyDataSetChanged();
    }
}
