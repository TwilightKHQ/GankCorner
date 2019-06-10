package com.gankcorner.Bean;

/**
 * Created by frank on 2017/4/14.
 */

public class GroupInfoBean {
    //组号
    private int GroupID;
    // Header 的 title
    private String chapterName;
    private String title;
    private String link;
    //ItemView 在组内的位置
    private int position;
    // 组的成员个数
    private int GroupLength;


    public GroupInfoBean(int groupId, String chapterName, String title, String link) {
        this.GroupID = groupId;
        this.chapterName = chapterName;
        this.title = title;
        this.link = link;
    }

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int groupID) {
        this.GroupID = groupID;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isFirstViewInGroup () {
        return position == 0;
    }

    public boolean isLastViewInGroup () {
        return position == GroupLength - 1 && position >= 0;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setGroupLength(int groupLength) {
        this.GroupLength = groupLength;
    }
}
