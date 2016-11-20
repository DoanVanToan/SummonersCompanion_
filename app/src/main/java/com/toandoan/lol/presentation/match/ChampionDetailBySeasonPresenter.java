package com.toandoan.lol.presentation.match;

import android.widget.Toast;

import com.google.gson.Gson;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.model.champion_by_season.RankedStatsEnity;
import com.toandoan.lol.utility.JsonUtil;
import com.toandoan.lol.utility.LogUtil;

import org.json.JSONObject;

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

    public ChampionDetailBySeasonPresenter(BaseActivity mActivity, ChampionDetailBySeasonContract.View mView) {
        this.mActivity = mActivity;
        this.mView = mView;
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
}
