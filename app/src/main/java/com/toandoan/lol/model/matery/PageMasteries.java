package com.toandoan.lol.model.matery;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by framgia on 10/11/2016.
 */

public class PageMasteries {
    @SerializedName("masteries")
    private List<MasteryEnity> mMasteries;
    @SerializedName("id")
    private int mID;
    @SerializedName("name")
    private String mName;
    @SerializedName("current")
    private boolean mIsCurrent;

    public int getID() {
        return mID;
    }

    public void setID(int mID) {
        this.mID = mID;
    }

    public List<MasteryEnity> getMasteries() {
        return mMasteries;
    }

    public void setMasteries(List<MasteryEnity> mMasteries) {
        this.mMasteries = mMasteries;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public boolean isIsCurrent() {
        return mIsCurrent;
    }

    public void setmIsCurrent(boolean mIsCurrent) {
        this.mIsCurrent = mIsCurrent;
    }
}
