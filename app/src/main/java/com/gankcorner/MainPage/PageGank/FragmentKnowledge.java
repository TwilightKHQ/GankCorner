package com.gankcorner.MainPage.PageGank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gankcorner.Adapter.AdapterWanKnowledge;
import com.gankcorner.Entity.WanKnowledge;
import com.gankcorner.Bean.WanKnowledgeBean;
import com.gankcorner.Interface.WanAndroid;
import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.gankcorner.Utils.CommonUtils.changeSpecialChar;

public class FragmentKnowledge extends BaseFragment {

    private String TAG = "========zzq";

    AdapterWanKnowledge adapterWanKnowledge;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<WanKnowledge> mWanKnowledgeList = new ArrayList<>();
    private List<WanKnowledge> tempList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.wanandroid_knowledge, container, false);

        initViews(view);

        return view;
    }

    @Override
    public void onFirstVisibleToUser() {
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }

    private void initViews(View view) {

        // 初始化控件
        mRecyclerView = view.findViewById(R.id.left_recycle_view);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        // 设置线性布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 设置adapter
        adapterWanKnowledge = new AdapterWanKnowledge(getContext(), mWanKnowledgeList);
        mRecyclerView.setAdapter(adapterWanKnowledge);
        //配置SwipeRefreshLayout
        mSwipeRefreshLayout.setColorSchemeResources(R.color.bilibili);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWanKnowledge();
            }
        });
    }

    private void refresh() {
        getWanKnowledge();
    }

    //获取WanAndroid的体系数据
    private void getWanKnowledge() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final WanAndroid requestWanKnowledge = retrofit.create(WanAndroid.class);

        Call<WanKnowledgeBean> call = requestWanKnowledge.getKnowledgeList();

        call.enqueue(new Callback<WanKnowledgeBean>() {
            @Override
            public void onResponse(@NonNull Call<WanKnowledgeBean> call, @NonNull Response<WanKnowledgeBean> response) {
                Log.d(TAG, "response: " + response.toString());
                //完成解析后可以直接获取数据
                WanKnowledgeBean wanKnowledgeBean = response.body();
                tempList = new ArrayList<>();
                for (WanKnowledgeBean.DataBean dataBean : wanKnowledgeBean.getData()) {
                    List<String> tagList = new ArrayList<>();
                    for (WanKnowledgeBean.DataBean.ChildrenBean childrenBean : dataBean.getChildren()) {
                        tagList.add(childrenBean.getName());
//                        Log.d(TAG, "addKnowledgeData: " + childrenBean.getName());
                    }
                    WanKnowledge wanKnowledge = new WanKnowledge(dataBean.getName(), tagList);
                    tempList.add(wanKnowledge);
                }
                //更新请求状态以及列表信息
                adapterWanKnowledge.refreshList(tempList);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<WanKnowledgeBean> call, @NonNull Throwable t) {

            }
        });
    }

}
