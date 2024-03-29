package com.gankcorner.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gankcorner.Entity.WanNavigation;
import com.gankcorner.R;

import java.util.List;

public class AdapterWanNaviLeft extends RecyclerView.Adapter<AdapterWanNaviLeft.ViewHolder> {
    private Context mContext;
    private List<WanNavigation> leftRecyclerData;//数据集合
    private OnItemClickListener mOnItemClickListener;
    private int selectedPosition = 0;//初始第一个为选中状态

    public AdapterWanNaviLeft(Context context, List<WanNavigation> data) {
        this.mContext = context;
        this.leftRecyclerData = data;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout item_navi_left;
        TextView item_left_recycler_tv;

        ViewHolder(View view) {
            super(view);
            item_navi_left = view.findViewById(R.id.item_navi_left);
            item_left_recycler_tv = view.findViewById(R.id.item_left_recycler_tv);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int itemViewId = R.layout.item_navigation_left;
        return new ViewHolder(LayoutInflater.from(mContext).inflate(itemViewId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.item_left_recycler_tv.setText(leftRecyclerData.get(position).getName());

        //选中和没选中时，设置不同的颜色
        if (position == selectedPosition) {
            holder.item_left_recycler_tv.setBackgroundColor(Color.GRAY);
        } else {
            holder.item_left_recycler_tv.setBackgroundColor(Color.WHITE);
        }

        // 如果设置了回调,则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (null != leftRecyclerData && leftRecyclerData.size() > 0) ? leftRecyclerData.size() : 0;
    }

    public void refreshList(List<WanNavigation> Data) {
        if (Data != null) {
            leftRecyclerData.clear();
            leftRecyclerData.addAll(Data);
        }
        notifyDataSetChanged();
    }

    public int getSelectedPosition(){
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;//动态设置选中状态
        notifyDataSetChanged();
    }

}