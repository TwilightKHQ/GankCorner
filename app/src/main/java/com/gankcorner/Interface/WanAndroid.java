package com.gankcorner.Interface;

import com.gankcorner.Bean.BannerBean;
import com.gankcorner.Bean.WanArticleBean;
import com.gankcorner.Bean.WanKnowledgeBean;
import com.gankcorner.Bean.WanNavigationBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WanAndroid {

    @GET("banner/json")
    Call<BannerBean> getBannerList();

    @GET("tree/json")
    Call<WanKnowledgeBean> getKnowledgeList();

    @GET("navi/json")
    Call<WanNavigationBean> getNavigationList();

    @GET("article/list/{page}/json")
    Call<WanArticleBean> getArticleList(@Path("page") int page);

}
