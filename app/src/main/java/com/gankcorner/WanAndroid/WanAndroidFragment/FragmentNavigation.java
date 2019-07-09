package com.gankcorner.WanAndroid.WanAndroidFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gankcorner.Adapter.AdapterWanNavigationRight;
import com.gankcorner.Adapter.AdapterWanNavigationLeft;
import com.gankcorner.Bean.WanNavigation;
import com.gankcorner.Bean.WanNavigationBean;
import com.gankcorner.Interface.WanAndroid;
import com.gankcorner.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentNavigation extends Fragment {

    AdapterWanNavigationLeft adapterWanNavigationLeft;
    AdapterWanNavigationRight adapterWanNavigationRight;

    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;
    private List<WanNavigation> mWanNavigationList = new ArrayList<>();
    private List<WanNavigation> tempList = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.wanandroid_test, container, false);

        initViews(view);
        initData();

        return view;
    }

    private void initViews(View view) {

        // 初始化控件
        leftRecyclerView = view.findViewById(R.id.left_recycle_view);

        // 设置线性布局管理器
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 设置adapter
        adapterWanNavigationLeft = new AdapterWanNavigationLeft(R.layout.item_navigation_left, mWanNavigationList);
        leftRecyclerView.setAdapter(adapterWanNavigationLeft);

        // 初始化控件
        rightRecyclerView = view.findViewById(R.id.right_recycler_view);

        // 设置线性布局管理器
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 设置adapter
        adapterWanNavigationRight = new AdapterWanNavigationRight(getContext(), mWanNavigationList);
        rightRecyclerView.setAdapter(adapterWanNavigationRight);
    }

    private void initData() {
        getWanNavigation();
    }

    //获取WanAndroid的体系数据
    private void getWanNavigation() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final WanAndroid requestWanNavigation = retrofit.create(WanAndroid.class);

        Call<WanNavigationBean> call = requestWanNavigation.getNavigationList();

        call.enqueue(new Callback<WanNavigationBean>() {
            @Override
            public void onResponse(@NonNull Call<WanNavigationBean> call, @NonNull Response<WanNavigationBean> response) {
                Log.d("Test", "response: " + response.toString());
                //完成解析后可以直接获取数据
                WanNavigationBean wanNavigationBean = response.body();
//                String Desc = null;
//                if (wanKnowledgeBean != null) {
//                    Desc = wanKnowledgeBean.getData().get(0).getName();
//                }
//                Log.d("WanKnowledgeBean", "UpdateInfo: " + Desc);
                for (WanNavigationBean.DataBean dataBean : wanNavigationBean.getData()) {
                    List<String> tagList = new ArrayList<>();
                    List<String> urlList = new ArrayList<>();
                    for (WanNavigationBean.DataBean.ArticlesBean articlesBean : dataBean.getArticles()) {
                        tagList.add(articlesBean.getTitle());
                        urlList.add(articlesBean.getLink());
//                        Log.d("tagList", "addKnowledgeData: " + articlesBean.getTitle());
//                        Log.d("tagList", "addKnowledgeData: " + articlesBean.getLink());
                    }
                    WanNavigation wanNavigation = new WanNavigation(dataBean.getName(), tagList, urlList);
//                    Log.d("tagTitle", "addKnowledgeData: " + dataBean.getName());
                    tempList.add(wanNavigation);
                }
                //更新请求状态以及列表信息
                adapterWanNavigationRight.refreshList(tempList);
                adapterWanNavigationLeft.setNewData(tempList);
            }

            @Override
            public void onFailure(@NonNull Call<WanNavigationBean> call, @NonNull Throwable t) {

            }
        });
    }

}
