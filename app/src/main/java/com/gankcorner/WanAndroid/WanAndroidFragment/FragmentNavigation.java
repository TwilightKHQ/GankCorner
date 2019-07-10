package com.gankcorner.WanAndroid.WanAndroidFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.gankcorner.Adapter.AdapterWanNaviRight;
import com.gankcorner.Adapter.AdapterWanNaviLeft;
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

    private String TAG = "========zzq";

    private AdapterWanNaviLeft adapterWanNaviLeft;
    AdapterWanNaviRight adapterWanNaviRight;

    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;
    private List<WanNavigation> mWanNavigationList = new ArrayList<>();
    private List<WanNavigation> tempList;

    private boolean isScroll;//点击左边recycler，右边不监听设置左边的Recycler的选中


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.wanandroid_navigation, container, false);

        initViews(view);
        initData();
        initLinkageListener();

        return view;
    }

    private void initViews(View view) {

        // 初始化控件
        leftRecyclerView = view.findViewById(R.id.left_recycle_view);

        // 设置线性布局管理器
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 设置adapter
        adapterWanNaviLeft = new AdapterWanNaviLeft(getContext(), mWanNavigationList);
        leftRecyclerView.setAdapter(adapterWanNaviLeft);

        // 初始化控件
        rightRecyclerView = view.findViewById(R.id.right_recycler_view);

        // 设置线性布局管理器
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 设置adapter
        adapterWanNaviRight = new AdapterWanNaviRight(getContext(), mWanNavigationList);
        rightRecyclerView.setAdapter(adapterWanNaviRight);
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
                tempList = new ArrayList<>();
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
                adapterWanNaviRight.refreshList(tempList);
                adapterWanNaviLeft.refreshList(tempList);
            }

            @Override
            public void onFailure(@NonNull Call<WanNavigationBean> call, @NonNull Throwable t) {

            }
        });
    }

    //初始化联动监听
    @SuppressLint("ClickableViewAccessibility")
    private void initLinkageListener() {
        //右边RecyclerView滚动监听
        rightRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //获取第一个可见view的位置
                int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                int lastItemPosition = linearManager.findLastVisibleItemPosition();
                Log.i(TAG, "onScrolled: " + firstItemPosition);

                if (isScroll) {
                    adapterWanNaviLeft.setSelectedPosition(firstItemPosition);//滚动到悬浮view
                    if (dy > 0) {
                        leftRecyclerView.smoothScrollToPosition(lastItemPosition);
                    } else {
                        leftRecyclerView.smoothScrollToPosition(firstItemPosition);
                    }

                }
            }
        });


        //右边RecyclerView的触摸监听
        rightRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isScroll = true;//触摸右边recycler，可监听设置左边的选中
                return false;
            }
        });


        //左边RecyclerView点击事件
        adapterWanNaviLeft.setOnItemClickListener(new AdapterWanNaviLeft.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                isScroll = false;
                adapterWanNaviLeft.setSelectedPosition(position);
                rightRecyclerView.smoothScrollToPosition(position);//滚动到对应的Item
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }


        });
    }

}
