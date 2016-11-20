package com.toandoan.lol.model.champion_by_season;

import com.toandoan.lol.model.champion.ChampionStats;

import java.util.List;

/**
 * Created by ToanDoan on 11/20/2016.
 */

public class RankedStatsEnity {
    private List<ChampionStatsEnity> champions;
    private long modifyDate;
    private long summonerId;

    public List<ChampionStatsEnity> getChampions() {
        return champions;
    }

    public void setChampions(List<ChampionStatsEnity> champions) {
        this.champions = champions;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }
}
