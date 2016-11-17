package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

/**
 * Created by framgia on 17/11/2016.
 */

public class ParticipantFrame {
    @SerializedName("currentGold")
    private int mCurrentGold;
    @SerializedName("dominionScore")
    private int mDominionScore;
    @SerializedName("jungleMinionsKilled")
    private int mJungleMinionsKilled;
    @SerializedName("level")
    private int mLevel;
    @SerializedName("minionsKilled")
    private int mMinionsKilled;
    @SerializedName("participantId")
    private int participantId;
    @SerializedName("position")
    private Position mPosition;
    @SerializedName("teamScore")
    private int mTeamScore;
    @SerializedName("totalGold")
    private int mTotalGold;
    @SerializedName("xp")
    private int mXp;

    public int getCurrentGold() {
        return mCurrentGold;
    }

    public void setCurrentGold(int currentGold) {
        mCurrentGold = currentGold;
    }

    public int getDominionScore() {
        return mDominionScore;
    }

    public void setDominionScore(int dominionScore) {
        mDominionScore = dominionScore;
    }

    public int getJungleMinionsKilled() {
        return mJungleMinionsKilled;
    }

    public void setJungleMinionsKilled(int jungleMinionsKilled) {
        mJungleMinionsKilled = jungleMinionsKilled;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public int getMinionsKilled() {
        return mMinionsKilled;
    }

    public void setMinionsKilled(int minionsKilled) {
        mMinionsKilled = minionsKilled;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public Position getPosition() {
        return mPosition;
    }

    public void setPosition(Position position) {
        mPosition = position;
    }

    public int getTeamScore() {
        return mTeamScore;
    }

    public void setTeamScore(int teamScore) {
        mTeamScore = teamScore;
    }

    public int getTotalGold() {
        return mTotalGold;
    }

    public void setTotalGold(int totalGold) {
        mTotalGold = totalGold;
    }

    public int getXp() {
        return mXp;
    }

    public void setXp(int xp) {
        mXp = xp;
    }
}
