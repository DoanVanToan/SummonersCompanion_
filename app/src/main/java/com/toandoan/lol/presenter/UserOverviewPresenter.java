package com.toandoan.lol.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.toandoan.lol.R;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.listenner.IUserOverviewListenner;
import com.toandoan.lol.utility.JsonUtil;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public class UserOverviewPresenter {
    public Context mContext;
    private IUserOverviewListenner listenner;

    public UserOverviewPresenter(Context mContext, IUserOverviewListenner listenner) {
        this.mContext = mContext;
        this.listenner = listenner;
    }

    public void callApiSearch(String region, String name) {
        if (!TextUtils.isEmpty(region)) {
//            Constant.Config.REGION = region;
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.Config.getBaseUrl())
                .build();

        RiotService service = retrofit.create(RiotService.class);

        Call<ResponseBody> call = service.findUserByName(region, name);
        call.enqueue(searchUserByName);

    }

    Callback<ResponseBody> searchUserByName = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            JSONObject responseJson = JsonUtil.convertResponseToJson(response);
            if (responseJson != null) {
                if (JsonUtil.getJsonStatus(responseJson) == null) {
                    listenner.searchUserByNameSuccess(responseJson);
                }else {
                    listenner.searchUserByNameFail(JsonUtil.getStatusMsg(responseJson));
                }
            }else {
                listenner.searchUserByNameFail(mContext.getString(R.string.something_wrong));
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            listenner.searchUserByNameFail(t.toString());
        }
    };
}
