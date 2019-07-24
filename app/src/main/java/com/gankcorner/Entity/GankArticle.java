package com.gankcorner.Entity;

import java.util.List;

public class GankArticle {

    private String who;
    private String desc;
    private String publishedAt;
    private String url;
    private List<String> image;

    public GankArticle(String who, String title, String publishedAt, String url, List<String> image) {
        this.who = who;
        this.desc = title;
        this.publishedAt = publishedAt;
        this.url = url;
        this.image = image;
    }

    public String getWho() {
        return who;
    }

    public String getDesc() {
        return desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getImage() {
        return image;
    }

}
