package com.toandoan.lol.listenner;

import com.toandoan.lol.model.champion.ChampionEnity;

/**
 * Created by ToanDoan on 10/15/2016.
 */

public interface ChampionDetailActivityListenner {
    void getChampionByIDSucces(ChampionEnity championEnity);
    void getChampionByIDFail();
}
