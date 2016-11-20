package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

/**
 * Created by framgia on 17/11/2016.
 */

public class Position {
    @SerializedName("x")
    private int mX;
    @SerializedName("y")
    private int mY;

    public int getX() {
        return mX;
    }

    public void setX(int x) {
        mX = x;
    }

    public int getY() {
        return mY;
    }

    public void setY(int y) {
        mY = y;
    }
}
