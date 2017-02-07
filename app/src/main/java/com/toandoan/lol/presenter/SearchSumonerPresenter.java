package com.toandoan.lol.presenter;

import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.toandoan.lol.R;
import com.toandoan.lol.activity.SumonerDetailActivity;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.SumonerEnity;
import com.toandoan.lol.mvp_abstract.SearchSumonerContract;
import com.toandoan.lol.utility.JsonUtil;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public class SearchSumonerPresenter implements SearchSumonerContract.Presenter {
    public BaseActivity mActivity;
    public SearchSumonerContract.View mView;

    public SearchSumonerPresenter(BaseActivity mActivity, SearchSumonerContract.View view) {
        this.mActivity = mActivity;
        this.mView = view;
    }

    @Override
    public void searchSumonerByName(String region, String name) {
        if (!TextUtils.isEmpty(region)) {
        }
        mActivity.showDialog();
        RiotService service = ServiceGenerator.createService(RiotService.class, mActivity);
        Call<ResponseBody> call = service.getSumonerByName(region, name, Constant.ApiKeyValue.API_KEY_VALUE);
        call.enqueue(searchUserByName);

    }

    Callback<ResponseBody> searchUserByName = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            mActivity.dismissDialog();
            JSONObject responseJson = JsonUtil.convertResponseToJson(response);
            if (responseJson != null) {
                JSONObject jsonData = responseJson.optJSONObject(responseJson.keys().next());
                if (jsonData!=null){
                    SumonerEnity sumoner = new Gson().fromJson(jsonData.toString(), SumonerEnity.class);
                    mView.updateUISumoner(sumoner);
                    SumonerDetailActivity.startActivity(mActivity, sumoner);
                }else {
                    Toast.makeText(mActivity, R.string.soming_was_wrong, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(mActivity, R.string.not_internet_connected, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(mActivity, R.string.not_internet_connected, Toast.LENGTH_SHORT).show();
            mActivity.dismissDialog();
        }
    };
}
