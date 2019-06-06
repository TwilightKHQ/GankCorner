package com.gankcorner.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gankcorner.Bean.MenuItem;
import com.gankcorner.R;

import java.util.List;

public class AdapterMenuItem extends RecyclerView.Adapter<AdapterMenuItem.ViewHolder> {

    private List<MenuItem> menuItemList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        ImageView itemImage;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemImage = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }

    public AdapterMenuItem(List<MenuItem> menuItem) {
        menuItemList = menuItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem menuItem = menuItemList.get(position);
        holder.itemName.setText(menuItem.getName());
        holder.itemImage.setImageResource(menuItem.getImageId());
    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }
}
