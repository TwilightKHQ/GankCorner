package com.gankcorner.Interface;

import com.gankcorner.Bean.OneBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface One {

    @GET("one/day")
    Call<OneBean> getOne();
}
