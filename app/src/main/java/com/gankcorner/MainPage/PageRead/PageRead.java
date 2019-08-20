package com.gankcorner.MainPage.PageRead;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.gankcorner.Adapter.AdapterFragment;
import com.gankcorner.Entity.ReadType;
import com.gankcorner.R;
import com.gankcorner.Utils.BaseFragment;
import com.gankcorner.Utils.LogUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageRead extends BaseFragment implements View.OnClickListener {

    private String TAG = PageRead.class.getSimpleName();

    private View view;

    private List<ReadType> typeList;

    private List<String> titleList;
    private List<Fragment> fragmentList;

    private boolean hasData = false;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0://更新UI
                    initView();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.page_read, container, false);

        LogUtil.i(TAG, "Created");

        if (hasData) {
            initView();
        }

        return view;
    }

    @Override
    public void onFirstVisibleToUser() {
        getData();
    }

    private void getData() {

        final String API_URL = "http://gank.io/xiandu/";

        LogUtil.d(TAG, "获取闲读的所有栏目分类...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //解析 html
                    Document doc = Jsoup.connect(API_URL).get();
                    Element element = doc.getElementById("xiandu_cat");
                    LogUtil.i(TAG, element.html());
                    Elements elements = element.getElementsByTag("a");
                    typeList = new ArrayList<>();
                    for (Element e : elements) {
                        ReadType entity = new ReadType();
                        entity.setName(e.text());
                        entity.setType(e.attr("href").substring(e.attr("href").lastIndexOf("/") + 1));
                        typeList.add(entity);
                    }
                    LogUtil.d(TAG, "获取成功: " + typeList.size());
                    handler.sendEmptyMessage(0);
                    hasData = true;
                } catch (IOException e) {
                    LogUtil.e(TAG, "获取失败, " + e.getMessage());
                }
            }
        }).start();
    }

    private void initView() {
        ImageView changeItem = (ImageView) view.findViewById(R.id.change_item);
        changeItem.setOnClickListener(this);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);

        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        for (int i = 0; i < typeList.size(); i++) {
            titleList.add(typeList.get(i).getName());
            fragmentList.add(new FragmentReading());
        }

        AdapterFragment adapterFragment = new
                AdapterFragment(getChildFragmentManager(), getContext(), fragmentList, titleList);

        //在Fragment当中
        viewPager.setAdapter(adapterFragment);
        //将TabLayout与ViewPager联系起来
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_item:
                Toast.makeText(getContext(), "改变Tab", Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }
}
