package com.example.olxclone.entity;

import android.media.Image;

public class Category {

    private String name;
    private Image icon;

    public Category(String name, Image icon){
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }
}