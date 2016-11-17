package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

/**
 * Created by framgia on 17/11/2016.
 */

public class Player {
    @SerializedName("matchHistoryUri")
    private String mMatchHistoryUri;

    @SerializedName("profileIcon")
    private int mProfileIcon;

    @SerializedName("summonerId")
    private long mSummonerId;

    @SerializedName("summonerName")
    private String mSummonerName;

    public String getMatchHistoryUri() {
        return mMatchHistoryUri;
    }

    public void setMatchHistoryUri(String matchHistoryUri) {
        mMatchHistoryUri = matchHistoryUri;
    }

    public int getProfileIcon() {
        return mProfileIcon;
    }

    public void setProfileIcon(int profileIcon) {
        mProfileIcon = profileIcon;
    }

    public long getSummonerId() {
        return mSummonerId;
    }

    public void setSummonerId(long summonerId) {
        mSummonerId = summonerId;
    }

    public String getSummonerName() {
        return mSummonerName;
    }

    public void setSummonerName(String summonerName) {
        mSummonerName = summonerName;
    }
}
