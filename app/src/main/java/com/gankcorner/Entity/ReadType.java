package com.gankcorner.Entity;

/**
 * Created by dasu on 2017/4/21.
 *
 * 闲读的分类（包括科技咨询等等）
 */

public class ReadType {

    private String type; //英文类别
    private String name; //类别对应的中文名

    public ReadType() {
    }

    public ReadType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        // 首页http://gank.io/xiandu/wow
        return type.equals("xiandu") ? "wow" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TypeEntity{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
