package com.gankcorner.MainPage;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gankcorner.ActivityRead.ActivityRead;
import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;

public class PageRead extends BaseFragment implements View.OnClickListener {

    private String TAG = "=======zzq";

    private TextView mRead;
    private TextView mBillboard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.page_read, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        mRead = (TextView) view.findViewById(R.id.read);
        mBillboard = (TextView) view.findViewById(R.id.billboard);
        mRead.setOnClickListener(this);
        mBillboard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.read:
                Intent intent = new Intent(getContext(), ActivityRead.class);
                getContext().startActivity(intent);
                break;
            case R.id.billboard:
                Toast.makeText(getContext(), "榜单", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
