package com.gankcorner.WanAndroid.WanAndroidFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gankcorner.ActivityWeb;
import com.gankcorner.Adapter.AdapterWanArticle;
import com.gankcorner.Adapter.AdapterWanArticleTest;
import com.gankcorner.Bean.BannerBean;
import com.gankcorner.Bean.BannerList;
import com.gankcorner.Bean.WanArticle;
import com.gankcorner.Bean.WanArticleBean;
import com.gankcorner.Interface.WanAndroid;
import com.gankcorner.R;
import com.gankcorner.Utils.BannerImageLoader;
import com.gankcorner.Utils.CommonUtils;
import com.gankcorner.Utils.ContextUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.gankcorner.Utils.CommonUtils.getHeightPix;

public class FragmentNavigationTest extends Fragment {

    private Banner mBannerView;
    private List<BannerList> mBannerListList;

    private AdapterWanArticleTest adapterWanArticle;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<WanArticle> mWanArticleList = new ArrayList<>();

    private int numPerPage = 20;
    private int currentPage = 0;            //当前页数

    private boolean gettingData = false; //当前是否正在请求数据

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.wanandroid_home, container, false);

        initViews(view);
        initClickEvents();
        initData();

        return view;
    }

    private void initViews(View view) {

        // 初始化控件
        mRecyclerView = view.findViewById(R.id.left_recycle_view);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        // 设置线性布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 设置adapter
        adapterWanArticle = new AdapterWanArticleTest(R.layout.item_wanandroid, mWanArticleList);
//        //设置HeaderView
        View headerView = getHeaderView(0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterWanArticle.addHeaderView(getHeaderView(1, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "banner", Toast.LENGTH_SHORT).show();
                    }
                }), 0);
            }
        });
        //设置banner的高度为手机屏幕的四分之一
