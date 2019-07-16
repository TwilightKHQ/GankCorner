package com.gankcorner.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.gankcorner.Bean.WanArticleBean;
import com.gankcorner.Interface.Gank;
import com.gankcorner.Interface.WanAndroid;
import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;
import com.gankcorner.Utils.BottomDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_Two extends BaseFragment {

    private String TAG = "=======zzq";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        Button button = (Button) view.findViewById(R.id.button_center);
        button.setText("Fragment_Two");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomDialogFragment bottomDialogFragment = new BottomDialogFragment();
                bottomDialogFragment.show(getActivity().getSupportFragmentManager(), "bottomDialogFragment");

                getSearchList();
            }
        });

        Log.i("========zzq", "Fragment_Two_isVisibleToUser: " + isVisibleToUser());

        return view;
    }

    private void getSearchList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WanAndroid postSearchInfo = retrofit.create(WanAndroid.class);

        Call<WanArticleBean> call = postSearchInfo.getSearchList(0, "问题");

        call.enqueue(new Callback<WanArticleBean>() {
            @Override
            public void onResponse(Call<WanArticleBean> call, Response<WanArticleBean> response) {
                Log.d(TAG, "Post_response: " + response.toString());
                WanArticleBean wanArticleBean = response.body();
                String Desc = null;
                int size = 0;
                if (wanArticleBean != null) {
                    Desc = wanArticleBean.getData().getDatas().get(0).getTitle();
                    size = wanArticleBean.getData().getDatas().size();
                }
                Log.d(TAG, "String: " + Desc + " Size:" + size);
            }

            @Override
            public void onFailure(Call<WanArticleBean> call, Throwable t) {

            }
        });

    }

}
