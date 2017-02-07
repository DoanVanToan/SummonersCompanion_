package com.toandoan.lol.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.sumoner_overview.LeagueEnity;
import com.toandoan.lol.model.sumoner_overview.LeagueEntryEnity;
import com.toandoan.lol.mvp_abstract.ChallengerContract;
import com.toandoan.lol.utility.JsonUtil;
import com.toandoan.lol.utility.LogUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by framgia on 25/11/2016.
 */
public class ChallengerPresenter implements ChallengerContract.Presenter {
    private ChallengerContract.View mView;
    private BaseActivity mActivity;
    private List<LeagueEntryEnity> mLeagueEntries;
    private String mTypeRank;

    public ChallengerPresenter(BaseActivity activity, ChallengerContract.View view) {
        mActivity = activity;
        mView = view;
    }

    @Override
    public void loadChallengerSumoner(String region) {
        RiotService service = ServiceGenerator.createService(RiotService.class, mActivity);
        Call<ResponseBody> call = service.getRankChallange(region,
            Constant.ApiKeyValue.RANK_TYPE_5_5,
            Constant.ApiKeyValue.API_KEY_VALUE);
        call.enqueue(getChallengerCallBack);
    }

    @Override
    public void searchSumoner(String key) {
        if (mLeagueEntries != null) {
            List<LeagueEntryEnity> result = new ArrayList<>();
            if (key.length() == 0) {
                mView.updateChallengeSumoner(mLeagueEntries);
                return;
            }
            for (LeagueEntryEnity leangue : mLeagueEntries) {
                if (leangue.getPlayerOrTeamName().toLowerCase().contains(key.toLowerCase())) {
                    result.add(leangue);
                }
            }
            mView.updateChallengeSumoner(result);
        }
    }

    @Override
    public void loadSumonerRank(String region, String sumonerID, String typeRank) {
        RiotService service = ServiceGenerator.createService(RiotService.class, mActivity);
        mTypeRank = typeRank;
        Call<ResponseBody> call =
            service.getRankBySumoner(region, sumonerID, Constant.ApiKeyValue.API_KEY_VALUE);
        call.enqueue(getSummonerRankCallBack);
    }

    Callback<ResponseBody> getSummonerRankCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            JSONObject jsonResponse = JsonUtil.convertResponseToJson(response);
            LogUtil.e("getSummonerStatsCallBack", jsonResponse.toString());
            if (jsonResponse != null) {
                JSONArray jsonData = jsonResponse.optJSONArray(jsonResponse.keys().next());
                if (jsonData != null) {
                    List<LeagueEnity> listData;
                    Type listType = new TypeToken<ArrayList<LeagueEnity>>() {
                    }.getType();
                    listData = new Gson().fromJson(jsonData.toString(), listType);
                    if (listData != null) {
                        mLeagueEntries = getListLegueByType(listData);
                        sortListLegue(mLeagueEntries);
                        mView.updateChallengeSumoner(mLeagueEntries);
                        mView.scrollToCurrentPosition();
                    }
                }
            } else {
                Toast.makeText(mActivity, R.string.not_internet_connected, Toast.LENGTH_SHORT)
                    .show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(mActivity, R.string.not_internet_connected, Toast.LENGTH_SHORT).show();
        }
    };

    private List<LeagueEntryEnity> getListLegueByType(List<LeagueEnity> leagueEnity) {
        for (LeagueEnity league : leagueEnity) {
            if (league.getQueue().equals(mTypeRank))
                return league.getEntries();
        }
        return new ArrayList<>();
    }

    Callback<ResponseBody> getChallengerCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            JSONObject jsonResponse = JsonUtil.convertResponseToJson(response);
            LogUtil.e("getChallengerCallBack", jsonResponse.toString());
            if (jsonResponse != null) {
                LeagueEnity league =
                    new Gson().fromJson(jsonResponse.toString(), LeagueEnity.class);
                if (league != null) {
                    sortListLegue(league.getEntries());
                    mLeagueEntries = league.getEntries();
                    mView.updateChallengeSumoner(league.getEntries());
                }
            } else {
                Toast.makeText(mActivity, R.string.not_internet_connected, Toast.LENGTH_SHORT)
                    .show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(mActivity, R.string.not_internet_connected, Toast.LENGTH_SHORT).show();
        }
    };

    public void sortListLegue(List<LeagueEntryEnity> entries) {
        if (entries == null) return;
        Collections.sort(entries, new Comparator<LeagueEntryEnity>() {
            @Override
            public int compare(LeagueEntryEnity o1, LeagueEntryEnity o2) {
                if (o1.getLeaguePoints() > o2.getLeaguePoints()) return -1;
                else if (o1.getLeaguePoints() < o2.getLeaguePoints()) return 1;
                else return 0;
            }
        });
    }
}
