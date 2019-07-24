package com.gankcorner.Entity;

/**
 * Banner实体类
 */
public class NetEaseBanner {

    private String url;
    private String picUrl;


    public NetEaseBanner(String url, String picUrl){
        this.picUrl = picUrl;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
