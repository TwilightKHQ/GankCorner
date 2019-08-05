package com.gankcorner.Utils;

import android.util.Log;

import com.gankcorner.Bean.GankArticleBean;
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

        Call<GankArticleBean> call = requestGankInfo.getArticleList(type, num, page);

        call.enqueue(new Callback<GankArticleBean>() {
            @Override
            public void onResponse(Call<GankArticleBean> call, Response<GankArticleBean> response) {
                LogUtil.d("Test", "response: " + response.toString());
                //完成解析后可以直接获取数据
                GankArticleBean gankArticleBean = response.body();
                String Desc = null;
                if (gankArticleBean != null) {
                    Desc = gankArticleBean.getResults().get(0).getDesc();
                }
                LogUtil.d("Test", "UpdateInfo: " + Desc);
            }

            @Override
            public void onFailure(Call<GankArticleBean> call, Throwable t) {

            }
        });
    }
}
