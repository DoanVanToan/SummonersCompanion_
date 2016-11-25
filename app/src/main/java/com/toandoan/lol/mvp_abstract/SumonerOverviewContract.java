package com.toandoan.lol.mvp_abstract;

import com.toandoan.lol.model.SumonerEnity;
import com.toandoan.lol.model.champion_by_season.ChampionStatsEnity;
import com.toandoan.lol.model.sumoner_overview.LeagueEnity;
import com.toandoan.lol.model.sumoner_sumary.PlayerStatsSummaryEnity;
import com.toandoan.lol.model.sumoner_sumary.PlayerStatsSummaryListEnity;

import java.util.List;

/**
 * Created by framgia on 24/11/2016.
 */

public interface SumonerOverviewContract {
    interface View {
        void updateSumonerStats(List<LeagueEnity> leagues);

        void updateMostChampionPlayed(List<ChampionStatsEnity> champions);

        void updateSumonerSumary(PlayerStatsSummaryEnity stats);

        void updateSumoner(SumonerEnity sumoner);
    }

    interface Presenter {
        void loadSumonerStats(String region, String sumonerID);

        void loadSumonerSumary(String region, String sumonerID);

        void loadSumonerById(String region, String sumonerID);
    }
}
