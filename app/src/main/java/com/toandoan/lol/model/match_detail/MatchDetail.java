package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by framgia on 17/11/2016.
 */

public class MatchDetail {
    @SerializedName("mapId")
    private int mapId;

    @SerializedName("matchCreation")
    private long matchCreation;

    @SerializedName("matchDuration")
    private long matchDuration;

    @SerializedName("matchId")
    private long matchId;

    @SerializedName("matchMode")
    private String matchMode;

    @SerializedName("matchType")
    private String matchType;

    @SerializedName("matchVersion")
    private String matchVersion;

    @SerializedName("participantIdentities")
    private List<ParticipantIdentity> participantIdentities;

    @SerializedName("participants")
    private List<Participant> participants;

    @SerializedName("platformId")
    private String platformId;

    @SerializedName("queueType")
    private String queueType;

    @SerializedName("region")
    private String region;

    @SerializedName("season")
    private String season;

    @SerializedName("teams")
    private List<Team> teams;

    @SerializedName("timeline")
    private Timeline mTimeline;

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public long getMatchCreation() {
        return matchCreation;
    }

    public void setMatchCreation(long matchCreation) {
        this.matchCreation = matchCreation;
    }

    public long getMatchDuration() {
        return matchDuration;
    }

    public void setMatchDuration(long matchDuration) {
        this.matchDuration = matchDuration;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public String getMatchMode() {
        return matchMode;
    }

    public void setMatchMode(String matchMode) {
        this.matchMode = matchMode;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getMatchVersion() {
        return matchVersion;
    }

    public void setMatchVersion(String matchVersion) {
        this.matchVersion = matchVersion;
    }

    public List<ParticipantIdentity> getParticipantIdentities() {
        return participantIdentities;
    }

    public void setParticipantIdentities(List<ParticipantIdentity> participantIdentities) {
        this.participantIdentities = participantIdentities;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Timeline getTimeline() {
        return mTimeline;
    }

    public void setTimeline(Timeline timeline) {
        mTimeline = timeline;
    }
}
