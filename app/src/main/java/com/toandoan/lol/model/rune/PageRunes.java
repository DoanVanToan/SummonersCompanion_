package com.toandoan.lol.model.rune;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
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
    private List<RuneEnity> mCountRunes;


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



    public void getCountRunes() {
        mCountRunes = new ArrayList<>();
        for (RuneEnity rune : mRunes) {
            int positionOfRune = positionOnList(rune, mCountRunes);
            if (positionOfRune == -1) {
                rune.setmCount(1);
                mCountRunes.add(rune);
            } else {
                mCountRunes.get(positionOfRune).setmCount(mCountRunes.get(positionOfRune).getmCount() + 1);
            }
        }

    }

    public int positionOnList(RuneEnity checkRune, List<RuneEnity> runes) {
        if (runes != null && runes.size() > 0) {
            for (int i = 0; i < runes.size(); i++) {
                RuneEnity rune = runes.get(i);
                if (rune.getId() == checkRune.getId()) return i;
            }
        }
        return -1;
    }
}
