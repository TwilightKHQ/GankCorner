package com.gankcorner.Entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class MultipleItem implements MultiItemEntity {

    public static final int TYPE_BANNER = 1;
    public static final int TYPE_TEXT = 2;
    public static final int TYPE_TITLE = 3;
    public static final int TYPE_PIC = 4;
    public static final int TYPE_BANNER_SIZE = 3;
    public static final int TYPE_TEXT_SIZE = 3;
    public static final int TYPE_TITLE_SIZE = 3;
    public static final int TYPE_PIC_SIZE = 1;

    private int itemType;
    private int spanSize;

    //轮播台
    private List<NetEaseBanner> netEaseBannerList;
    //本地
    private int icon;
    private String text;
    private String number;
    //歌单
    private String name;
    private String coverImgUrl;

    public MultipleItem(List<NetEaseBanner> netEaseBannerList) {
        this.itemType = MultipleItem.TYPE_BANNER;
        this.netEaseBannerList = netEaseBannerList;
        this.spanSize = TYPE_BANNER_SIZE;
    }

    public MultipleItem(int icon, String text) {
        this.itemType = MultipleItem.TYPE_TEXT;
        this.text = text;
        this.icon = icon;
        this.spanSize = TYPE_TEXT_SIZE;
    }

    public MultipleItem() {
        this.itemType = MultipleItem.TYPE_TITLE;
        this.spanSize = TYPE_TITLE_SIZE;
    }

    public MultipleItem(String name, String coverImgUrl) {
        this.itemType = MultipleItem.TYPE_PIC;
        this.name = name;
        this.coverImgUrl = coverImgUrl;
        this.spanSize = TYPE_PIC_SIZE;
    }

    public List<NetEaseBanner> getNetEaseBannerList() {
        return netEaseBannerList;
    }

    public int getIcon() {
        return icon;
    }

    public String getText() {
        return text;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public int getSpanSize() {
        return spanSize;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
