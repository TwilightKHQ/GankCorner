package com.gankcorner.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.gankcorner.Bean.BannerBean;
import com.gankcorner.R;
import com.gankcorner.Utils.BannerImageLoader;
import com.gankcorner.Utils.BottomDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Three extends Fragment {

    private List<BannerBean> mBannerBeanList;

    private Banner mBannerView;

    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.wanandroid_layout, container, false);


        initViews(view);
        initDatas();

        button.setText("Fragment_Three");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomDialog bottomDialog = new BottomDialog(getContext(), R.style.BottomDialog);
                bottomDialog.show();
            }
        });

        return view;
    }

    private void initViews(View view) {
        button = (Button) view.findViewById(R.id.button_center);
        mBannerView = view.findViewById(R.id.banner);
    }

    private void initDatas() {
        mBannerBeanList = new ArrayList<BannerBean>();
        //这里手动添加一些测试数据
        BannerBean bannerBean1 = new BannerBean();
        bannerBean1.setTitle("西昌铁路警方用表情包宣传爱路小知识");
        bannerBean1.setImgUrl("https://b.bdstatic.com/boxlib/20180120/2018012017100383423448679.jpg");
        bannerBean1.setUrlPath("http://pic.chinadaily.com.cn/2018-01/20/content_35544757.htm");

        BannerBean bannerBean2 = new BannerBean();
        bannerBean2.setTitle("成都熊猫基地太阳产房全新升级");
        bannerBean2.setImgUrl("https://b.bdstatic.com/boxlib/20180120/2018012017100311270281486.jpg");
        bannerBean2.setUrlPath("http://pic.chinadaily.com.cn/2018-01/20/content_35544758.htm");

        BannerBean bannerBean3 = new BannerBean();
        bannerBean3.setTitle("长沙“90后”交警用手绘记录交警故事");
        bannerBean3.setImgUrl("https://b.bdstatic.com/boxlib/20180120/2018012017100392134086973.jpg");
        bannerBean3.setUrlPath("http://pic.chinadaily.com.cn/2018-01/20/content_35544759.htm");

        mBannerBeanList.add(bannerBean1);
        mBannerBeanList.add(bannerBean2);
        mBannerBeanList.add(bannerBean3);

        initBanner();//设置Banner配置，必须在设置Banner数据之前执行
        initBannerData();//设置Banner的数据
        initBannerEvent();//设置Banner监听事件
    }

    //设置Banner配置，必须在设置Banner数据之前执行
    private void initBanner() {
        //轮播图的常规设置
        mBannerView.setIndicatorGravity(BannerConfig.RIGHT);//设置指示器局右显示
        //====加载Banner数据====
        mBannerView.setImageLoader(new BannerImageLoader());//设置图片加载器
        //设置Banner的切换时间
        mBannerView.setDelayTime(5000);
        //设置显示圆形指示器和标题（水平显示）
        mBannerView.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
    }

    //设置Banner的数据
    private void initBannerData() {
        List<String> images = new ArrayList<String>();
        List<String> titles = new ArrayList<String>();
        for (BannerBean bannerBean : mBannerBeanList) {
            images.add(bannerBean.getImgUrl());
            titles.add(bannerBean.getTitle());
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
                String urlPath = mBannerBeanList.get(position).getUrlPath();
                Toast.makeText(getContext(), urlPath, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
