package com.toandoan.lol.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.model.SumonerEnity;
import com.toandoan.lol.mvp_abstract.SumonerDetailAsbtract;
import com.toandoan.lol.utility.JsonUtil;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ToanDoan on 11/25/2016.
 */

public class SumonerDetailPresenter implements SumonerDetailAsbtract.Presenter {

    private BaseActivity mActivity;
    private SumonerDetailAsbtract.View mView;

    public SumonerDetailPresenter(BaseActivity mActivity, SumonerDetailAsbtract.View mView) {
        this.mActivity = mActivity;
        this.mView = mView;
    }

    @Override
    public void getSumonerByID(String region, String sumonerID) {
        mActivity.showDialog();
        RiotService service = ServiceGenerator.createStaticService(RiotService.class);
        Call<ResponseBody> call = service.getSumonnerByID(region, sumonerID);
        call.enqueue(getUserCallBack);
    }

    @Override
    public void getSumonerByName(String region, String sumonerName) {
        RiotService service = ServiceGenerator.createStaticService(RiotService.class);
        Call<ResponseBody> call = service.getSumonerByName(region, sumonerName);
        call.enqueue(getUserCallBack);
    }

    Callback<ResponseBody> getUserCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            mActivity.dismissDialog();
            JSONObject responseJson = JsonUtil.convertResponseToJson(response);
            if (responseJson != null) {
                JSONObject jsonData = responseJson.optJSONObject(responseJson.keys().next());
                if (jsonData != null) {
                    SumonerEnity sumoner = new Gson().fromJson(jsonData.toString(), SumonerEnity.class);
                    if (sumoner != null) {
                        mView.initViewPager(sumoner);
                    }
                }
            }

            mActivity.showDialog();
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(mActivity, R.string.not_internet_connected, Toast.LENGTH_SHORT).show();
            mActivity.dismissDialog();
        }
    };


}
