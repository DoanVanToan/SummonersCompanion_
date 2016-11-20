package com.toandoan.lol.model.match;

import com.google.gson.annotations.SerializedName;

/**
 * Created by framgia on 17/11/2016.
 */

public class MatchReference {
    @SerializedName("champion")
    private long mChampion;
    @SerializedName("lane")
    private String mLane;
    @SerializedName("matchId")
    private long mMatchId;
    @SerializedName("platformId")
    private String mPlatformId;
    @SerializedName("queue")
    private String mQueue;
    @SerializedName("region")
    private String mRegion;
    @SerializedName("role")
    private String mRole;
    @SerializedName("season")
    private String mSeason;

    public long getChampion() {
        return mChampion;
    }

    public void setChampion(long champion) {
        mChampion = champion;
    }

    public String getLane() {
        return mLane;
    }

    public void setLane(String lane) {
        mLane = lane;
    }

    public long getMatchId() {
        return mMatchId;
    }

    public void setMatchId(long matchId) {
        mMatchId = matchId;
    }

    public String getPlatformId() {
        return mPlatformId;
    }

    public void setPlatformId(String platformId) {
        mPlatformId = platformId;
    }

    public String getQueue() {
        return mQueue;
    }

    public void setQueue(String queue) {
        mQueue = queue;
    }

    public String getRegion() {
        return mRegion;
    }

    public void setRegion(String region) {
        mRegion = region;
    }

    public String getRole() {
        return mRole;
    }

    public void setRole(String role) {
        mRole = role;
    }

    public String getSeason() {
        return mSeason;
    }

    public void setSeason(String season) {
        mSeason = season;
    }
}
