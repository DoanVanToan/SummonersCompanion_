package com.toandoan.lol.mvp_abstract;

import com.toandoan.lol.model.SumonerEnity;

/**
 * Created by framgia on 24/11/2016.
 */

public interface SearchSumonerContract {
    interface View {
        void updateUISumoner(SumonerEnity sumonerEnity);
    }

    interface Presenter {
        void searchSumonerByName(String region, String name);
    }
}
