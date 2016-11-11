package com.toandoan.lol.model.rune;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by framgia on 11/11/2016.
 */

public class PageRunes {
    @SerializedName("id")
    private int mID;
    @SerializedName("slots")
    private List<RuneEnity> mRunes;
    @SerializedName("name")
    private String mName;
    @SerializedName("current")
    private boolean mCurrent;

    public int getID() {
        return mID;
    }

    public void setmID(int ID) {
        this.mID = ID;
    }

    public List<RuneEnity> getRunes() {
        return mRunes;
    }

    public void setRunes(List<RuneEnity> runes) {
        this.mRunes = runes;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public boolean isCurrent() {
        return mCurrent;
    }

    public void setCurrent(boolean current) {
        this.mCurrent = current;
    }
}
