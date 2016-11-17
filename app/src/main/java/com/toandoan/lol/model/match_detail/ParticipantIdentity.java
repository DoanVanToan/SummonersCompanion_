package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

/**
 * Created by framgia on 17/11/2016.
 */

public class ParticipantIdentity {
    @SerializedName("participantId")
    private int participantId;

    @SerializedName("player")
    private Player mPlayer;
}
