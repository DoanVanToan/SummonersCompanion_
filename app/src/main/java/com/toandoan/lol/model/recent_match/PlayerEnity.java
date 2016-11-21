package com.toandoan.lol.model.recent_match;

import java.io.Serializable;

/**
 * Created by ToanDoan on 11/21/2016.
 */

public class PlayerEnity implements Serializable {
    private int championId;
    private long summonerId;
    private int teamId;
    private String sumonerName;

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getSumonerName() {
        return sumonerName;
    }

    public void setSumonerName(String sumonerName) {
        this.sumonerName = sumonerName;
    }
}
