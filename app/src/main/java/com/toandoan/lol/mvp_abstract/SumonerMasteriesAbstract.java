package com.toandoan.lol.mvp_abstract;

import com.toandoan.lol.model.matery.MasteryEnity;
import com.toandoan.lol.model.matery.PageMasteries;

import java.util.List;

/**
 * Created by framgia on 10/11/2016.
 */

public interface SumonerMasteriesAbstract {
    interface View{
        void initViews();
        void initViewPager(List<MasteryEnity> masteryEnities);
        void updateTabLayout(int countFerocity, int countCunning, int countReslve);
    }

    interface Presenter{
        void loadSumonerMasteries(String region, String id);
        void getCountMasteries(List<MasteryEnity> listPages);
    }
}
