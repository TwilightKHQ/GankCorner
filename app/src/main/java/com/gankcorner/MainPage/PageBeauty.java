package com.gankcorner.MainPage;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.gankcorner.Bean.OneBean;
import com.gankcorner.Interface.One;
import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;
import com.gankcorner.View.ImageViewHeight;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PageBeauty extends BaseFragment {

    private String TAG = "=======zzq";

    private ImageViewHeight mPic;

    private TextView textVolume;
    private TextView textPicInfo;
    private TextView textForward;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.page_beauty, container, false);

        initView(view);


        return view;
    }

    @Override
    public void onFirstVisibleToUser() {
        getOneInfo();
    }

    private void initView(View view) {
        mPic = (ImageViewHeight) view.findViewById(R.id.mine_pic);

        textVolume = (TextView) view.findViewById(R.id.volume);
        textPicInfo = (TextView) view.findViewById(R.id.pic_info);
        textForward = (TextView) view.findViewById(R.id.forward);
    }

    private void getOneInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://v1.itooi.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        One getOne = retrofit.create(One.class);

        Call<OneBean> call = getOne.getOne();

        call.enqueue(new Callback<OneBean>() {
            @Override
            public void onResponse(Call<OneBean> call, Response<OneBean> response) {
                Log.d(TAG, "One_response: " + response.toString());
                OneBean oneBean = response.body();
                String Desc = null;
                String size = null;
                if (oneBean != null) {
                    Desc = oneBean.getData().getImg_url();
                    size = oneBean.getData().getForward();
                }
                Log.d(TAG, "Desc: " + Desc + " Size:" + size);
                Glide.with(getContext())
                        .load(Desc)
                        .into(mPic);
                textVolume.setText(oneBean.getData().getVolume());
                textPicInfo.setText( oneBean.getData().getTitle() + " | "  + oneBean.getData().getPic_info());
                textForward.setText("\t\t" + oneBean.getData().getForward());
            }

            @Override
            public void onFailure(Call<OneBean> call, Throwable t) {

            }
        });
    }
}
