package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

/**
 * Created by framgia on 17/11/2016.
 */

public class ParticipantTimelineData {
    @SerializedName("tenToTwenty")
    private double mTenToTwenty;

    @SerializedName("thirtyToEnd")
    private double mThirtyToEnd;

    @SerializedName("twentyToThirty")
    private double mTwentyToThirty;

    @SerializedName("zeroToTen")
    private double mZeroToTen;

    public double getTenToTwenty() {
        return mTenToTwenty;
    }

    public void setTenToTwenty(double tenToTwenty) {
        mTenToTwenty = tenToTwenty;
    }

    public double getThirtyToEnd() {
        return mThirtyToEnd;
    }

    public void setThirtyToEnd(double thirtyToEnd) {
        mThirtyToEnd = thirtyToEnd;
    }

    public double getTwentyToThirty() {
        return mTwentyToThirty;
    }

    public void setTwentyToThirty(double twentyToThirty) {
        mTwentyToThirty = twentyToThirty;
    }

    public double getZeroToTen() {
        return mZeroToTen;
    }

    public void setZeroToTen(double zeroToTen) {
        mZeroToTen = zeroToTen;
    }
}
