package com.toandoan.lol.model.sumoner_overview;

import java.util.List;

/**
 * Created by framgia on 24/11/2016.
 */

public class LeagueEnity {
    private List<LeagueEntryEnity> entries;
    private String name;
    private String participantId;
    private String queue;
    private String tier;

    public List<LeagueEntryEnity> getEntries() {
        return entries;
    }

    public void setEntries(List<LeagueEntryEnity> entries) {
        this.entries = entries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }
}
