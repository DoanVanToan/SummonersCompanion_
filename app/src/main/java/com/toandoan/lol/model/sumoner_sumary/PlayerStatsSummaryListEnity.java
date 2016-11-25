package com.toandoan.lol.model.sumoner_sumary;

import java.util.List;

/**
 * Created by framgia on 25/11/2016.
 */

public class PlayerStatsSummaryListEnity {
    private List<PlayerStatsSummaryEnity> playerStatSummaries;
    private long summonerId;

    public List<PlayerStatsSummaryEnity> getPlayerStatSummaries() {
        return playerStatSummaries;
    }

    public void setPlayerStatSummaries(List<PlayerStatsSummaryEnity> playerStatSummaries) {
        this.playerStatSummaries = playerStatSummaries;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }
}
