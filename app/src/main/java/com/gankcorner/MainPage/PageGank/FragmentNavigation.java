package com.gankcorner.MainPage.PageGank;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.gankcorner.Adapter.AdapterWanNaviLeft;
import com.gankcorner.Adapter.AdapterWanNaviRight;
import com.gankcorner.Entity.WanNavigation;
import com.gankcorner.Bean.WanNavigationBean;
import com.gankcorner.Interface.WanAndroid;
import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;
import com.gankcorner.Utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentNavigation extends BaseFragment {

    private String TAG = "========zzq";

    private AdapterWanNaviLeft adapterWanNaviLeft;
    private AdapterWanNaviRight adapterWanNaviRight;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private View mCenterView;

    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;
    private LinearLayoutManager leftRecyclerManager;
    private LinearLayoutManager rightRecyclerManager;

    private List<WanNavigation> mWanNavigationList = new ArrayList<>();
    private List<WanNavigation> tempList;

    private boolean isScroll = true;//点击左边recycler，右边不监听设置左边的Recycler的选中

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gabhuo_navigation, container, false);

        initViews(view);
        initLinkageListener();

        return view;
    }

    @Override
    public void onFirstVisibleToUser() {
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }

    private void initViews(View view) {

        mCenterView = (View) view.findViewById(R.id.center_view);

        // 初始化控件
        leftRecyclerView = view.findViewById(R.id.left_recycle_view);
        // 设置线性布局管理器
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        leftRecyclerManager = (LinearLayoutManager) leftRecyclerView.getLayoutManager();
        // 设置adapter
        adapterWanNaviLeft = new AdapterWanNaviLeft(getContext(), mWanNavigationList);
        leftRecyclerView.setAdapter(adapterWanNaviLeft);

        // 初始化控件
        rightRecyclerView = view.findViewById(R.id.right_recycler_view);
        // 设置线性布局管理器
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rightRecyclerManager = (LinearLayoutManager) rightRecyclerView.getLayoutManager();
        // 设置adapter
        adapterWanNaviRight = new AdapterWanNaviRight(getContext(), mWanNavigationList);
        rightRecyclerView.setAdapter(adapterWanNaviRight);

        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.bilibili);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void refresh() {
        getWanNavigation();
    }

    //获取WanAndroid的导航数据
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
                LogUtil.d("Test", "response: " + response.toString());
                //完成解析后可以直接获取数据
                WanNavigationBean wanNavigationBean = response.body();
                tempList = new ArrayList<>();
                for (WanNavigationBean.DataBean dataBean : wanNavigationBean.getData()) {
                    List<String> tagList = new ArrayList<>();
                    List<String> urlList = new ArrayList<>();
                    for (WanNavigationBean.DataBean.ArticlesBean articlesBean : dataBean.getArticles()) {
                        tagList.add(articlesBean.getTitle());
                        urlList.add(articlesBean.getLink());
                    }
                    WanNavigation wanNavigation = new WanNavigation(dataBean.getName(), tagList, urlList);
                    tempList.add(wanNavigation);
                }
                //更新请求状态以及列表信息
                adapterWanNaviRight.refreshList(tempList);
                adapterWanNaviLeft.refreshList(tempList);
                mCenterView.setVisibility(View.VISIBLE);
                mSwipeRefreshLayout.setRefreshing(false);
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
                LogUtil.i(TAG, "onScrollStateChanged: " + newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //获取第一个可见view的位置
                int firstItemPosition = rightRecyclerManager.findFirstVisibleItemPosition();

                if (isScroll) {
                    //用于将左边RecyclerView的内容调整至屏幕中间
                    int firstVisibleItem = leftRecyclerManager.findFirstVisibleItemPosition();
                    int lastVisibleItem = leftRecyclerManager.findLastVisibleItemPosition();
                    int containHalfItem = (lastVisibleItem - firstVisibleItem) / 2;
                    int nowSelectedPosition = adapterWanNaviLeft.getSelectedPosition();

                    if (dy > 0) { // 右边RecyclerView向下滑动
                        if (nowSelectedPosition < firstVisibleItem) { //将左侧不可见的Item移动至可见
                            leftRecyclerView.smoothScrollToPosition(nowSelectedPosition);
                        } else {
                            // 将左侧可见的已选中Item移动到屏幕中间
                            if (firstItemPosition + containHalfItem < adapterWanNaviLeft.getItemCount()) {
                                leftRecyclerView.smoothScrollToPosition(firstItemPosition + containHalfItem);
                            } else { // 即将到达顶部，尽可能使已选中的Item保持在屏幕中间
                                leftRecyclerView.smoothScrollToPosition(firstItemPosition);
                            }
                        }

                    } else {    // 右边RecyclerView向上滑动
                        if (nowSelectedPosition > lastVisibleItem) { //将左侧不可见的Item移动至可见
                            leftRecyclerView.smoothScrollToPosition(nowSelectedPosition);
                        } else {
                            if (firstItemPosition - containHalfItem > 0) { // 将左侧可见的已选中Item移动到屏幕中间
                                leftRecyclerView.smoothScrollToPosition(firstItemPosition - containHalfItem);
                            } else {// 即将到达顶部，尽可能使已选中的Item保持在屏幕中间
                                leftRecyclerView.smoothScrollToPosition(0);
                            }
                        }
                    }
                    adapterWanNaviLeft.setSelectedPosition(firstItemPosition);//左边RecyclerView选定点击的Item

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
                LogUtil.i(TAG, "position: " + position);
                adapterWanNaviLeft.setSelectedPosition(position);
                ((LinearLayoutManager) rightRecyclerView.getLayoutManager()).scrollToPositionWithOffset(position, 0);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }


        });
    }

}