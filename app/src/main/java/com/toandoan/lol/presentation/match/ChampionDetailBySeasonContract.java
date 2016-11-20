package com.toandoan.lol.presentation.match;

import com.toandoan.lol.base.BaseView;
import com.toandoan.lol.model.champion_by_season.ChampionStatsEnity;

import java.util.List;

/**
 * Created by ToanDoan on 11/20/2016.
 */

public interface ChampionDetailBySeasonContract {
    interface View extends BaseView {
        void updateListChampionStats(List<ChampionStatsEnity> champions);
    }

    interface Presenter {
        void loadChampionDetailStats(String region, String summonerID);
    }
}
