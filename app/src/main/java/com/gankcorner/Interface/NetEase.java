package com.gankcorner.Interface;

import com.gankcorner.Bean.NetEaseBannerBean;
import com.gankcorner.Bean.NetEaseSongListBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetEase {

    @GET("netease/banner")
    Call<NetEaseBannerBean> getNetEaseBanner();

//    @GET("netease/songList/hot?cat=全部&pageSize={pageSize}&page={page}")
//    Call<NetEaseSongListBean> getNetEaseSongList(@Path("pageSize") int pageSize, @Path("page") int page);

    @GET("netease/songList/hot?cat=全部")
    Call<NetEaseSongListBean> getNetEaseSongList(@Query("pageSize") int pageSize, @Query("page") int page);
}
