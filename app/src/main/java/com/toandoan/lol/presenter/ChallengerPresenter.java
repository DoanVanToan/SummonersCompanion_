package com.toandoan.lol.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.sumoner_overview.LeagueEnity;
import com.toandoan.lol.model.sumoner_sumary.PlayerStatsSummaryEnity;
import com.toandoan.lol.model.sumoner_sumary.PlayerStatsSummaryListEnity;
import com.toandoan.lol.mvp_abstract.ChallengerContract;
import com.toandoan.lol.utility.JsonUtil;
import com.toandoan.lol.utility.LogUtil;

import org.json.JSONObject;

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

    public ChallengerPresenter(BaseActivity activity, ChallengerContract.View view) {
        mActivity = activity;
        mView = view;
    }

    @Override
    public void loadChallengerSumoner(String region) {
        RiotService service = ServiceGenerator.createStaticService(RiotService.class);
        Call<ResponseBody> call = service.getRankChallange(region);
        call.enqueue(getChallengerCallBack);
    }

    Callback<ResponseBody> getChallengerCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            JSONObject jsonResponse = JsonUtil.convertResponseToJson(response);
            LogUtil.e("getChallengerCallBack", jsonResponse.toString());
            if (jsonResponse != null) {
                LeagueEnity league = new Gson().fromJson(jsonResponse.toString(), LeagueEnity.class);
                if (league != null) {
                    mView.updateChallengeSumoner(league.getEntries());
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
}
