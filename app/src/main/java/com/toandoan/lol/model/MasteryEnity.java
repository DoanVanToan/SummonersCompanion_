package com.toandoan.lol.model;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.toandoan.lol.database.dao.MasteryDAO;
import com.toandoan.lol.utility.Utils;

import java.util.List;

/**
 * Created by ToanDoan on 10/30/2016.
 */

public class MasteryEnity {
    @SerializedName("id")
    private int id;
    @SerializedName("ranks")
    private int ranks;
    @SerializedName("sanitizedDescription")
    private List<String> sanitizedDescription;
    @SerializedName("description")
    private List<String> description;
    @SerializedName("name")
    private String name;
    @SerializedName("masteries_image")
    private ImageEnity image;
    @SerializedName("prereq")
    private int prereq;
    @SerializedName("masteryTree")
    private String masteryTree;

    public MasteryEnity(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex(MasteryDAO.FIELD_ID));
        this.ranks = cursor.getInt(cursor.getColumnIndex(MasteryDAO.FIELD_RANKS));
        this.sanitizedDescription = Utils.getListFromString(cursor.getString(cursor.getColumnIndex(MasteryDAO.FIELD_SANITIZEDDESCRIPTION)));
        this.description = Utils.getListFromString(cursor.getString(cursor.getColumnIndex(MasteryDAO.FIELD_DESCRIPTION)));
        this.name = cursor.getString(cursor.getColumnIndex(MasteryDAO.FIELD_NAME));
        this.image = new ImageEnity();
        this.image.setFull(cursor.getString(cursor.getColumnIndex(MasteryDAO.FIELD_IMAGE)));
        this.prereq = cursor.getInt(cursor.getColumnIndex(MasteryDAO.FIELD_PREREQ));
        this.masteryTree = cursor.getString(cursor.getColumnIndex(MasteryDAO.FIELD_MASTERYTREE));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRanks() {
        return ranks;
    }

    public void setRanks(int ranks) {
        this.ranks = ranks;
    }

    public List<String> getSanitizedDescription() {
        return sanitizedDescription;
    }

    public void setSanitizedDescription(List<String> sanitizedDescription) {
        this.sanitizedDescription = sanitizedDescription;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
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

    public int getPrereq() {
        return prereq;
    }

    public void setPrereq(int prereq) {
        this.prereq = prereq;
    }

    public String getMasteryTree() {
        return masteryTree;
    }

    public void setMasteryTree(String masteryTree) {
        this.masteryTree = masteryTree;
    }
}
