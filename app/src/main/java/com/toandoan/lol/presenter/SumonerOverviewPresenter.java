package com.toandoan.lol.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.model.rune.RuneEnity;
import com.toandoan.lol.model.sumoner_overview.LeagueEnity;
import com.toandoan.lol.mvp_abstract.SumonerOverviewContract;
import com.toandoan.lol.utility.JsonUtil;
import com.toandoan.lol.utility.LogUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        call.enqueue(getSummonerRuneCallBack);
    }

    Callback<ResponseBody> getSummonerRuneCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            JSONObject jsonResponse = JsonUtil.convertResponseToJson(response);
            LogUtil.e("getSummonerRuneCallBack", jsonResponse.toString());
            if (jsonResponse!=null){
//                JSONObject jsonData = jsonResponse.optJSONObject(jsonResponse.keys().next());
//                if (jsonData!=null){
//                    LeagueEnity leagueEnity = new Gson().fromJson(jsonData.toString(), LeagueEnity.class);
//                    if (leagueEnity!=null){
//                        mView.loadSumonerStats(leagueEnity);
//                    }
//                }
                JSONArray jsonData = jsonResponse.optJSONArray(jsonResponse.keys().next());
                if (jsonData!=null){
                    List<LeagueEnity> listData;
                    Type listType = new TypeToken<ArrayList<LeagueEnity>>() {
                    }.getType();
//                    LinkedTreeMap<String, LeagueEnity> hmTemp = new Gson().fromJson(jsonData.toString(), listType);
                    listData = new Gson().fromJson(jsonData.toString(), listType);
//                    listData = new ArrayList<>(hmTemp.values());
                    if (listData!=null){
                        mView.loadSumonerStats(listData);
                    }else {
                        //TODO some thing
                    }
                }

            }else {
                Toast.makeText(mActivity, R.string.not_internet_connected, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(mActivity, R.string.not_internet_connected, Toast.LENGTH_SHORT).show();
        }
    };
}
