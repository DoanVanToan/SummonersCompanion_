package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by framgia on 17/11/2016.
 */

public class Rune implements Serializable {
    @SerializedName("rank")
    private long mRank;

    @SerializedName("runeId")
    private long mRuneId;

    public long getRank() {
        return mRank;
    }

    public void setRank(long rank) {
        mRank = rank;
    }

    public long getRuneId() {
        return mRuneId;
    }

    public void setRuneId(long runeId) {
        mRuneId = runeId;
    }
}
