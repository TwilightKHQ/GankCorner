package com.gankcorner.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gankcorner.R;
import com.gankcorner.Utils.ContextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frank on 2017/4/11.
 */

public class AdapterTest extends RecyclerView.Adapter<AdapterTest.TestHolder> {

    List<String> data;

    public AdapterTest(List<String> data) {
        this.data = data;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
        return new TestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TestHolder holder, int position) {
        if (data != null && data.size() > 0) {
            String text = data.get(position);
            holder.textView.setText(text);
        }
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContextUtil.getContext(), holder.textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    static class TestHolder extends RecyclerView.ViewHolder {
        TextView textView;

        TestHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.header_text);
            textView.setTextSize(38.0f);
        }
    }
}
