package com.gankcorner.WanAndroid.WanAndroidFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gankcorner.Adapter.AdapterWanNaviRight;
import com.gankcorner.Adapter.AdapterWanNaviLeft;
import com.gankcorner.Bean.WanNavigation;
import com.gankcorner.Bean.WanNavigationBean;
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

public class FragmentNavigation extends BaseFragment {

    private String TAG = "========zzq";

    private AdapterWanNaviLeft adapterWanNaviLeft;
    AdapterWanNaviRight adapterWanNaviRight;

    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;
    private LinearLayoutManager leftRecyclerManager;
    private LinearLayoutManager rightRecyclerManager;

    private List<WanNavigation> mWanNavigationList = new ArrayList<>();
    private List<WanNavigation> tempList;

    private boolean isScroll = true;//点击左边recycler，右边不监听设置左边的Recycler的选中

    private boolean move = false;

    private int mIndex = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.wanandroid_navigation, container, false);

        initViews(view);
        initLinkageListener();

        return view;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
//        if (isVisible) {
//            //更新界面数据，如果数据还在下载中，就显示加载框
//            if (firstEnter) {
//
//            }
//        }
        initData();
    }

    @Override
    protected void onFragmentFirstVisible() {
        //去服务器下载数据

    }

    private void initViews(View view) {

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
    }

    private void initData() {
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
                Log.i(TAG, "onScrollStateChanged: " + newState);
//                if (move && newState == RecyclerView.SCROLL_STATE_IDLE) { //二次滑动
//                    move = false;
//                    int n = mIndex - rightRecyclerManager.findFirstVisibleItemPosition();
//                    if (0 <= n && n < rightRecyclerView.getChildCount()) {
//                        int top = rightRecyclerView.getChildAt(n).getTop();
//                        rightRecyclerView.smoothScrollBy(0, top);
//                    }
//                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                //用于将左边RecyclerView的内容调整至屏幕中间
                int containHalfItem = (leftRecyclerManager.findLastVisibleItemPosition() -
                        leftRecyclerManager.findFirstVisibleItemPosition()) / 2;

//                Log.i(TAG, "containItem: " + containHalfItem);

                //获取第一个可见view的位置
                int firstItemPosition = rightRecyclerManager.findFirstVisibleItemPosition();

//                Log.i(TAG, "onScrolled: " + firstItemPosition);

//                if (move) { //二次滑动
//                    move = false;
//                    int n = mIndex - rightRecyclerManager.findFirstVisibleItemPosition();
//                    if (0 <= n && n < rightRecyclerView.getChildCount()) {
//                        int top = rightRecyclerView.getChildAt(n).getTop();
//                        rightRecyclerView.scrollBy(0, top);
//                    }
//                }

                if (isScroll) {
                    if (dy > 0) { // 右边RecyclerView向下滑动
                        if (firstItemPosition + containHalfItem < adapterWanNaviLeft.getItemCount()) {
                            leftRecyclerView.smoothScrollToPosition(firstItemPosition + containHalfItem);
                        } else {
                            leftRecyclerView.smoothScrollToPosition(firstItemPosition);
                        }
                    } else {    // 右边RecyclerView向上滑动
                        if (firstItemPosition - containHalfItem > 0) {
                            leftRecyclerView.smoothScrollToPosition(firstItemPosition - containHalfItem);
                        } else {
                            leftRecyclerView.smoothScrollToPosition(0);
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
                Log.i(TAG, "position: " + position);
                adapterWanNaviLeft.setSelectedPosition(position);
                //使用smoothScrollToPosition 首次进入Fragment时 滑动左侧RecyclerView，点击下方Item，右侧自动滑动至最低端
//                rightRecyclerView.smoothScrollToPosition(position);  //滚动到对应的Item
//                move(position);
//                final TopSmoothScroller mScroller = new TopSmoothScroller(getActivity());
//                mScroller.setTargetPosition(position);
//                rightRecyclerManager.startSmoothScroll(mScroller);
                ((LinearLayoutManager)rightRecyclerView.getLayoutManager()).scrollToPositionWithOffset(position,0);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }


        });
    }

    private void move(int position) {
        mIndex = position;
        rightRecyclerView.stopScroll();
        moveToPosition(position); //普通定位
//        smoothMoveToPosition(position); //滑动定位
    }

    //直接指定到某位置
    private void moveToPosition(int n) {

        int firstItem = rightRecyclerManager.findFirstVisibleItemPosition();
        int lastItem = rightRecyclerManager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            rightRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = rightRecyclerView.getChildAt(n - firstItem).getTop();
            rightRecyclerView.scrollBy(0, top);
        } else {
            rightRecyclerView.scrollToPosition(n);
            move = true;
        }

    }

    //滑动到某位置
    private void smoothMoveToPosition(int position) {
        Log.i(TAG, "smoothMoveToPosition: " + position);

        int firstItem = rightRecyclerManager.findFirstVisibleItemPosition();
        int lastItem = rightRecyclerManager.findLastVisibleItemPosition();
        Log.i(TAG, "firstItem: " + firstItem);
        Log.i(TAG, "lastItem: " + lastItem);
        if (position <= firstItem) {
            rightRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            int top = rightRecyclerView.getChildAt(position - firstItem).getTop();
            rightRecyclerView.smoothScrollBy(0, top);
        } else {
            rightRecyclerView.smoothScrollToPosition(position);
            move = true;
        }

    }

    public class TopSmoothScroller extends LinearSmoothScroller {
        TopSmoothScroller(Context context) {
            super(context);
        }

        @Override
        protected int getHorizontalSnapPreference() {
            return SNAP_TO_START;//具体见源码注释
        }

        @Override
        protected int getVerticalSnapPreference() {
            return SNAP_TO_START;//具体见源码注释
        }
    }

}