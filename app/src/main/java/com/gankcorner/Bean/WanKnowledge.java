package com.gankcorner.Bean;

import java.util.List;

public class WanKnowledge {

    private String name;
    private List<String> tagList;

    public WanKnowledge(String name, List<String> tagList) {
        this.name = name;
        this.tagList = tagList;
    }

    public String getName() {
        return name;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
