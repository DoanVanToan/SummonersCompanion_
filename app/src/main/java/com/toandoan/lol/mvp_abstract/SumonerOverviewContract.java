package com.toandoan.lol.mvp_abstract;

import com.toandoan.lol.model.sumoner_overview.LeagueEnity;

/**
 * Created by framgia on 24/11/2016.
 */

public interface SumonerOverviewContract {
    interface View {
        void loadSumonerStats(LeagueEnity leagueEnity);
    }

    interface Presenter {
        void loadSumonerStats(String region, String sumonerID);
    }
}
