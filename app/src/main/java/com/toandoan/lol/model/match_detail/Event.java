package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by framgia on 17/11/2016.
 */

public class Event implements Serializable {
    @SerializedName("ascendedType")
    private String mAscendedType;

    @SerializedName("assistingParticipantIds")
    private List<Integer> mAssistingParticipantIds;

    @SerializedName("buildingType")
    private String mBuildingType;

    @SerializedName("creatorId")
    private int mCreatorId;

    @SerializedName("eventType")
    private String mEventType;

    @SerializedName("itemAfter")
    private int mItemAfter;

    @SerializedName("itemBefore")
    private int mItemBefore;

    @SerializedName("itemId")
    private int mItemId;

    @SerializedName("killerId")
    private int mKillerId;

    @SerializedName("laneType")
    private String mLaneType;

    @SerializedName("levelUpType")
    private String mLevelUpType;

    @SerializedName("monsterSubType")
    private String mMonsterSubType;

    @SerializedName("monsterType")
    private String mMonsterType;

    @SerializedName("participantId")
    private int mParticipantId;

    @SerializedName("position")
    private Position mPosition;

    @SerializedName("skillSlot")
    private int mSkillSlot;

    @SerializedName("teamId")
    private int mTeamId;

    @SerializedName("timestamp")
    private long mTimestamp;

    @SerializedName("towerType")
    private String mTowerType;

    @SerializedName("victimId")
    private int mVictimId;

    @SerializedName("wardType")
    private String mWardType;

    public String getAscendedType() {
        return mAscendedType;
    }

    public void setAscendedType(String ascendedType) {
        mAscendedType = ascendedType;
    }

    public List<Integer> getAssistingParticipantIds() {
        return mAssistingParticipantIds;
    }

    public void setAssistingParticipantIds(List<Integer> assistingParticipantIds) {
        mAssistingParticipantIds = assistingParticipantIds;
    }

    public String getBuildingType() {
        return mBuildingType;
    }

    public void setBuildingType(String buildingType) {
        mBuildingType = buildingType;
    }

    public int getCreatorId() {
        return mCreatorId;
    }

    public void setCreatorId(int creatorId) {
        mCreatorId = creatorId;
    }

    public String getEventType() {
        return mEventType;
    }

    public void setEventType(String eventType) {
        mEventType = eventType;
    }

    public int getItemAfter() {
        return mItemAfter;
    }

    public void setItemAfter(int itemAfter) {
        mItemAfter = itemAfter;
    }

    public int getItemBefore() {
        return mItemBefore;
    }

    public void setItemBefore(int itemBefore) {
        mItemBefore = itemBefore;
    }

    public int getItemId() {
        return mItemId;
    }

    public void setItemId(int itemId) {
        mItemId = itemId;
    }

    public int getKillerId() {
        return mKillerId;
    }

    public void setKillerId(int killerId) {
        mKillerId = killerId;
    }

    public String getLaneType() {
        return mLaneType;
    }

    public void setLaneType(String laneType) {
        mLaneType = laneType;
    }

    public String getLevelUpType() {
        return mLevelUpType;
    }

    public void setLevelUpType(String levelUpType) {
        mLevelUpType = levelUpType;
    }

    public String getMonsterSubType() {
        return mMonsterSubType;
    }

    public void setMonsterSubType(String monsterSubType) {
        mMonsterSubType = monsterSubType;
    }

    public String getMonsterType() {
        return mMonsterType;
    }

    public void setMonsterType(String monsterType) {
        mMonsterType = monsterType;
    }

    public int getParticipantId() {
        return mParticipantId;
    }

    public void setParticipantId(int participantId) {
        mParticipantId = participantId;
    }

    public Position getPosition() {
        return mPosition;
    }

    public void setPosition(Position position) {
        mPosition = position;
    }

    public int getSkillSlot() {
        return mSkillSlot;
    }

    public void setSkillSlot(int skillSlot) {
        mSkillSlot = skillSlot;
    }

    public int getTeamId() {
        return mTeamId;
    }

    public void setTeamId(int teamId) {
        mTeamId = teamId;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(long timestamp) {
        mTimestamp = timestamp;
    }

    public String getTowerType() {
        return mTowerType;
    }

    public void setTowerType(String towerType) {
        mTowerType = towerType;
    }

    public int getVictimId() {
        return mVictimId;
    }

    public void setVictimId(int victimId) {
        mVictimId = victimId;
    }

    public String getWardType() {
        return mWardType;
    }

    public void setWardType(String wardType) {
        mWardType = wardType;
    }
}
