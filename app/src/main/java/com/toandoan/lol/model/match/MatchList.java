package com.toandoan.lol.model.match;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by framgia on 17/11/2016.
 */

public class MatchList {
    @SerializedName("endIndex")
    private int mEndIndex;
    @SerializedName("matches")
    private List<MatchReference> mMatches;
    @SerializedName("startIndex")
    private int mStartIndex;
    @SerializedName("totalGames")
    private int mTotalGames;

    public int getEndIndex() {
        return mEndIndex;
    }

    public void setEndIndex(int endIndex) {
        mEndIndex = endIndex;
    }

    public List<MatchReference> getMatches() {
        return mMatches;
    }

    public void setMatches(List<MatchReference> matches) {
        mMatches = matches;
    }

    public int getStartIndex() {
        return mStartIndex;
    }

    public void setStartIndex(int startIndex) {
        mStartIndex = startIndex;
    }

    public int getTotalGames() {
        return mTotalGames;
    }

    public void setTotalGames(int totalGames) {
        mTotalGames = totalGames;
    }
}
