package com.gankcorner.Entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.gankcorner.Bean.NetEaseBanner;
import com.gankcorner.Bean.NetEaseSongList;

import java.util.List;

public class MultipleItem implements MultiItemEntity {

    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;
    public static final int TYPE_FOUR = 4;
    public static final int TYPE_ONE_SIZE = 3;
    public static final int TYPE_TWO_SIZE = 3;
    public static final int TYPE_THREE_SIZE = 3;
    public static final int TYPE_FOUR_SIZE = 1;

    private int itemType;
    private int spanSize;

    //轮播台
    private List<NetEaseBanner> netEaseBannerList;
    //本地
    private int icon;
    private String text;
    private String number;
    //歌单
    private List<NetEaseSongList> netEaseSongListList;
    private String name;
    private String coverImgUrl;

    public MultipleItem(List<NetEaseBanner> netEaseBannerList) {
        this.itemType = MultipleItem.TYPE_ONE;
        this.netEaseBannerList = netEaseBannerList;
        this.spanSize = TYPE_ONE_SIZE;
    }

    public MultipleItem(int icon, String text) {
        this.itemType = MultipleItem.TYPE_TWO;
        this.text = text;
        this.icon = icon;
        this.spanSize = TYPE_TWO_SIZE;
    }

    public MultipleItem() {
        this.itemType = MultipleItem.TYPE_THREE;
        this.spanSize = TYPE_THREE_SIZE;
    }

    public MultipleItem(String name, String coverImgUrl) {
        this.itemType = MultipleItem.TYPE_FOUR;
        this.name = name;
        this.coverImgUrl = coverImgUrl;
        this.spanSize = TYPE_FOUR_SIZE;
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

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
