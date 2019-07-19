package com.gankcorner.GankIO.GankioFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gankcorner.ActivityWeb;
import com.gankcorner.Adapter.AdapterGank;
import com.gankcorner.Bean.GankArticle;
import com.gankcorner.Bean.GankArticleBean;
import com.gankcorner.Interface.Gank;
import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;
import com.gankcorner.Utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentAndroid extends BaseFragment {

    private String TAG = "=======zzq";

    private AdapterGank adapterGank;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<GankArticle> mGankArticle = new ArrayList<>();

    private int numPerPage = 10; //每页的个数

    private boolean gettingData = false; //当前是否正在请求数据

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gank_android, container, false);

        initView(view);

        return view;
    }

    @Override
    public void onFirstVisibleToUser() {
        mSwipeRefreshLayout.setRefreshing(true);
        getGank("Android", numPerPage, 1);
    }

    private void initView(View view) {
        // 初始化控件
        mRecyclerView = view.findViewById(R.id.left_recycle_view);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        // 定义一个线性布局管理器
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        // 设置布局管理器
        mRecyclerView.setLayoutManager(manager);
        // 设置adapter
        adapterGank = new AdapterGank(R.layout.item_gank, mGankArticle);
        adapterGank.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapterGank.isFirstOnly(false);
        adapterGank.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), ActivityWeb.class);
                //在滑动gankArticle会的值会变化，导致传递的字符串不对
//                Log.d("Url", "Position"+ position + " Desc: " + adapterGank.getData().get(position).getDesc());
                intent.putExtra("page_desc", adapterGank.getData().get(position).getDesc());
                intent.putExtra("page_url", adapterGank.getData().get(position).getUrl());
                getContext().startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapterGank);
        //快要滑动到底部时自动加载更多
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (CommonUtils.isWillBottom(recyclerView) && !gettingData) {
                    getGank("Android", numPerPage, manager.getItemCount() / numPerPage + 1);
                    Log.d("currentPage", "onScrollStateChanged: " + manager.getItemCount() / numPerPage);
                }
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(R.color.bilibili);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getGank("Android", numPerPage, 1);
            }
        });
    }

    private void getGank(String type, final int num, int page) {
        gettingData = true;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gank.io/api/data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Gank requestGankInfo = retrofit.create(Gank.class);

        Call<GankArticleBean> call = requestGankInfo.getArticleList(type, num, page);

        call.enqueue(new Callback<GankArticleBean>() {
            @Override
            public void onResponse(@NonNull Call<GankArticleBean> call, @NonNull Response<GankArticleBean> response) {
                Log.d(TAG, "Android_response: " + response.toString());
                //完成解析后可以直接获取数据
                GankArticleBean gankArticleBean = response.body();
//                String  Desc = null;
//                if (gankArticleBean != null) {
//                    Desc = gankArticleBean.getResults().get(0).getDesc();
//                }
//                Log.d("Test", "UpdateInfo: " + Desc);
                addData(gankArticleBean);
                gettingData = false;
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<GankArticleBean> call, @NonNull Throwable t) {

            }
        });
    }

    private void addData(GankArticleBean gankArticleBean) {
        mGankArticle = new ArrayList<>();
        for (int i = 0; i < numPerPage; i++) {
            GankArticleBean.ResultsBean resultsBean = gankArticleBean.getResults().get(i);
            GankArticle gankArticle = new GankArticle(resultsBean.getWho(), resultsBean.getDesc(),
                    resultsBean.getPublishedAt(), resultsBean.getUrl(), resultsBean.getImages());
//            Log.d("GankArticle", "addData: " + gankArticle.getDesc());
            mGankArticle.add(gankArticle);
        }
        if (mSwipeRefreshLayout.isRefreshing()) {
            adapterGank.setNewData(mGankArticle);
        } else {
            adapterGank.addData(mGankArticle);
        }
    }
}
