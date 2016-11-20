package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by framgia on 17/11/2016.
 */

public class BannedChampion implements Serializable {
    @SerializedName("championId")
    private int mChampionId;

    @SerializedName("pickTurn")
    private int mPickTurn;

    public int getPickTurn() {
        return mPickTurn;
    }

    public void setPickTurn(int pickTurn) {
        mPickTurn = pickTurn;
    }

    public int getChampionId() {
        return mChampionId;
    }

    public void setChampionId(int championId) {
        mChampionId = championId;
    }
}
