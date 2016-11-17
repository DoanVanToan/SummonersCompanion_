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

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public Player getPlayer() {
        return mPlayer;
    }

    public void setPlayer(Player player) {
        mPlayer = player;
    }
}
