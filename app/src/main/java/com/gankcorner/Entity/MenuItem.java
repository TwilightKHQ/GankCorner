package com.gankcorner.Entity;

public class MenuItem {

    private String name;
    private int imageId;

    public MenuItem(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
