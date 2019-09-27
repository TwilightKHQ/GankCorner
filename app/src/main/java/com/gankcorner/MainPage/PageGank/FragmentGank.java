package com.gankcorner.MainPage.PageGank;


import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gankcorner.ActivityWeb;
import com.gankcorner.Adapter.AdapterGank;
import com.gankcorner.Entity.GankArticle;
import com.gankcorner.Bean.GankArticleBean;
import com.gankcorner.Interface.Gank;
import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;
import com.gankcorner.Utils.CommonUtils;
import com.gankcorner.Utils.LogUtil;
import com.gankcorner.View.DialogSelect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentGank extends BaseFragment {

    private String TAG = "=======zzq";

    private TextView headerText;
    private AdapterGank adapterGank;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<GankArticle> mGankArticle = new ArrayList<>();

    private String type = "all";
    private int REQUEST_CODE = 21;

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
        refresh();
    }

    private void refresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        getGank(type, numPerPage, 1);
    }


    private void initView(View view) {
        // 初始化控件
        mRecyclerView = view.findViewById(R.id.recycle_view);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        // 定义一个线性布局管理器
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        // 设置布局管理器
        mRecyclerView.setLayoutManager(manager);
        // 设置adapter
        adapterGank = new AdapterGank(R.layout.item_gank, mGankArticle);
        adapterGank.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapterGank.isFirstOnly(false);
        adapterGank.addHeaderView(getHeaderView());
        adapterGank.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), ActivityWeb.class);
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
                    getGank(type, numPerPage, manager.getItemCount() / numPerPage + 1);
                    LogUtil.d("currentPage", "onScrollStateChanged: " + manager.getItemCount() / numPerPage);
                }
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(R.color.bilibili);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getGank(type, numPerPage, 1);
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
                LogUtil.d(TAG, "Android_response: " + response.toString());
                //完成解析后可以直接获取数据
                GankArticleBean gankArticleBean = response.body();
                addData(gankArticleBean);
                gettingData = false;
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<GankArticleBean> call, @NonNull Throwable t) {
                LogUtil.i("ERROR_INFO", "onFailure------> " + t.getMessage());
                LogUtil.i("ERROR_INFO", "onFailure------> " + t.toString());
            }
        });
    }

    private void addData(GankArticleBean gankArticleBean) {
        mGankArticle = new ArrayList<>();
        for (int i = 0; i < numPerPage; i++) {
            GankArticleBean.ResultsBean resultsBean = gankArticleBean.getResults().get(i);
            GankArticle gankArticle = new GankArticle(resultsBean.getWho(), resultsBean.getDesc(),
                    resultsBean.getPublishedAt(), resultsBean.getUrl(), resultsBean.getImages());
            mGankArticle.add(gankArticle);
        }
        if (mSwipeRefreshLayout.isRefreshing()) {
            adapterGank.setNewData(mGankArticle);
        } else {
            adapterGank.addData(mGankArticle);
        }
    }

    private View getHeaderView() {
        View view = getLayoutInflater().inflate(R.layout.header_gank,
                (ViewGroup) mRecyclerView.getParent(), false);
        TextView selectOther = (TextView) view.findViewById(R.id.more);
        headerText = (TextView) view.findViewById(R.id.header);
        selectOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogSelect dialogSelect = new DialogSelect();
                dialogSelect.setTargetFragment(FragmentGank.this, REQUEST_CODE);
                dialogSelect.show(getFragmentManager(), "dialogSelect");
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK && data != null) {//获取从DialogFragmentB中传递的mB2A
                if (!type.equals(data.getStringExtra("refresh_type"))) {
                    type = data.getStringExtra("refresh_type");
                    if (type.equals("all")) {
                        headerText.setText(getResources().getString(R.string.all));
                    } else {
                        headerText.setText(type);
                    }
                    refresh();
                } else {
                    Toast.makeText(getContext(), "呲！", Toast.LENGTH_SHORT).show();
                }
                LogUtil.i(TAG, "refresh_type: " + type);
            }
        }
    }
}
