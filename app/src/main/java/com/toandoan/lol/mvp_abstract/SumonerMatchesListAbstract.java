package com.toandoan.lol.mvp_abstract;

import android.view.View;

import com.toandoan.lol.base.BaseView;
import com.toandoan.lol.model.match_detail.Participant;
import com.toandoan.lol.model.recent_match.GameEnity;
import com.toandoan.lol.model.recent_match.RecentGamesEnity;

import java.util.List;

/**
 * Created by framgia on 17/11/2016.
 */

public interface SumonerMatchesListAbstract {
    interface View {
        void initViews(android.view.View v);
        void updateHistoryMatches(List<GameEnity> list);
    }

    interface Presenter {
        void getAllMatches(String region, String id);
        void onItemClickListenner(android.view.View v, int position, GameEnity gameEnity);
    }
}
