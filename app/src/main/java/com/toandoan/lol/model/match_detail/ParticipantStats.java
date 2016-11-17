package com.toandoan.lol.model.match_detail;

import com.google.gson.annotations.SerializedName;

/**
 * Created by framgia on 17/11/2016.
 */

public class ParticipantStats {
    @SerializedName("assists")
    private long assists;

    @SerializedName("champLevel")
    private long champLevel;

    @SerializedName("combatPlayerScore")
    private long combatPlayerScore;

    @SerializedName("deaths")
    private long deaths;

    @SerializedName("doubleKills")
    private long doubleKills;

    @SerializedName("firstBloodAssist")
    private boolean firstBloodAssist;

    @SerializedName("firstBloodKill")
    private boolean firstBloodKill;

    @SerializedName("firstInhibitorAssist")
    private boolean firstInhibitorAssist;

    @SerializedName("firstInhibitorKill")
    private boolean firstInhibitorKill;

    @SerializedName("firstTowerAssist")
    private boolean firstTowerAssist;

    @SerializedName("firstTowerKill")
    private boolean firstTowerKill;

    @SerializedName("goldEarned")
    private long goldEarned;

    @SerializedName("goldSpent")
    private long goldSpent;

    @SerializedName("inhibitorKills")
    private long inhibitorKills;

    @SerializedName("item0")
    private long item0;

    @SerializedName("item1")
    private long item1;

    @SerializedName("item2")
    private long item2;

    @SerializedName("item3")
    private long item3;

    @SerializedName("item4")
    private long item4;

    @SerializedName("item5")
    private long item5;

    @SerializedName("item6")
    private long item6;

    @SerializedName("killingSprees")
    private long killingSprees;

    @SerializedName("kills")
    private long kills;

    @SerializedName("largestCriticalStrike")
    private long largestCriticalStrike;

    @SerializedName("largestKillingSpree")
    private long largestKillingSpree;

    @SerializedName("largestMultiKill")
    private long largestMultiKill;

    @SerializedName("magicDamageDealt")
    private long magicDamageDealt;

    @SerializedName("magicDamageDealtToChampions")
    private long magicDamageDealtToChampions;

    @SerializedName("magicDamageTaken")
    private long magicDamageTaken;

    @SerializedName("minionsKilled")
    private long minionsKilled;

    @SerializedName("neutralMinionsKilled")
    private long neutralMinionsKilled;

    @SerializedName("neutralMinionsKilledEnemyJungle")
    private long neutralMinionsKilledEnemyJungle;

    @SerializedName("neutralMinionsKilledTeamJungle")
    private long neutralMinionsKilledTeamJungle;

    @SerializedName("nodeCapture")
    private long nodeCapture;

    @SerializedName("nodeCaptureAssist")
    private long nodeCaptureAssist;

    @SerializedName("nodeNeutralize")
    private long nodeNeutralize;

    @SerializedName("nodeNeutralizeAssist")
    private long nodeNeutralizeAssist;

    @SerializedName("objectivePlayerScore")
    private long objectivePlayerScore;

    @SerializedName("pentaKills")
    private long pentaKills;

    @SerializedName("physicalDamageDealt")
    private long physicalDamageDealt;

    @SerializedName("physicalDamageDealtToChampions")
    private long physicalDamageDealtToChampions;

    @SerializedName("physicalDamageTaken")
    private long physicalDamageTaken;

    @SerializedName("quadraKills")
    private long quadraKills;

    @SerializedName("sightWardsBoughtInGame")
    private long sightWardsBoughtInGame;

    @SerializedName("teamObjective")
    private long teamObjective;

    @SerializedName("totalDamageDealt")
    private long totalDamageDealt;

    @SerializedName("totalDamageDealtToChampions")
    private long totalDamageDealtToChampions;

    @SerializedName("totalDamageTaken")
    private long totalDamageTaken;

    @SerializedName("totalHeal")
    private long totalHeal;

    @SerializedName("totalPlayerScore")
    private long totalPlayerScore;

    @SerializedName("totalScoreRank")
    private long totalScoreRank;

