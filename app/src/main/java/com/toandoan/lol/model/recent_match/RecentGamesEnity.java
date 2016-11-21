package com.toandoan.lol.model.recent_match;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ToanDoan on 11/21/2016.
 */

public class RecentGamesEnity implements Serializable {
    private List<GameEnity> games;
    private long summonerId;

    public List<GameEnity> getGames() {
        return games;
    }

    public void setGames(List<GameEnity> games) {
        this.games = games;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }
}
