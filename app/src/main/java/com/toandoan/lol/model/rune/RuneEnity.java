package com.toandoan.lol.model.rune;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.toandoan.lol.database.dao.RunesDAO;
import com.toandoan.lol.model.ImageEnity;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public class RuneEnity {
    @SerializedName("id")
    private int id;
    @SerializedName("description")
    private String description;
    @SerializedName("rune")
    private RuneInfo rune;
    @SerializedName("name")
    private String name;
    @SerializedName("masteries_image")
    private ImageEnity image;

    public RuneEnity(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex(RunesDAO.FIELD_ID));
        this.description = cursor.getString(cursor.getColumnIndex(RunesDAO.FIELD_DESCRIPTION));
        boolean isRune = cursor.getInt(cursor.getColumnIndex(RunesDAO.FIELD_ISRUNE)) == 1 ? true : false;
        int tier = cursor.getInt(cursor.getColumnIndex(RunesDAO.FIELD_TIER));
        String type = cursor.getString(cursor.getColumnIndex(RunesDAO.FIELD_TYPE));
        this.rune = new RuneInfo(type, tier, isRune);
        this.name = cursor.getString(cursor.getColumnIndex(RunesDAO.FIELD_NAME));
        this.image = new ImageEnity();
        this.image.setFull(cursor.getString(cursor.getColumnIndex(RunesDAO.FIELD_IMAGE)));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RuneInfo getRune() {
        return rune;
    }

    public void setRune(RuneInfo rune) {
        this.rune = rune;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageEnity getImage() {
        return image;
    }

    public void setImage(ImageEnity image) {
        this.image = image;
    }
}
