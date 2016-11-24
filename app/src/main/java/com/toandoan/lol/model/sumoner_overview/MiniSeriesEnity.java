package com.toandoan.lol.model.sumoner_overview;

import java.io.Serializable;

/**
 * Created by framgia on 24/11/2016.
 */

public class MiniSeriesEnity implements Serializable {
    private int losses;
    private String progress;
    private int target;
    private int wins;

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
