package com.toandoan.lol.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.model.sumoner_overview.LeagueEnity;
import com.toandoan.lol.mvp_abstract.SumonerOverviewContract;
import com.toandoan.lol.utility.JsonUtil;

import org.json.JSONObject;

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
            JSONObject jsonData = JsonUtil.convertResponseToJson(response);
            if (jsonData!=null){
                LeagueEnity leagueEnity = new Gson().fromJson(jsonData.toString(), LeagueEnity.class);
                if (leagueEnity!=null){

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
