package com.gankcorner.Entity;

/**
 * Banner实体类
 */
public class BannerList {
    private String title;
    private String imgUrl;
    private String urlPath;

    public BannerList(String title, String imgUrl, String urlPath){
        this.title = title;
        this.imgUrl = imgUrl;
        this.urlPath = urlPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
}
