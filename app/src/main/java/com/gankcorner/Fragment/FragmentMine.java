package com.gankcorner.Fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gankcorner.Adapter.AdapterMine;
import com.gankcorner.Bean.NetEaseBanner;
import com.gankcorner.Bean.NetEaseBannerBean;
import com.gankcorner.Bean.NetEaseSongListBean;
import com.gankcorner.Entity.MultipleItem;
import com.gankcorner.Interface.NetEase;
import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentMine extends BaseFragment {

    private String TAG = "========zzq";

    private AdapterMine adapterMine;

    private List<MultipleItem> data = new ArrayList<>();
    private List<MultipleItem> tempData;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private GridLayoutManager mLayoutManager;

    private int gettingDataNumber = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        initView(view);

        return view;
    }

    @Override
    public void onFirstVisibleToUser() {
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }

    private void initView(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.bilibili);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapterMine = new AdapterMine(data);
        adapterMine.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });
        mRecyclerView.setAdapter(adapterMine);
    }

    private void refresh() {
        gettingDataNumber = 2;
        tempData = new ArrayList<>();
        getNetEaseBanner();
        tempData.add(new MultipleItem(R.mipmap.music, "本地音乐"));
        tempData.add(new MultipleItem(R.mipmap.recent_play, "最近播放"));
        tempData.add(new MultipleItem(R.mipmap.my_star, "我的收藏"));
        tempData.add(new MultipleItem()); //推荐歌单 title
        getNetEaseSongList();
    }

    private void getNetEaseBanner() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://v1.itooi.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetEase getBanner = retrofit.create(NetEase.class);

        Call<NetEaseBannerBean> call = getBanner.getNetEaseBanner();

        call.enqueue(new Callback<NetEaseBannerBean>() {
            @Override
            public void onResponse(Call<NetEaseBannerBean> call, Response<NetEaseBannerBean> response) {
                Log.d(TAG, "One_response: " + response.toString());
                NetEaseBannerBean netEaseBannerBean = response.body();
                String Desc = null;
                String size = null;
                if (netEaseBannerBean != null) {
                    Desc = netEaseBannerBean.getData().get(0).getUrl();
                    size = netEaseBannerBean.getData().get(0).getPicUrl();
                }
                Log.d(TAG, "Desc: " + Desc + " Size:" + size);
                Log.i(TAG, "Data Size: " + data.size());
                List<NetEaseBanner> netEaseBannerList = new ArrayList<>();
                for (NetEaseBannerBean.DataBean dataBean : netEaseBannerBean.getData()) {
                    netEaseBannerList.add(new NetEaseBanner(dataBean.getUrl(),
                            dataBean.getPicUrl()));
                }
                tempData.add(0, new MultipleItem(netEaseBannerList));
                if ( --gettingDataNumber == 0) {
                    data = tempData;
                    adapterMine.setNewData(data);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<NetEaseBannerBean> call, Throwable t) {
                Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getNetEaseSongList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://v1.itooi.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetEase getSongList = retrofit.create(NetEase.class);

        Call<NetEaseSongListBean> call = getSongList.getNetEaseSongList(9, 0);

        call.enqueue(new Callback<NetEaseSongListBean>() {
            @Override
            public void onResponse(Call<NetEaseSongListBean> call, Response<NetEaseSongListBean> response) {
                Log.d(TAG, "One_response: " + response.toString());
                NetEaseSongListBean netEaseSongListBean = response.body();
                String Desc = null;
                String size = null;
                if (netEaseSongListBean != null) {
                    Desc = netEaseSongListBean.getData().get(0).getName();
                    size = netEaseSongListBean.getData().get(0).getCoverImgUrl();
                }
                Log.d(TAG, "Desc: " + Desc + " Size:" + size);
                List<MultipleItem> multipleItemList = new ArrayList<>();
                for (NetEaseSongListBean.DataBean dataBean : netEaseSongListBean.getData()) {
                    multipleItemList.add(new MultipleItem(dataBean.getName(),
                            dataBean.getCoverImgUrl()));
                }
                tempData.addAll(tempData.size(), multipleItemList);
                if ( --gettingDataNumber == 0) {
                    data = tempData;
                    adapterMine.setNewData(data);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<NetEaseSongListBean> call, Throwable t) {
                Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
