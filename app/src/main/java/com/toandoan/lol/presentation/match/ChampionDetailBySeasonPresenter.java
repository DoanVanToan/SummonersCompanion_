package com.toandoan.lol.presentation.match;

import android.widget.Toast;

import com.google.gson.Gson;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.model.champion_by_season.ChampionStatsEnity;
import com.toandoan.lol.model.champion_by_season.RankedStatsEnity;
import com.toandoan.lol.utility.JsonUtil;
import com.toandoan.lol.utility.LogUtil;

import org.json.JSONObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ToanDoan on 11/20/2016.
 */

public class ChampionDetailBySeasonPresenter implements ChampionDetailBySeasonContract.Presenter {
    private BaseActivity mActivity;
    private ChampionDetailBySeasonContract.View mView;
    private OnLoadChampionFinnish mListenner;

    public ChampionDetailBySeasonPresenter(BaseActivity mActivity, ChampionDetailBySeasonContract.View mView,
                                           OnLoadChampionFinnish listenner) {
        this.mActivity = mActivity;
        this.mView = mView;
        this.mListenner = listenner;
    }

    @Override
    public void loadChampionDetailStats(String region, String summonerID) {
        RiotService service = ServiceGenerator.createService(RiotService.class);
        Call<ResponseBody> call = service.getSumonnerChampionStatsByID(region, summonerID);
        call.enqueue(getChampionStatsCallBack);
    }

    Callback<ResponseBody> getChampionStatsCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            JSONObject jsonRespone = JsonUtil.convertResponseToJson(response);
            if (jsonRespone != null) {
                RankedStatsEnity rankStats = new Gson().fromJson(jsonRespone.toString(), RankedStatsEnity.class);
                LogUtil.e("getChampionStatsCallBack", jsonRespone.toString());
                if (rankStats != null) {
                    sortChampionByMatchCount(rankStats.getChampions());
                    mListenner.onLoadFinnish(rankStats.getChampions());
                    mView.updateListChampionStats(rankStats.getChampions());
                }
            } else {
                Toast.makeText(mActivity, mActivity.getString(R.string.not_internet_connected), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(mActivity, mActivity.getString(R.string.not_internet_connected), Toast.LENGTH_SHORT).show();
        }
    };

    public void sortChampionByMatchCount(List<ChampionStatsEnity> stats) {
        Collections.sort(stats, new Comparator<ChampionStatsEnity>() {
            @Override
            public int compare(ChampionStatsEnity o1, ChampionStatsEnity o2) {
                if (o1.getStats().getTotalSessionsPlayed() > o2.getStats().getTotalSessionsPlayed()) {
                    return -1;
                } else if (o1.getStats().getTotalSessionsPlayed() < o2.getStats().getTotalSessionsPlayed()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        if (stats != null && stats.size() != 0) {
            if (stats.get(0).getId() == 0) {
                stats.remove(0);
            }
        }
    }

    public interface OnLoadChampionFinnish {
        void onLoadFinnish(List<ChampionStatsEnity> stats);
    }
}
