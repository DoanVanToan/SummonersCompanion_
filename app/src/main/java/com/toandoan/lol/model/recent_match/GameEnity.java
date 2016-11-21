package com.toandoan.lol.model.recent_match;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ToanDoan on 11/21/2016.
 */

public class GameEnity implements Serializable {
    private int championId;
    private long createDate;
    private List<PlayerEnity> fellowPlayers;
    private long gameId;
    private String gameMode;
    private String gameType;
    private boolean invalid;
    private int ipEarned;
    private int level;
    private int mapId;
    private int spell1;
    private int spell2;
    private RawStatsEnity stats;
    private String subType;
    private int teamId;

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public List<PlayerEnity> getFellowPlayers() {
        return fellowPlayers;
    }

    public void setFellowPlayers(List<PlayerEnity> fellowPlayers) {
        this.fellowPlayers = fellowPlayers;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public int getIpEarned() {
        return ipEarned;
    }

    public void setIpEarned(int ipEarned) {
        this.ipEarned = ipEarned;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public int getSpell1() {
        return spell1;
    }

    public void setSpell1(int spell1) {
        this.spell1 = spell1;
    }

    public int getSpell2() {
        return spell2;
    }

    public void setSpell2(int spell2) {
        this.spell2 = spell2;
    }

    public RawStatsEnity getStats() {
        return stats;
    }

    public void setStats(RawStatsEnity stats) {
        this.stats = stats;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
