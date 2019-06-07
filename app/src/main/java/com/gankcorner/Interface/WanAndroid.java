package com.gankcorner.Interface;

import com.gankcorner.Bean.BannerBean;
import com.gankcorner.Bean.WanAndroidArticleBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WanAndroid {

    @GET("banner/json")
    Call<BannerBean> getBannerList();

    @GET("article/list/{page}/json")
    Call<WanAndroidArticleBean> getArticleList(@Path("page") int page);
}
