package com.gankcorner.GankIO;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.gankcorner.Adapter.AdapterGank;
import com.gankcorner.Bean.GankArticle;
import com.gankcorner.Bean.GankBean;
import com.gankcorner.Interface.Gank;
import com.gankcorner.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment with a Google +1 button.
 */
public class FragmentiOS extends Fragment {

    private AdapterGank adapterGank;
    private RecyclerView mRecyclerView;
    private List<GankArticle> mGankArticle = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_android, container, false);

        // 初始化控件
        mRecyclerView = view.findViewById(R.id.recycle_view);

        initRecyclerView();
        getGank("iOS", 20, 1);

        return view;
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView(){
        // 定义一个线性布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        // 设置布局管理器
        mRecyclerView.setLayoutManager(manager);
        // 设置adapter
        adapterGank = new AdapterGank(getContext(), mGankArticle);
        mRecyclerView.setAdapter(adapterGank);
    }

    private void getGank(String type, final int num, int page) {
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
//                String  Desc = null;
//                if (gankBean != null) {
//                    Desc = gankBean.getResults().get(0).getDesc();
//                }
//                Log.d("Test", "UpdateInfo: " + Desc);
                initData(gankBean);
            }

            @Override
            public void onFailure(Call<GankBean> call, Throwable t) {

            }
        });
    }

    private void initData(GankBean gankBean){
        mGankArticle = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            GankBean.ResultsBean resultsBean = gankBean.getResults().get(i);
            GankArticle gankArticle = new GankArticle(resultsBean.getWho(), resultsBean.getDesc(),
                    resultsBean.getPublishedAt(), resultsBean.getUrl(), resultsBean.getImages());
//            Log.d("GankArticle", "image: " + resultsBean.getImages());
            mGankArticle.add(gankArticle);
        }
        // 更新数据
        adapterGank.updateList(mGankArticle);
    }

}
