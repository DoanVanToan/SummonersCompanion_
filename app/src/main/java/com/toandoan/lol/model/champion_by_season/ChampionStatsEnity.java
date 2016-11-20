package com.toandoan.lol.model.champion_by_season;

import java.io.Serializable;

/**
 * Created by ToanDoan on 11/20/2016.
 */

public class ChampionStatsEnity implements Serializable{
    private int id;
    private AggregatedStatsEnity stats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AggregatedStatsEnity getStats() {
        return stats;
    }

    public void setStats(AggregatedStatsEnity stats) {
        this.stats = stats;
    }
}
