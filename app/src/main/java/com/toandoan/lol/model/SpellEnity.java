package com.toandoan.lol.model;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.toandoan.lol.database.dao.SpellsDAO;

/**
 * Created by framgia on 17/11/2016.
 */

public class SpellEnity {
    @SerializedName("id")
    private int id;
    @SerializedName("image")
    private ImageEnity image;
    @SerializedName("description")
    private String description;
    @SerializedName("summonerLevel")
    private int summonerLevel;
    @SerializedName("name")
    private String name;


    public SpellEnity(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex(SpellsDAO.FIELD_ID));
        this.description = cursor.getString(cursor.getColumnIndex(SpellsDAO.FIELD_DESCRIPTION));
        this.name = cursor.getString(cursor.getColumnIndex(SpellsDAO.FIELD_NAME));
        this.image = new ImageEnity();
        this.image.setFull(cursor.getString(cursor.getColumnIndex(SpellsDAO.FIELD_IMAGE)));
        this.summonerLevel = cursor.getInt(cursor.getColumnIndex(SpellsDAO.FIELD_SUMMONER_LEVEL));
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ImageEnity getImage() {
        return image;
    }

    public void setImage(ImageEnity image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
