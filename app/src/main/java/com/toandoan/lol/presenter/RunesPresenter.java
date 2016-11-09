package com.toandoan.lol.presenter;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.database.impl.RunesImpl;
import com.toandoan.lol.listenner.RuneListenner;
import com.toandoan.lol.model.rune.RuneEnity;
import com.toandoan.lol.utility.JsonUtil;
import com.toandoan.lol.utility.LogUtil;

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
 * Created by ToanDoan on 10/29/2016.
 */

public class RunesPresenter {
    private final BaseActivity mBaseActivity;
    private String TAG;
    private RuneListenner mListenner;
    private RunesImpl mRuneController;

    public RunesPresenter(BaseActivity mBaseActivity, RuneListenner mListenner) {
        this.mBaseActivity = mBaseActivity;
        this.TAG = mBaseActivity.getClass().getName();
        this.mListenner = mListenner;
    }

    public void getALlRunesFromDatabase() {
        List<RuneEnity> listData;
        mRuneController = new RunesImpl(mBaseActivity);
        listData = mRuneController.getAllRunes();
        mListenner.getAllRunesSuccessful(listData);
    }

    public void getAllRunesFromServer(String region) {
        mBaseActivity.showDialog();

        RiotService service = ServiceGenerator.createStaticService(RiotService.class);
        Call<ResponseBody> call = service.getAllRunes(region);
        call.enqueue(getAllRunesCallBack);
    }

    Callback<ResponseBody> getAllRunesCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            JSONObject jsonObject = JsonUtil.convertResponseToJson(response);
            if (jsonObject != null) {
                LogUtil.e(TAG, "getAllRunesCallBack>" + jsonObject.toString());

                JSONObject data = JsonUtil.getJsonData(jsonObject);
                if (data != null) {
                    List<RuneEnity> listData;
                    Type listType = new TypeToken<Map<String, RuneEnity>>() {
                    }.getType();
                    LinkedTreeMap<String, RuneEnity> hmTemp = new Gson().fromJson(data.toString(), listType);
                    listData = new ArrayList<>(hmTemp.values());

                    mListenner.getAllRunesSuccessful(listData);
                    saveItemToDB(listData);
                } else {
                    mListenner.getAllRunesFail(JsonUtil.getStatusMsg(jsonObject));
                }
            } else {
                mListenner.getAllRunesFail(mBaseActivity.getString(R.string.not_internet_connected));
            }

            mBaseActivity.dismissDialog();

        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            mBaseActivity.dismissDialog();
            mListenner.getAllRunesFail(mBaseActivity.getString(R.string.not_internet_connected));
        }
    };

    public void saveItemToDB(List<RuneEnity> list) {
        RunesImpl database = new RunesImpl(mBaseActivity);
        database.insertListRunes(list);
    }
}