    @SerializedName("totalTimeCrowdControlDealt")
    private long totalTimeCrowdControlDealt;

    @SerializedName("totalUnitsHealed")
    private long totalUnitsHealed;

    @SerializedName("towerKills")
    private long towerKills;

    @SerializedName("tripleKills")
    private long tripleKills;

    @SerializedName("trueDamageDealt")
    private long trueDamageDealt;

    @SerializedName("trueDamageDealtToChampions")
    private long trueDamageDealtToChampions;

    @SerializedName("trueDamageTaken")
    private long trueDamageTaken;

    @SerializedName("unrealKills")
    private long unrealKills;

    @SerializedName("visionWardsBoughtInGame")
    private long visionWardsBoughtInGame;

    @SerializedName("wardsKilled")
    private long wardsKilled;

    @SerializedName("wardsPlaced")
    private long wardsPlaced;

    @SerializedName("winner")
    private boolean winner;

    public long getAssists() {
        return assists;
    }

    public void setAssists(long assists) {
        this.assists = assists;
    }

    public long getChampLevel() {
        return champLevel;
    }

    public void setChampLevel(long champLevel) {
        this.champLevel = champLevel;
    }

    public long getCombatPlayerScore() {
        return combatPlayerScore;
    }

    public void setCombatPlayerScore(long combatPlayerScore) {
        this.combatPlayerScore = combatPlayerScore;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getDoubleKills() {
        return doubleKills;
    }

    public void setDoubleKills(long doubleKills) {
        this.doubleKills = doubleKills;
    }

    public boolean isFirstBloodAssist() {
        return firstBloodAssist;
    }

    public void setFirstBloodAssist(boolean firstBloodAssist) {
        this.firstBloodAssist = firstBloodAssist;
    }

    public boolean isFirstBloodKill() {
        return firstBloodKill;
    }

    public void setFirstBloodKill(boolean firstBloodKill) {
        this.firstBloodKill = firstBloodKill;
    }

    public boolean isFirstInhibitorAssist() {
        return firstInhibitorAssist;
    }

    public void setFirstInhibitorAssist(boolean firstInhibitorAssist) {
        this.firstInhibitorAssist = firstInhibitorAssist;
    }

    public boolean isFirstInhibitorKill() {
        return firstInhibitorKill;
    }

    public void setFirstInhibitorKill(boolean firstInhibitorKill) {
        this.firstInhibitorKill = firstInhibitorKill;
    }

    public boolean isFirstTowerAssist() {
        return firstTowerAssist;
    }

    public void setFirstTowerAssist(boolean firstTowerAssist) {
        this.firstTowerAssist = firstTowerAssist;
    }

    public boolean isFirstTowerKill() {
        return firstTowerKill;
    }

    public void setFirstTowerKill(boolean firstTowerKill) {
        this.firstTowerKill = firstTowerKill;
    }

    public long getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(long goldEarned) {
        this.goldEarned = goldEarned;
    }

    public long getGoldSpent() {
        return goldSpent;
    }

    public void setGoldSpent(long goldSpent) {
        this.goldSpent = goldSpent;
    }

    public long getInhibitorKills() {
        return inhibitorKills;
    }

    public void setInhibitorKills(long inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    public long getItem0() {
        return item0;
    }

    public void setItem0(long item0) {
        this.item0 = item0;
    }

    public long getItem1() {
        return item1;
    }

    public void setItem1(long item1) {
        this.item1 = item1;
    }

    public long getItem2() {
        return item2;
    }

    public void setItem2(long item2) {
        this.item2 = item2;
    }

    public long getItem3() {
        return item3;
    }

    public void setItem3(long item3) {
        this.item3 = item3;
    }

    public long getItem4() {
        return item4;
    }

    public void setItem4(long item4) {
        this.item4 = item4;
    }

    public long getItem5() {
        return item5;
    }

    public void setItem5(long item5) {
        this.item5 = item5;
    }

    public long getItem6() {
        return item6;
    }

    public void setItem6(long item6) {
        this.item6 = item6;
    }

    public long getKillingSprees() {
        return killingSprees;
    }

    public void setKillingSprees(long killingSprees) {
        this.killingSprees = killingSprees;
    }

    public long getKills() {
        return kills;
    }

    public void setKills(long kills) {
        this.kills = kills;
    }

    public long getLargestCriticalStrike() {
        return largestCriticalStrike;
    }

    public void setLargestCriticalStrike(long largestCriticalStrike) {
        this.largestCriticalStrike = largestCriticalStrike;
    }

    public long getLargestKillingSpree() {
        return largestKillingSpree;
    }

    public void setLargestKillingSpree(long largestKillingSpree) {
        this.largestKillingSpree = largestKillingSpree;
    }

    public long getLargestMultiKill() {
        return largestMultiKill;
    }

    public void setLargestMultiKill(long largestMultiKill) {
        this.largestMultiKill = largestMultiKill;
    }

    public long getMagicDamageDealt() {
        return magicDamageDealt;
    }

    public void setMagicDamageDealt(long magicDamageDealt) {
        this.magicDamageDealt = magicDamageDealt;
    }

    public long getMagicDamageDealtToChampions() {
        return magicDamageDealtToChampions;
    }

    public void setMagicDamageDealtToChampions(long magicDamageDealtToChampions) {
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
    }

    public long getMagicDamageTaken() {
        return magicDamageTaken;
    }

    public void setMagicDamageTaken(long magicDamageTaken) {
        this.magicDamageTaken = magicDamageTaken;
    }

    public long getMinionsKilled() {
        return minionsKilled;
    }

    public void setMinionsKilled(long minionsKilled) {
        this.minionsKilled = minionsKilled;
    }

    public long getNeutralMinionsKilled() {
        return neutralMinionsKilled;
    }

    public void setNeutralMinionsKilled(long neutralMinionsKilled) {
        this.neutralMinionsKilled = neutralMinionsKilled;
    }

    public long getNeutralMinionsKilledEnemyJungle() {
        return neutralMinionsKilledEnemyJungle;
    }

    public void setNeutralMinionsKilledEnemyJungle(long neutralMinionsKilledEnemyJungle) {
        this.neutralMinionsKilledEnemyJungle = neutralMinionsKilledEnemyJungle;
    }

    public long getNeutralMinionsKilledTeamJungle() {
        return neutralMinionsKilledTeamJungle;
    }

    public void setNeutralMinionsKilledTeamJungle(long neutralMinionsKilledTeamJungle) {
        this.neutralMinionsKilledTeamJungle = neutralMinionsKilledTeamJungle;
    }

    public long getNodeCapture() {
        return nodeCapture;
    }

    public void setNodeCapture(long nodeCapture) {
        this.nodeCapture = nodeCapture;
    }

    public long getNodeCaptureAssist() {
        return nodeCaptureAssist;
    }

    public void setNodeCaptureAssist(long nodeCaptureAssist) {
        this.nodeCaptureAssist = nodeCaptureAssist;
    }

    public long getNodeNeutralize() {
        return nodeNeutralize;
    }

    public void setNodeNeutralize(long nodeNeutralize) {
        this.nodeNeutralize = nodeNeutralize;
    }

    public long getNodeNeutralizeAssist() {
        return nodeNeutralizeAssist;
    }

    public void setNodeNeutralizeAssist(long nodeNeutralizeAssist) {
        this.nodeNeutralizeAssist = nodeNeutralizeAssist;
    }

    public long getObjectivePlayerScore() {
        return objectivePlayerScore;
    }

    public void setObjectivePlayerScore(long objectivePlayerScore) {
        this.objectivePlayerScore = objectivePlayerScore;
    }

    public long getPentaKills() {
        return pentaKills;
    }

    public void setPentaKills(long pentaKills) {
        this.pentaKills = pentaKills;
    }

    public long getPhysicalDamageDealt() {
        return physicalDamageDealt;
    }

    public void setPhysicalDamageDealt(long physicalDamageDealt) {
        this.physicalDamageDealt = physicalDamageDealt;
    }

    public long getPhysicalDamageDealtToChampions() {
        return physicalDamageDealtToChampions;
    }

    public void setPhysicalDamageDealtToChampions(long physicalDamageDealtToChampions) {
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
    }

    public long getPhysicalDamageTaken() {
        return physicalDamageTaken;
    }

    public void setPhysicalDamageTaken(long physicalDamageTaken) {
        this.physicalDamageTaken = physicalDamageTaken;
    }

    public long getQuadraKills() {
        return quadraKills;
    }

    public void setQuadraKills(long quadraKills) {
        this.quadraKills = quadraKills;
    }

    public long getSightWardsBoughtInGame() {
        return sightWardsBoughtInGame;
    }

    public void setSightWardsBoughtInGame(long sightWardsBoughtInGame) {
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
    }

    public long getTeamObjective() {
        return teamObjective;
    }

    public void setTeamObjective(long teamObjective) {
        this.teamObjective = teamObjective;
    }

    public long getTotalDamageDealt() {
        return totalDamageDealt;
    }

    public void setTotalDamageDealt(long totalDamageDealt) {
        this.totalDamageDealt = totalDamageDealt;
    }

    public long getTotalDamageDealtToChampions() {
        return totalDamageDealtToChampions;
    }

    public void setTotalDamageDealtToChampions(long totalDamageDealtToChampions) {
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
    }

    public long getTotalDamageTaken() {
        return totalDamageTaken;
    }

    public void setTotalDamageTaken(long totalDamageTaken) {
        this.totalDamageTaken = totalDamageTaken;
    }

    public long getTotalHeal() {
        return totalHeal;
    }

    public void setTotalHeal(long totalHeal) {
        this.totalHeal = totalHeal;
    }

    public long getTotalPlayerScore() {
        return totalPlayerScore;
    }

    public void setTotalPlayerScore(long totalPlayerScore) {
        this.totalPlayerScore = totalPlayerScore;
    }

    public long getTotalScoreRank() {
        return totalScoreRank;
    }

    public void setTotalScoreRank(long totalScoreRank) {
        this.totalScoreRank = totalScoreRank;
    }

    public long getTotalTimeCrowdControlDealt() {
        return totalTimeCrowdControlDealt;
    }

    public void setTotalTimeCrowdControlDealt(long totalTimeCrowdControlDealt) {
        this.totalTimeCrowdControlDealt = totalTimeCrowdControlDealt;
    }

    public long getTotalUnitsHealed() {
        return totalUnitsHealed;
    }

    public void setTotalUnitsHealed(long totalUnitsHealed) {
        this.totalUnitsHealed = totalUnitsHealed;
    }

    public long getTowerKills() {
        return towerKills;
    }

    public void setTowerKills(long towerKills) {
        this.towerKills = towerKills;
    }

    public long getTripleKills() {
        return tripleKills;
    }

    public void setTripleKills(long tripleKills) {
        this.tripleKills = tripleKills;
    }

    public long getTrueDamageDealt() {
        return trueDamageDealt;
    }

    public void setTrueDamageDealt(long trueDamageDealt) {
        this.trueDamageDealt = trueDamageDealt;
    }

    public long getTrueDamageDealtToChampions() {
        return trueDamageDealtToChampions;
    }

    public void setTrueDamageDealtToChampions(long trueDamageDealtToChampions) {
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
    }

    public long getTrueDamageTaken() {
        return trueDamageTaken;
    }

    public void setTrueDamageTaken(long trueDamageTaken) {
        this.trueDamageTaken = trueDamageTaken;
    }

    public long getUnrealKills() {
        return unrealKills;
    }

    public void setUnrealKills(long unrealKills) {
        this.unrealKills = unrealKills;
    }

    public long getVisionWardsBoughtInGame() {
        return visionWardsBoughtInGame;
    }

    public void setVisionWardsBoughtInGame(long visionWardsBoughtInGame) {
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
    }

    public long getWardsKilled() {
        return wardsKilled;
    }

    public void setWardsKilled(long wardsKilled) {
        this.wardsKilled = wardsKilled;
    }

    public long getWardsPlaced() {
        return wardsPlaced;
    }

    public void setWardsPlaced(long wardsPlaced) {
        this.wardsPlaced = wardsPlaced;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
