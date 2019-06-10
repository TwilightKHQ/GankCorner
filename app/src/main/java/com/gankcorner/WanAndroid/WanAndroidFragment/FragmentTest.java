package com.gankcorner.WanAndroid.WanAndroidFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gankcorner.Bean.GroupInfoBean;
import com.gankcorner.Adapter.AdapterTest;
import com.gankcorner.Bean.WanNavigationBean;
import com.gankcorner.Interface.WanAndroid;
import com.gankcorner.R;
import com.gankcorner.Utils.ItemDecorationSticky;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentTest extends Fragment {
    RecyclerView mRecyclerView;
    List<String> data;
    AdapterTest mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.wanandroid_test, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        getWanNavigationList();

        mAdapter = new AdapterTest(data);
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        ItemDecorationSticky.GroupInfoCallback callback = new ItemDecorationSticky.GroupInfoCallback() {
            @Override
            public GroupInfoBean getGroupInfo(int position) {

                /*
                 * 分组逻辑，这里为了测试每5个数据为一组。大家可以在实际开发中
                 * 替换为真正的需求逻辑
                 */
                int groupId = position / 5;
                int index = position % 5;
                GroupInfoBean groupInfoBean = new GroupInfoBean(groupId, groupId + "", "11", "22");
                groupInfoBean.setGroupLength(5);
                groupInfoBean.setPosition(index);
                return groupInfoBean;
            }
        };
        mRecyclerView.addItemDecoration(new ItemDecorationSticky(getContext(), callback));

        return view;
    }

    //获取WanAndroid的导航数据
    private void getWanNavigationList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WanAndroid requestWanNavigation = retrofit.create(WanAndroid.class);

        Call<WanNavigationBean> call = requestWanNavigation.getNavigationList();

        call.enqueue(new Callback<WanNavigationBean>() {
            @Override
            public void onResponse(Call<WanNavigationBean> call, Response<WanNavigationBean> response) {
                Log.d("Test", "response: " + response.toString());
                //完成解析后可以直接获取数据
                WanNavigationBean wanNavigationBean = response.body();
                String Desc = null;
                if (wanNavigationBean != null) {
                    Desc = wanNavigationBean.getData().get(0).getName();
                }
                Log.d("Test", "UpdateInfo: " + Desc);
                data = new ArrayList<>();
                for (WanNavigationBean.DataBean dataBean : wanNavigationBean.getData()) {
                    for (WanNavigationBean.DataBean.ArticlesBean articlesBean : dataBean.getArticles()) {
                        data.add(articlesBean.getTitle());
                    }
                }
                mAdapter.setData(data);
            }

            @Override
            public void onFailure(@NonNull Call<WanNavigationBean> call, @NonNull Throwable t) {

            }
        });
    }

}
