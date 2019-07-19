package com.gankcorner.Adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gankcorner.Bean.NetEaseBanner;
import com.gankcorner.Entity.MultipleItem;
import com.gankcorner.R;
import com.gankcorner.Utils.BannerImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class AdapterMine extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    private Banner banner;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public AdapterMine(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.TYPE_BANNER, R.layout.mine_type_banner);
        addItemType(MultipleItem.TYPE_TEXT, R.layout.mine_type_text);
        addItemType(MultipleItem.TYPE_TITLE, R.layout.mine_type_title);
        addItemType(MultipleItem.TYPE_PIC, R.layout.mine_type_pic);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TYPE_BANNER:
                banner = helper.getView(R.id.banner);
                initBanner();//设置Banner配置，必须在设置Banner数据之前执行
                initBannerData(item.getNetEaseBannerList());//设置Banner的数据
                break;
            case MultipleItem.TYPE_TEXT:
                helper.setImageResource(R.id.icon, item.getIcon());
                helper.setText(R.id.text, item.getText());
                break;
            case MultipleItem.TYPE_TITLE:
                break;
            case MultipleItem.TYPE_PIC:
                helper.setText(R.id.name, item.getName());
                Glide.with(mContext)
                        .load(item.getCoverImgUrl())
                        .into((ImageView) helper.getView(R.id.coverImgUrl));
                break;
        }
    }

    //设置Banner配置，必须在设置Banner数据之前执行
    private void initBanner() {
        //轮播图的常规设置
        banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器居中显示
        //====加载Banner数据====
        banner.setImageLoader(new BannerImageLoader());//设置图片加载器
        //设置Banner的切换时间
        banner.setDelayTime(6000);
        //设置显示圆形指示器和标题（水平显示）
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
    }

    //设置Banner的数据
    private void initBannerData(List<NetEaseBanner> netEaseBannerList) {
        List<String> images = new ArrayList<String>();
        for (NetEaseBanner netEaseBanner : netEaseBannerList) {
            images.add(netEaseBanner.getPicUrl());
        }
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
}
