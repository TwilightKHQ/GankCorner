package com.gankcorner.Fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class Fragment_Mine extends BaseFragment {

    private String TAG = "========zzq";

    private AdapterMine adapterMine;

    private List<MultipleItem> data = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        initView(view);

        initData();

        return view;
    }

    private void initView(View view) {
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

        //设置banner的高度为手机屏幕的四分之一, banner需要设置为最外层布局
//        mBannerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getHeightPix() / 4));
    }

    private void initData() {
        getNetEaseBanner();
        data.add(new MultipleItem(R.mipmap.music, "本地音乐"));
        data.add(new MultipleItem(R.mipmap.recent_play, "最近播放"));
        data.add(new MultipleItem(R.mipmap.my_star, "我的收藏"));
        data.add(new MultipleItem());
        getNetEaseSongList();

        Log.i(TAG, "getItemCount: " + adapterMine.getItemCount());
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
                data.add(0, new MultipleItem(netEaseBannerList));
                adapterMine.setNewData(data);
            }

            @Override
            public void onFailure(Call<NetEaseBannerBean> call, Throwable t) {

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
                data.addAll(data.size(), multipleItemList);
                adapterMine.setNewData(data);
            }

            @Override
            public void onFailure(Call<NetEaseSongListBean> call, Throwable t) {

            }
        });
    }
    
    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }
    
    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

}
