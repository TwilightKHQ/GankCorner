package com.gankcorner.Adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gankcorner.Entity.MenuItem;
import com.gankcorner.R;

import java.util.List;

import static com.gankcorner.Utils.AppUtil.getContext;

public class AdapterMenuItem extends RecyclerView.Adapter<AdapterMenuItem.ViewHolder>  {

    private List<MenuItem> menuItemList;
    private int resId;

    public AdapterMenuItem(int resId, List<MenuItem> menuItem) {
        this.resId = resId;
        this.menuItemList = menuItem;
    }

    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;

        ViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        MenuItem menuItem = menuItemList.get(position);
        holder.itemName.setText(menuItem.getName());
        Drawable drawable = getContext().getResources().getDrawable(menuItem.getImageId());
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        holder.itemName.setCompoundDrawables(null, drawable, null, null);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }
}
