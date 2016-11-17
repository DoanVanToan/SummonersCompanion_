package com.toandoan.lol.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.UserEnity;
import com.toandoan.lol.model.match.MatchList;
import com.toandoan.lol.model.match.MatchReference;
import com.toandoan.lol.model.match_detail.MatchDetail;
import com.toandoan.lol.model.match_detail.Participant;
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
    private MatchList mMatchList;
    private List<MatchReference> mMatchReferences;
    private List<MatchDetail> mMatchDetails;
    private int mCurrentPos;
    private int mMaxSize;
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
        Call<ResponseBody> call = service.getSumonnerMatchesList(region, id);
        call.enqueue(getSumonerMatchesListCallback);
    }

    @Override
    public void getMatchByID(String region, String matchID) {
        RiotService service = ServiceGenerator.createService(RiotService.class);
        Call<ResponseBody> call = service.getSumonnerMatcheByID(region, matchID);
        call.enqueue(getSumonerMatchByIDCallback);
    }

    Callback<ResponseBody> getSumonerMatchesListCallback = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            JSONObject responseJson = JsonUtil.convertResponseToJson(response);
            if (responseJson != null) {
                LogUtil.e(TAG, responseJson.toString());
                mMatchList = new Gson().fromJson(responseJson.toString(), MatchList.class);
                mMatchReferences = mMatchList.getMatches();
                if (mMatchReferences != null && mMatchReferences.size() != 0) {
                    mMatchDetails = new ArrayList<>();
                    mCurrentPos = 0;
                    mMaxSize = mMatchReferences.size() < Constant.MAX_MATCH_SIZE ?
                            mMatchReferences.size() : Constant.MAX_MATCH_SIZE;
                    getMatchByID(Constant.Region.NORTH_AMERICA, String.valueOf(mMatchReferences.get(mCurrentPos).getMatchId()));
                } else {
                    Toast.makeText(mActivity, mActivity.getString(R.string.no_match_found), Toast.LENGTH_SHORT).show();
                }


            } else {
                Toast.makeText(mActivity, mActivity.getString(R.string.not_internet_connected), Toast.LENGTH_SHORT).show();
                mActivity.dismissDialog();
            }


        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(mActivity, mActivity.getString(R.string.not_internet_connected), Toast.LENGTH_SHORT).show();
            mActivity.dismissDialog();
        }
    };

    public List<Participant> getHistoryMatch() {
        List<Participant> userParticipants = new ArrayList<>();
        if (mMatchDetails == null || mMatchDetails.size() == 0) {
            return userParticipants;
        }

        for (MatchDetail matchDetail : mMatchDetails) {
            int participantId = -1;
            if (matchDetail.getParticipantIdentities()==null || matchDetail.getParticipantIdentities().size()==0) break;

            for (int i = 0; i < matchDetail.getParticipantIdentities().size(); i++) {
                long sumonerId = matchDetail.getParticipantIdentities().get(i).getPlayer().getSummonerId();
                if (sumonerId == mUser.getId()) {
                    participantId = matchDetail.getParticipantIdentities().get(i).getParticipantId();
                    break;
                }
            }
            if (participantId == -1) break;

            for (int i = 0; i < matchDetail.getParticipants().size(); i++) {
                if (matchDetail.getParticipants().get(i).getParticipantId() == participantId) {
                    matchDetail.getParticipants().get(i).setMatchDuration(matchDetail.getMatchDuration());
                    userParticipants.add(matchDetail.getParticipants().get(i));
                    break;
                }
            }
        }
        return userParticipants;
    }

    Callback<ResponseBody> getSumonerMatchByIDCallback = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            mCurrentPos++;
            JSONObject responseJson = JsonUtil.convertResponseToJson(response);
            if (responseJson != null) {
                LogUtil.e(TAG, mCurrentPos + "_" + responseJson.toString());
                MatchDetail matchDetail = new Gson().fromJson(responseJson.toString(), MatchDetail.class);
                if (matchDetail != null) mMatchDetails.add(matchDetail);
            } else {
                Toast.makeText(mActivity, mActivity.getString(R.string.not_internet_connected), Toast.LENGTH_SHORT).show();
            }

            if (mCurrentPos != mMaxSize) {
                getMatchByID(Constant.Region.NORTH_AMERICA, String.valueOf(mMatchReferences.get(mCurrentPos).getMatchId()));
            } else {
                mActivity.dismissDialog();
                LogUtil.e(TAG, mMatchDetails.size() + "");
                mView.updateHistoryMatches(getHistoryMatch());
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            if (mCurrentPos != mMatchReferences.size() - 1) {
                getMatchByID(Constant.Region.NORTH_AMERICA, String.valueOf(mMatchReferences.get(mCurrentPos).getMatchId()));
            } else {
                mActivity.dismissDialog();
                LogUtil.e(TAG, mMatchDetails.size() + "");
                mView.updateHistoryMatches(getHistoryMatch());
            }
        }
    };
}
