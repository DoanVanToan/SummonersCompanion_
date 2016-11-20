package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by framgia on 17/11/2016.
 */

public class Mastery implements Serializable {
    @SerializedName("masteryId")
    private long masteryId;

    @SerializedName("rank")
    private long rank;
}
