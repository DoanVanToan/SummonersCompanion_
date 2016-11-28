package com.toandoan.lol.model;

import android.database.Cursor;

import com.google.gson.JsonObject;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.database.dao.MyItemDAO;
import com.toandoan.lol.database.dao.SumonersDAO;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public class SumonerEnity implements Serializable {
    private int id;
    private String name;
    private int profileIconId;
    private long revisionDate;
    private int summonerLevel;
    private String region;

    public SumonerEnity() {
    }

    public SumonerEnity(JSONObject jsonObject) {
        if (!jsonObject.isNull(Constant.ApiKey.ID)) {
            id = jsonObject.optInt(Constant.ApiKey.ID);
        }
        if (!jsonObject.isNull(Constant.ApiKey.NAME)) {
            name = jsonObject.optString(Constant.ApiKey.NAME);
        }
        if (!jsonObject.isNull(Constant.ApiKey.PROFILE_ICON_ID)) {
            profileIconId = jsonObject.optInt(Constant.ApiKey.PROFILE_ICON_ID);
        }
        if (!jsonObject.isNull(Constant.ApiKey.REVISION_DATE)) {
            revisionDate = jsonObject.optInt(Constant.ApiKey.REVISION_DATE);
        }
        if (!jsonObject.isNull(Constant.ApiKey.SUMMONER_LEVEL)) {
            summonerLevel = jsonObject.optInt(Constant.ApiKey.SUMMONER_LEVEL);
        }
    }

    public SumonerEnity(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex(SumonersDAO.FIELD_ID));
        this.name = cursor.getString(cursor.getColumnIndex(SumonersDAO.FIELD_NAME));
        this.profileIconId = cursor.getInt(cursor.getColumnIndex(SumonersDAO.FIELD_PROFILE_ICON_ID));
        this.revisionDate = cursor.getInt(cursor.getColumnIndex(SumonersDAO.FIELD_REVISION_DATE));
        this.summonerLevel = cursor.getInt(cursor.getColumnIndex(SumonersDAO.FIELD_SUMMONER_LEVEL));
        this.region = cursor.getString(cursor.getColumnIndex(SumonersDAO.FIELD_REGION));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
