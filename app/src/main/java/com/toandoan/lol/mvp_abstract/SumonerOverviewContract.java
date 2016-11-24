package com.toandoan.lol.mvp_abstract;

import com.toandoan.lol.model.sumoner_overview.LeagueEnity;

import java.util.List;

/**
 * Created by framgia on 24/11/2016.
 */

public interface SumonerOverviewContract {
    interface View {
        void loadSumonerStats(List<LeagueEnity> leagueEnitíe);
    }

    interface Presenter {
        void loadSumonerStats(String region, String sumonerID);
    }
}
