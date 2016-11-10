package com.toandoan.lol.model;

import com.google.gson.JsonObject;
import com.toandoan.lol.constant.Constant;

import org.json.JSONObject;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public class UserEnity {
    private int id;
    private String name;
    private int profileIconId;
    private long revisionDate;
    private int summonerLevel;

    public UserEnity(JSONObject jsonObject) {
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
}
