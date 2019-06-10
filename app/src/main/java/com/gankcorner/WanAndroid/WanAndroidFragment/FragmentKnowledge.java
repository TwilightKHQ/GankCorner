package com.gankcorner.WanAndroid.WanAndroidFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gankcorner.Adapter.AdapterWanKnowledge;
import com.gankcorner.Bean.WanKnowledgeBean;
import com.gankcorner.Bean.WanKnowledge;
import com.gankcorner.Bean.WanWeChatBean;
import com.gankcorner.Interface.WanAndroid;
import com.gankcorner.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentKnowledge extends Fragment {

    AdapterWanKnowledge adapterWanKnowledge;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<WanKnowledge> mWanKnowledgeList = new ArrayList<>();
    private List<WanKnowledge> tempList = new ArrayList<>();

    private boolean requestWeChat = false;
    private boolean requestKnowledge = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.wanandroid_knowledge, container, false);

        initViews(view);
        initData();

        return view;
    }

    private void initViews(View view) {

        // 初始化控件
        mRecyclerView = view.findViewById(R.id.recycle_view);
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
//                requestWeChat = true;
                requestKnowledge = true;
                tempList.clear();
            }
        });
    }

    private void initData() {
//        getWanWeChat();
        getWanKnowledge();
    }

//    private void getWanWeChat() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://www.wanandroid.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        final WanAndroid requestWanWeChat = retrofit.create(WanAndroid.class);
//
//        Call<WanWeChatBean> call = requestWanWeChat.getWeChatList();
//
//        call.enqueue(new Callback<WanWeChatBean>() {
//            @Override
//            public void onResponse(@NonNull Call<WanWeChatBean> call, @NonNull Response<WanWeChatBean> response) {
//                Log.d("Test", "response: " + response.toString());
//                //完成解析后可以直接获取数据
//                WanWeChatBean wanWeChatBean = response.body();
//                String Desc = null;
//                if (wanWeChatBean != null) {
//                    Desc = wanWeChatBean.getData().get(0).getName();
//                }
//                Log.d("WanKnowledgeBean", "UpdateInfo: " + Desc);
////                addKnowledgeData(wanWeChatBean, 1);
//                List<String> tagList = new ArrayList<>();
//                for (WanWeChatBean.DataBean dataBean : wanWeChatBean.getData()) {
//                    tagList.add(dataBean.getName());
//                    Log.d("tagList", "addKnowledgeData: " + dataBean.getName());
//                }
//                WanKnowledge wanKnowledge = new WanKnowledge("公众号", tagList);
//                tempList.add(0, wanKnowledge);
//
//                //更新请求状态以及列表信息
//                requestWeChat = false;
//                if (!requestKnowledge) {
//                    mSwipeRefreshLayout.setRefreshing(false);
//                    mWanKnowledgeList.add(0, wanKnowledge);
//                    adapterWanNavigation.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<WanWeChatBean> call, @NonNull Throwable t) {
//
//            }
//        });
//    }

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
                Log.d("Test", "response: " + response.toString());
                //完成解析后可以直接获取数据
                WanKnowledgeBean wanKnowledgeBean = response.body();
//                String Desc = null;
//                if (wanKnowledgeBean != null) {
//                    Desc = wanKnowledgeBean.getData().get(0).getName();
//                }
//                Log.d("WanKnowledgeBean", "UpdateInfo: " + Desc);
                for (WanKnowledgeBean.DataBean dataBean : wanKnowledgeBean.getData()) {
                    List<String> tagList = new ArrayList<>();
                    for (WanKnowledgeBean.DataBean.ChildrenBean childrenBean : dataBean.getChildren()) {
                        tagList.add(childrenBean.getName());
                        Log.d("tagList", "addKnowledgeData: " + childrenBean.getName());
                    }
                    WanKnowledge wanKnowledge = new WanKnowledge(dataBean.getName(), tagList);
                    Log.d("tagTitle", "addKnowledgeData: " + dataBean.getName());
                    tempList.add(wanKnowledge);
                }
                //更新请求状态以及列表信息
                requestKnowledge = false;
                if (!requestWeChat) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    adapterWanKnowledge.refreshList(tempList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<WanKnowledgeBean> call, @NonNull Throwable t) {

            }
        });
    }

}
