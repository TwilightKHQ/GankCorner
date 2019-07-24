package com.gankcorner.Entity;

/**
 * Banner实体类
 */
public class NetEaseSongList {

    private String name;
    private String coverImgUrl;
    

    public NetEaseSongList(String coverImgUrl, String name){
        this.coverImgUrl = coverImgUrl;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }
}
