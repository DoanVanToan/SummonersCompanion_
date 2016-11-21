package com.toandoan.lol.presenter;

import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.toandoan.lol.R;
import com.toandoan.lol.activity.MatchDetailActivity;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.UserEnity;
import com.toandoan.lol.model.match.MatchList;
import com.toandoan.lol.model.match.MatchReference;
import com.toandoan.lol.model.match_detail.MatchDetail;
import com.toandoan.lol.model.match_detail.Participant;
import com.toandoan.lol.model.recent_match.GameEnity;
import com.toandoan.lol.model.recent_match.RecentGamesEnity;
import com.toandoan.lol.mvp_abstract.SumonerMatchesListAbstract;
import com.toandoan.lol.utility.JsonUtil;
import com.toandoan.lol.utility.LogUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by framgia on 17/11/2016.
 */

public class SumonerMatchesListPresenter implements SumonerMatchesListAbstract.Presenter {
    private final static String TAG = "SumonerMatchesListPresenter";
    private SumonerMatchesListAbstract.View mView;
    private BaseActivity mActivity;
    private RecentGamesEnity mRecentGames;
    private UserEnity mUser;

    public SumonerMatchesListPresenter(BaseActivity activity, SumonerMatchesListAbstract.View view, UserEnity user) {
        mActivity = activity;
        mView = view;
        mUser = user;
    }

    @Override
    public void getAllMatches(String region, String id) {
        mActivity.showDialog();
        RiotService service = ServiceGenerator.createService(RiotService.class);
        Call<ResponseBody> call = service.getSumonnerRecentMatches(region, id);
        call.enqueue(getSumonerMatchesListCallback);
    }

    @Override
    public void onItemClickListenner(View v, int position, GameEnity gameEnity) {
        MatchDetailActivity.startActivity(mActivity, gameEnity, (int) mRecentGames.getSummonerId());
    }


    Callback<ResponseBody> getSumonerMatchesListCallback = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            JSONObject responseJson = JsonUtil.convertResponseToJson(response);
            if (responseJson != null) {
                LogUtil.e(TAG, responseJson.toString());
                mRecentGames = new Gson().fromJson(responseJson.toString(), RecentGamesEnity.class);
                if (mRecentGames != null) {
                    mView.updateHistoryMatches(mRecentGames.getGames());
                }
            } else {
                Toast.makeText(mActivity, mActivity.getString(R.string.not_internet_connected), Toast.LENGTH_SHORT).show();
            }

            mActivity.dismissDialog();
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(mActivity, mActivity.getString(R.string.not_internet_connected), Toast.LENGTH_SHORT).show();
            mActivity.dismissDialog();
        }
    };

}
