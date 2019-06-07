package com.gankcorner.Interface;

import com.gankcorner.Bean.GankArticleBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Gank {

    @GET("{type}/{num}/{page}")
    Call<GankArticleBean> getArticleList(@Path("type") String type, @Path("num") int num, @Path("page") int page);
}
