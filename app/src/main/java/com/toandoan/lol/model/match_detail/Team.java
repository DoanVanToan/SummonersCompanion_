package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by framgia on 17/11/2016.
 */

public class Team implements Serializable {
    @SerializedName("bans")
    private List<BannedChampion> bans;

    @SerializedName("baronKills")
    private int baronKillsl;

    @SerializedName("dominionVictoryScore")
    private long dominionVictoryScore;

    @SerializedName("dragonKills")
    private int dragonKills;

    @SerializedName("firstBaron")
    private boolean firstBaron;

    @SerializedName("firstBlood")
    private boolean firstBlood;

    @SerializedName("firstDragon")
    private boolean firstDragon;

    @SerializedName("firstInhibitor")
    private boolean firstInhibitor;

    @SerializedName("firstRiftHerald")
    private boolean firstRiftHerald;

    @SerializedName("firstTower")
    private boolean firstTower;

    @SerializedName("inhibitorKills")
    private int inhibitorKills;

    @SerializedName("riftHeraldKills")
    private int riftHeraldKills;

    @SerializedName("teamId")
    private int teamId;

    @SerializedName("towerKills")
    private int towerKills;

    @SerializedName("vilemawKills")
    private int vilemawKills;

    @SerializedName("winner")
    private boolean winner;






}
