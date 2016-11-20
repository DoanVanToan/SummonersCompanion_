package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by framgia on 17/11/2016.
 */

public class Frame implements Serializable {
    @SerializedName("events")
    private List<Event> mEvents;

    @SerializedName("participantFrames")
    private HashMap<String, ParticipantFrame> mParticipantFrames;

    @SerializedName("timestamp")
    private long mTimestamp;
}
