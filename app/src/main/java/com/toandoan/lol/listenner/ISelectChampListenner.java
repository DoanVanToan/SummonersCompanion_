package com.toandoan.lol.listenner;

import com.toandoan.lol.model.champion.ChampionEnity;

import java.util.List;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public interface ISelectChampListenner {
    void getAllChampionSuccess(List<ChampionEnity> fullChampions);
}
