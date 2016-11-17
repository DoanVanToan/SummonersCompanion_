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

    private long matchDuration;


    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public String getHighestAchievedSeasonTier() {
        return highestAchievedSeasonTier;
    }

    public void setHighestAchievedSeasonTier(String highestAchievedSeasonTier) {
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
    }

    public List<Mastery> getMasteries() {
        return masteries;
    }

    public void setMasteries(List<Mastery> masteries) {
        this.masteries = masteries;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public List<Rune> getRunes() {
        return runes;
    }

    public void setRunes(List<Rune> runes) {
        this.runes = runes;
    }

    public int getSpell1Id() {
        return spell1Id;
    }

    public void setSpell1Id(int spell1Id) {
        this.spell1Id = spell1Id;
    }

    public int getSpell2Id() {
        return spell2Id;
    }

    public void setSpell2Id(int spell2Id) {
        this.spell2Id = spell2Id;
    }

    public ParticipantStats getStats() {
        return mStats;
    }

    public void setStats(ParticipantStats stats) {
        mStats = stats;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public ParticipantTimeline getTimeline() {
        return timeline;
    }

    public void setTimeline(ParticipantTimeline timeline) {
        this.timeline = timeline;
    }

    public long getMatchDuration() {
        return matchDuration;
    }

    public void setMatchDuration(long matchDuration) {
        this.matchDuration = matchDuration;
    }
}
