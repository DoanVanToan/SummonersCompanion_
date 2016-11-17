package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by framgia on 17/11/2016.
 */

public class Timeline {
    @SerializedName("frameInterval")
    private long frameInterval;

    @SerializedName("frames")
    private List<Frame> frames;
}
