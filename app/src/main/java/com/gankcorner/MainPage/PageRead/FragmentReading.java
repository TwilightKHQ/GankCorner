package com.gankcorner.MainPage.PageRead;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;
import com.gankcorner.View.ImageViewHeight;

public class FragmentReading extends BaseFragment {

    private String TAG = FragmentReading.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_article, container, false);



        return view;
    }
}
