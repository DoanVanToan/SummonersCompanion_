package com.toandoan.lol.mvp_abstract;

import com.toandoan.lol.model.sumoner_overview.LeagueEntryEnity;

import java.util.List;

/**
 * Created by framgia on 25/11/2016.
 */

public interface ChallengerContract {
    interface View {
        void initViews();
        void updateChallengeSumoner(List<LeagueEntryEnity> entries);
        void scrollToCurrentPosition();
    }

    interface Presenter {
        void loadChallengerSumoner(String region);
        void searchSumoner(String key);
        void loadSumonerRank(String region, String sumonerID, String typeRank);
    }
}
