package com.toandoan.lol.model.rune;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public class RuneInfo {
    @SerializedName("isRune")
    private boolean isRune;
    @SerializedName("tier")
    private int tier;
    @SerializedName("type")
    private String type;

    public RuneInfo(String type, int tier, boolean isRune) {
        this.type = type;
        this.tier = tier;
        this.isRune = isRune;
    }

    public boolean isRune() {
        return isRune;
    }

    public void setRune(boolean rune) {
        isRune = rune;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
