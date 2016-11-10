package com.toandoan.lol.presenter;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.database.impl.MasteriesImpl;
import com.toandoan.lol.listenner.MasteriesListenner;
import com.toandoan.lol.model.matery.MasteryEnity;
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

public class MasteriesPresenter {
    private final BaseActivity mBaseActivity;
    private String TAG;
    private MasteriesListenner mListenner;
    private MasteriesImpl mMasteriesController;

    public MasteriesPresenter(BaseActivity mBaseActivity, MasteriesListenner mListenner) {
        this.mBaseActivity = mBaseActivity;
        this.TAG = mBaseActivity.getClass().getName();
        this.mListenner = mListenner;
    }

    public void getALlMasteriesFromDatabase() {
        List<MasteryEnity> listData;
        mMasteriesController = new MasteriesImpl(mBaseActivity);
        listData = mMasteriesController.getAllMasteries();
        mListenner.getAllMasteriesSuccessful(listData);
    }

    public void getMasteriesByTypeTree(String typeTree) {
        List<MasteryEnity> listData;
        mMasteriesController = new MasteriesImpl(mBaseActivity);
        listData = mMasteriesController.getMasteriesByType(typeTree);
        mListenner.getAllMasteriesSuccessful(listData);
    }

    public void getAllMasteriesFromServer(String region) {
        mBaseActivity.showDialog();

        RiotService service = ServiceGenerator.createStaticService(RiotService.class);
        Call<ResponseBody> call = service.getAllMasteries(region);
        call.enqueue(getAllMasteriesCallBack);
    }

    Callback<ResponseBody> getAllMasteriesCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            JSONObject jsonObject = JsonUtil.convertResponseToJson(response);
            if (jsonObject != null) {
                LogUtil.e(TAG, "getAllMasteriesCallBack>" + jsonObject.toString());

                JSONObject data = JsonUtil.getJsonData(jsonObject);
                if (data != null) {
                    List<MasteryEnity> listData;
                    Type listType = new TypeToken<Map<String, MasteryEnity>>() {
                    }.getType();
                    LinkedTreeMap<String, MasteryEnity> hmTemp = new Gson().fromJson(data.toString(), listType);
                    listData = new ArrayList<>(hmTemp.values());

                    mListenner.getAllMasteriesSuccessful(listData);
                    saveItemToDB(listData);
                } else {
                    mListenner.getAllMasteriesFail(JsonUtil.getStatusMsg(jsonObject));
                }
            } else {
                mListenner.getAllMasteriesFail(mBaseActivity.getString(R.string.not_internet_connected));
            }

            mBaseActivity.dismissDialog();

        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            mBaseActivity.dismissDialog();
            mListenner.getAllMasteriesFail(mBaseActivity.getString(R.string.not_internet_connected));
        }
    };

    public void saveItemToDB(List<MasteryEnity> list) {
        MasteriesImpl database = new MasteriesImpl(mBaseActivity);
        database.insertListMasteries(list);
    }
}
