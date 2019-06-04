package com.gankcorner.Utils;

import android.util.Log;

import com.gankcorner.Bean.GankBean;
import com.gankcorner.Interface.Gank;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    public static void getGank(String type, final int num, int page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Gank requestGankInfo = retrofit.create(Gank.class);

        Call<GankBean> call = requestGankInfo.getArticleList(type, num, page);

        call.enqueue(new Callback<GankBean>() {
            @Override
            public void onResponse(Call<GankBean> call, Response<GankBean> response) {
                Log.d("Test", "response: " + response.toString());
                //完成解析后可以直接获取数据
                GankBean gankBean = response.body();
                String  Desc = null;
                if (gankBean != null) {
                    Desc = gankBean.getResults().get(0).getDesc();
                }
                Log.d("Test", "UpdateInfo: " + Desc);
            }

            @Override
            public void onFailure(Call<GankBean> call, Throwable t) {

            }
        });
    }
}
