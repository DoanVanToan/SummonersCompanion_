package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by framgia on 17/11/2016.
 */

public class Timeline implements Serializable {
    @SerializedName("frameInterval")
    private long frameInterval;

    @SerializedName("frames")
    private List<Frame> frames;
}
