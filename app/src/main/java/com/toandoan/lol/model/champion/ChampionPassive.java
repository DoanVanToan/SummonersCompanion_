package com.toandoan.lol.model.champion;

import com.toandoan.lol.model.ImageEnity;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public class ChampionPassive {
    private String name;
    private String description;
    private ImageEnity image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageEnity getImage() {
        return image;
    }

    public void setImage(ImageEnity image) {
        this.image = image;
    }
}
