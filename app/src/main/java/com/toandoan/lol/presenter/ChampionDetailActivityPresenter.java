package com.toandoan.lol.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.listenner.ChampionDetailActivityListenner;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.utility.FileOperations;
import com.toandoan.lol.utility.JsonUtil;
import com.toandoan.lol.utility.LogUtil;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by ToanDoan on 10/15/2016.
 */

public class ChampionDetailActivityPresenter {
    private BaseActivity activity;
    private ChampionDetailActivityListenner listenner;
    private FileOperations fileOperations;

    public ChampionDetailActivityPresenter(BaseActivity activity, ChampionDetailActivityListenner listenner) {
        this.activity = activity;
        this.listenner = listenner;
        this.fileOperations = new FileOperations(activity);
    }

    public void getChampionByID(String region, String id) {
        RiotService service = ServiceGenerator.createStaticService(RiotService.class);
        Call<ResponseBody> call = service.getChampionByID(region, id);
        call.enqueue(getAllChampionCallBack);
    }


    public void loadCacheData(String id) {
        ChampionEnity championEnity;
        String fullChampStr = fileOperations.readData(Constant.Data.CHAMPION_ + id);
        if (fullChampStr != null) {
            championEnity = new Gson().fromJson(fullChampStr, ChampionEnity.class);
            listenner.getChampionByIDSucces(championEnity);
        } else {
            activity.showDialog();
            getChampionByID(Constant.Region.NORTH_AMERICA, id);
        }
    }


    Callback<ResponseBody> getAllChampionCallBack = new Callback<ResponseBody>() {

        @Override
        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
            activity.dismissDialog();
            JSONObject responseJson = JsonUtil.convertResponseToJson(response);
            if (responseJson != null) {
                LogUtil.e("getAllChampionCallBack>>", responseJson.toString());
                ChampionEnity championEnity = new Gson().fromJson(responseJson.toString(), ChampionEnity.class);

                listenner.getChampionByIDSucces(championEnity);
                saveChampion(championEnity);

            } else {
                Toast.makeText(activity, activity.getString(R.string.not_internet_connected), Toast.LENGTH_SHORT).show();
                listenner.getChampionByIDFail();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            activity.dismissDialog();
            Toast.makeText(activity, activity.getString(R.string.not_internet_connected), Toast.LENGTH_SHORT).show();
            listenner.getChampionByIDFail();
        }
    };


    private void saveChampion(ChampionEnity championEnity) {
        String gson = new Gson().toJson(championEnity);
        FileOperations fileOperations = new FileOperations(activity);
        boolean isWiteSucces = fileOperations.writeData(Constant.Data.CHAMPION_ + championEnity.getId(), gson);
        LogUtil.e("saveChampion", isWiteSucces + "");
    }
}
