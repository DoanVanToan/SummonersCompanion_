package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by framgia on 17/11/2016.
 */

public class Participant {
    @SerializedName("championId")
    private int championId;

    @SerializedName("highestAchievedSeasonTier")
    private String highestAchievedSeasonTier;

    @SerializedName("masteries")
    private List<Mastery> masteries;

    @SerializedName("participantId")
    private int participantId;

    @SerializedName("runes")
    private List<Rune> runes;

    @SerializedName("spell1Id")
    private int spell1Id;

    @SerializedName("spell2Id")
    private int spell2Id;

    @SerializedName("stats")
    private ParticipantStats mStats;

    @SerializedName("teamId")
    private int teamId;

    @SerializedName("timeline")
    private ParticipantTimeline timeline;

}
