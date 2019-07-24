package com.gankcorner.Entity;

import java.util.List;

public class WanNavigation {

    private String name;
    private List<String> tagList;
    private List<String> urlList;

    public WanNavigation(String name, List<String> tagList, List<String> urlList) {
        this.name = name;
        this.tagList = tagList;
        this.urlList = urlList;
    }

    public String getName() {
        return name;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public List<String> getUrlList() {
        return urlList;
    }
}
