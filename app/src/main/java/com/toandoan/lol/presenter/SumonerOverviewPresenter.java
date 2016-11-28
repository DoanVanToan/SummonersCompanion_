package com.toandoan.lol.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toandoan.lol.R;
import com.toandoan.lol.activity.SumonerDetailActivity;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.SumonerEnity;
import com.toandoan.lol.model.sumoner_overview.LeagueEnity;
import com.toandoan.lol.model.sumoner_sumary.PlayerStatsSummaryEnity;
import com.toandoan.lol.model.sumoner_sumary.PlayerStatsSummaryListEnity;
import com.toandoan.lol.mvp_abstract.SumonerOverviewContract;
import com.toandoan.lol.utility.JsonUtil;
import com.toandoan.lol.utility.LogUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by framgia on 24/11/2016.
 */

public class SumonerOverviewPresenter implements SumonerOverviewContract.Presenter {
    private BaseActivity mActivity;
    private SumonerOverviewContract.View mView;

    public SumonerOverviewPresenter(BaseActivity activity, SumonerOverviewContract.View view) {
        mActivity = activity;
        mView = view;
    }

    @Override
    public void loadSumonerStats(String region, String sumonerID) {
        RiotService service = ServiceGenerator.createStaticService(RiotService.class);
        Call<ResponseBody> call = service.getSumonnerRankStats(region, sumonerID);
        call.enqueue(getSummonerStatsCallBack);
    }


    Callback<ResponseBody> getSummonerStatsCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            JSONObject jsonResponse = JsonUtil.convertResponseToJson(response);
            LogUtil.e("getSummonerStatsCallBack", jsonResponse.toString());
            if (jsonResponse != null) {
                try {
                    JSONArray jsonData = jsonResponse.optJSONArray(jsonResponse.keys().next());
                    if (jsonData != null) {
                        List<LeagueEnity> listData;
                        Type listType = new TypeToken<ArrayList<LeagueEnity>>() {
                        }.getType();
                        listData = new Gson().fromJson(jsonData.toString(), listType);
                        if (listData != null) {
                            mView.updateSumonerStats(listData);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(mActivity, R.string.not_internet_connected, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(mActivity, R.string.not_internet_connected, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void loadSumonerSumary(String region, String sumonerID) {
        RiotService service = ServiceGenerator.createStaticService(RiotService.class);
        Call<ResponseBody> call = service.getSumonnerRankSumary(region, sumonerID);
        call.enqueue(getSummonerSumaryCallBack);
    }


    Callback<ResponseBody> getSummonerSumaryCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            JSONObject jsonResponse = JsonUtil.convertResponseToJson(response);
            LogUtil.e("getSummonerSumaryCallBack", jsonResponse.toString());
            if (jsonResponse != null) {
                PlayerStatsSummaryListEnity playerList = new Gson().fromJson(jsonResponse.toString(), PlayerStatsSummaryListEnity.class);
                if (playerList != null) {
                    if (playerList != null && playerList.getPlayerStatSummaries() != null &&
                            playerList.getPlayerStatSummaries().size() != 0) {

                        for (PlayerStatsSummaryEnity playerStats : playerList.getPlayerStatSummaries()) {

                            if (playerStats.getPlayerStatSummaryType().equalsIgnoreCase(Constant.SumonerSumary.RANKEDSOLO5X5)) {
                                mView.updateSumonerSumary(playerStats);
                            }
                        }
                    }

                }
            } else {
                Toast.makeText(mActivity, R.string.not_internet_connected, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(mActivity, R.string.not_internet_connected, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void loadSumonerById(String region, String sumonerID) {
        RiotService service = ServiceGenerator.createStaticService(RiotService.class);
        Call<ResponseBody> call = service.getSumonnerByID(region, sumonerID);
        call.enqueue(searchUserByID);
    }

    Callback<ResponseBody> searchUserByID = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            mActivity.dismissDialog();
            JSONObject responseJson = JsonUtil.convertResponseToJson(response);
            if (responseJson != null) {
                JSONObject jsonData = responseJson.optJSONObject(responseJson.keys().next());
                if (jsonData != null) {
                    SumonerEnity sumoner = new Gson().fromJson(jsonData.toString(), SumonerEnity.class);
                    if (sumoner != null) {
                        mView.updateSumoner(sumoner);
                    }
                }
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(mActivity, R.string.not_internet_connected, Toast.LENGTH_SHORT).show();
            mActivity.dismissDialog();
        }
    };
}
