package com.toandoan.lol.model.sumoner_sumary;

import com.toandoan.lol.model.champion_by_season.AggregatedStatsEnity;

/**
 * Created by framgia on 25/11/2016.
 */

public class PlayerStatsSummaryEnity {
    private AggregatedStatsEnity aggregatedStats;
    private int losses;
    private long modifyDate;
    private String playerStatSummaryType;
    private int wins;

    public AggregatedStatsEnity getAggregatedStats() {
        return aggregatedStats;
    }

    public void setAggregatedStats(AggregatedStatsEnity aggregatedStats) {
        this.aggregatedStats = aggregatedStats;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getPlayerStatSummaryType() {
        return playerStatSummaryType;
    }

    public void setPlayerStatSummaryType(String playerStatSummaryType) {
        this.playerStatSummaryType = playerStatSummaryType;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
