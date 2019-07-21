package com.gankcorner.ActivityRead;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.gankcorner.Bean.OneBean;
import com.gankcorner.Interface.One;
import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;
import com.gankcorner.Utils.ImageViewHeight;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentReadArticle extends BaseFragment {

    private String TAG = "=======zzq";

    private ImageViewHeight mPic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tes, container, false);

        return view;
    }
}
