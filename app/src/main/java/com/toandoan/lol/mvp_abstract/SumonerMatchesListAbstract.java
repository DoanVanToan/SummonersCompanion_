package com.toandoan.lol.mvp_abstract;

import android.view.View;

import com.toandoan.lol.base.BaseView;
import com.toandoan.lol.model.match_detail.Participant;

import java.util.List;

/**
 * Created by framgia on 17/11/2016.
 */

public interface SumonerMatchesListAbstract {
    interface View {
        void initViews(android.view.View v);
        void updateHistoryMatches(List<Participant> list);
    }

    interface Presenter {
        void getAllMatches(String region, String id);
        void getMatchByID(String region, String matchID);
    }
}
