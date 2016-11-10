package com.toandoan.lol.listenner;

import com.toandoan.lol.model.matery.MasteryEnity;

import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public interface MasteriesListenner {
    void getAllMasteriesSuccessful(List<MasteryEnity> listData);
    void getAllMasteriesFail(String message);
    void onItemClickListenner(MasteryEnity masteryEnity);
}
