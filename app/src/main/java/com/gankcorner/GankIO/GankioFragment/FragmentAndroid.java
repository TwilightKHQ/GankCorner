package com.gankcorner.GankIO.GankioFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class FragmentAndroid extends Fragment {

    private AdapterGank adapterGank;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<GankArticle> mGankArticle = new ArrayList<>();

    private int numPerPage = 10; //每页的个数

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_android, container, false);

        // 初始化控件
        mRecyclerView = view.findViewById(R.id.recycle_view);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        initView();
        getGank("Android", numPerPage, 1);

        return view;
    }

    /**
     * 初始化RecyclerView
     */
    private void initView() {
        // 定义一个线性布局管理器
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        // 设置布局管理器
        mRecyclerView.setLayoutManager(manager);
        // 设置adapter
        adapterGank = new AdapterGank(getContext(), mGankArticle);
        mRecyclerView.setAdapter(adapterGank);
        //快要滑动到底部时自动加载更多
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //SCROLL_STATE_IDLE为停止滑动状态
                //屏幕中最后一个可见子项的position
                final int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                //当前屏幕所看到的子项个数
                final int visibleItemCount = manager.getChildCount();
                //当前RecyclerView的所有子项个数
                final int totalItemCount = manager.getItemCount();
                if (visibleItemCount > 0 && lastVisibleItemPosition >= totalItemCount - 3 && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    getGank("Android", numPerPage, totalItemCount / numPerPage + 1);
                    Log.d("currentNum", "Page: " + totalItemCount / numPerPage);
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
                addData(gankBean);
                mSwipeRefreshLayout.setRefreshing(false);
                Log.d("SwipeRefreshLayout", "onResponse: 加载完成");
            }

            @Override
            public void onFailure(Call<GankBean> call, Throwable t) {

            }
        });
    }

    private void addData(GankBean gankBean) {
        mGankArticle = new ArrayList<>();
        for (int i = 0; i < numPerPage; i++) {
            GankBean.ResultsBean resultsBean = gankBean.getResults().get(i);
            GankArticle gankArticle = new GankArticle(resultsBean.getWho(), resultsBean.getDesc(),
                    resultsBean.getPublishedAt(), resultsBean.getUrl(), resultsBean.getImages());
            Log.d("GankArticle", "addData: " + gankArticle.getDesc());
            mGankArticle.add(gankArticle);
        }
        adapterGank.updateList(mGankArticle);
    }

}