//        mBannerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getHeightPix() / 4));
//        adapterWanArticle.setHeaderView(mBannerView);
        mRecyclerView.setAdapter(adapterWanArticle);
        adapterWanArticle.addHeaderView(headerView);
        // 快要滑动到底部时自动加载更多
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (CommonUtils.isWillBottom(recyclerView) && !gettingData) {
                    getWanAndroidArticle(currentPage);
                    Log.d("currentPage", "onScrollStateChanged: " + currentPage);
                }
            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(R.color.bilibili);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWanAndroidArticle(0);
            }
        });
    }

    private void initClickEvents() {
        adapterWanArticle.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(), "123456", Toast.LENGTH_SHORT).show();
            }
        });

        adapterWanArticle.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.chapterName:
                        Toast.makeText(getContext(), "987564132", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.card_view:
                        Intent intent = new Intent(getContext(), ActivityWeb.class);
                        //在滑动gankArticle会的值会变化，导致传递的字符串不对
//                Log.d("Url", "Position"+ position + " Desc0: " + gankArticle.getDesc());
//                Log.d("Url", "Position"+ position + " Desc: " + mGankArticleList.get(position).getDesc());
                        intent.putExtra("page_desc",
                                mWanArticleList.get(position).getTitle());
                        intent.putExtra("page_url",
                                mWanArticleList.get(position).getLink());
                        getContext().startActivity(intent);
                        break;
                    case R.id.home_share:
                        Toast.makeText(ContextUtil.getContext(),
                                "分享：" + mWanArticleList.get(position).getTitle(),
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.more_item:
                        Toast.makeText(ContextUtil.getContext(),
                                "更多：" + mWanArticleList.get(position).getAuthor(),
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    private void initData() {
        getBannerList();
        getWanAndroidArticle(0);
    }

    //获取WanAndroid的文章数据
    private void getWanAndroidArticle(int page) {
        gettingData = true;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WanAndroid requestWanArticle = retrofit.create(WanAndroid.class);

        Call<WanArticleBean> call = requestWanArticle.getArticleList(page);

        call.enqueue(new Callback<WanArticleBean>() {
            @Override
            public void onResponse(@NonNull Call<WanArticleBean> call, @NonNull Response<WanArticleBean> response) {
                Log.d("Test", "response: " + response.toString());
                //完成解析后可以直接获取数据
                WanArticleBean wanArticleBean = response.body();
                String Desc = null;
                int size = 0;
                if (wanArticleBean != null) {
                    Desc = wanArticleBean.getData().getDatas().get(0).getTitle();
                    size = wanArticleBean.getData().getDatas().size();
                }
                Log.d("Test", "String: " + Desc + " Size:" + size);
                currentPage = wanArticleBean.getData().getCurPage();
                Log.d("currentPage", "onResponse: " + currentPage);
                addArticleData(wanArticleBean);
                mSwipeRefreshLayout.setRefreshing(false);
                gettingData = false;
            }

            @Override
            public void onFailure(@NonNull Call<WanArticleBean> call, @NonNull Throwable t) {

            }
        });
    }

    //添加WanAndroid的文章数据
    private void addArticleData(WanArticleBean wanArticleBean) {
        mWanArticleList = new ArrayList<>();
        for (int i = 0; i < numPerPage; i++) {
            WanArticleBean.DataBean.DatasBean dateBean = wanArticleBean.getData().getDatas().get(i);
            WanArticle wanArticle = new WanArticle(dateBean.getAuthor(), dateBean.getChapterName(),
                    dateBean.getLink(), dateBean.getNiceDate(), dateBean.getSuperChapterName(), dateBean.getTitle());
//            Log.d("GankArticle", "addData: " + wanArticle.getAuthor());
            mWanArticleList.add(wanArticle);
        }
        if (mSwipeRefreshLayout.isRefreshing()) {
            adapterWanArticle.setNewData(mWanArticleList);
        } else {
            adapterWanArticle.addData(mWanArticleList);
        }
    }

    //获取Banner的网络数据
    private void getBannerList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WanAndroid requestWanBanner = retrofit.create(WanAndroid.class);

        Call<BannerBean> call = requestWanBanner.getBannerList();

        call.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                Log.d("Test", "response: " + response.toString());
                //完成解析后可以直接获取数据
                BannerBean bannerBean = response.body();
                String Desc = null;
                if (bannerBean != null) {
                    Desc = bannerBean.getData().get(0).getDesc();
                }
                Log.d("Test", "UpdateInfo: " + Desc);
//                SetBanner(bannerBean);
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {

            }
        });
    }

    //配置Banner
    private void SetBanner(BannerBean bannerBean) {
        initBanner();//设置Banner配置，必须在设置Banner数据之前执行
        initBannerData(bannerBean);//设置Banner的数据
        initBannerEvent();//设置Banner监听事件
    }

    //设置Banner配置，必须在设置Banner数据之前执行
    private void initBanner() {
        //轮播图的常规设置
        mBannerView.setIndicatorGravity(BannerConfig.CENTER);//设置指示器局右显示
        //====加载Banner数据====
        mBannerView.setImageLoader(new BannerImageLoader());//设置图片加载器
        //设置Banner的切换时间
        mBannerView.setDelayTime(5000);
        //设置显示圆形指示器和标题（水平显示）
        mBannerView.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
    }

    //设置Banner的数据
    private void initBannerData(BannerBean bannerBean) {
        mBannerListList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            BannerBean.DataBean dataBean = bannerBean.getData().get(i);
            BannerList bannerList = new BannerList(dataBean.getTitle(), dataBean.getImagePath(),
                    dataBean.getUrl());
//            Log.d("GankArticle", "SetBanner: " + dataBean.getTitle());
            mBannerListList.add(bannerList);
        }
        List<String> images = new ArrayList<String>();
        List<String> titles = new ArrayList<String>();
        for (BannerList bannerList : mBannerListList) {
            images.add(bannerList.getImgUrl());
            titles.add(bannerList.getTitle());
//            Log.d("Title", "initBannerData: " + bannerList.getTitle());
        }
        mBannerView.setImages(images);
        mBannerView.setBannerTitles(titles);
        //banner设置方法全部调用完毕时最后调用
        mBannerView.start();
    }

    //设置Banner监听事件
    private void initBannerEvent() {
        //设置banner的滑动监听
        mBannerView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //设置点击事件，下标是从0开始
        mBannerView.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getContext(), ActivityWeb.class);
                intent.putExtra("page_desc", mBannerListList.get(position).getTitle());
                intent.putExtra("page_url", mBannerListList.get(position).getUrlPath());
                getContext().startActivity(intent);
            }
        });
    }

    private View getHeaderView(int type, View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.item_banner_test, (ViewGroup) mRecyclerView.getParent(), false);
        mBannerView = view.findViewById(R.id.banner);
        view.setOnClickListener(listener);
        return view;
    }

}