package com.toandoan.lol.mvp_abstract;

import com.toandoan.lol.base.BaseView;
import com.toandoan.lol.model.SumonerEnity;

/**
 * Created by ToanDoan on 11/12/2016.
 */

public interface SumonerDetailAsbtract {
    interface View extends BaseView {
        void initViewPager(SumonerEnity sumoner);
    }

    interface Presenter {
        void getSumonerByID(String region, String sumonerID);

        void getSumonerByName(String region, String sumonerName);
    }
}
