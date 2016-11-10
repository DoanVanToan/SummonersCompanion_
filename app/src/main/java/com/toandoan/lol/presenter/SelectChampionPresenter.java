package com.toandoan.lol.presenter;

import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.listenner.ISelectChampListenner;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.utility.FileOperations;
import com.toandoan.lol.utility.JsonUtil;
import com.toandoan.lol.utility.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public class SelectChampionPresenter {
    private BaseActivity activity;
    private ISelectChampListenner listenner;
    private FileOperations fileOperations;

    public SelectChampionPresenter(BaseActivity activity, ISelectChampListenner listenner) {
        this.activity = activity;
        this.listenner = listenner;
        this.fileOperations = new FileOperations(activity);
    }

    public void loadCacheData() {
        List<ChampionEnity> listData;
        String fullChampStr = fileOperations.readData(Constant.Data.FULL_CHAMP_LIST);
        if (fullChampStr != null) {
            Type listType = new TypeToken<ArrayList<ChampionEnity>>() {
            }.getType();
            listData = new Gson().fromJson(fullChampStr, listType);
            listenner.getAllChampionSuccess(listData);
        } else {
            loadChampionFromServer(Constant.Region.NORTH_AMERICA);
        }
    }

    private void saveListData(List<ChampionEnity> fullChampions) {
        String gson = new Gson().toJson(fullChampions);
        FileOperations fileOperations = new FileOperations(activity);
        boolean isWiteSucces = fileOperations.writeData(Constant.Data.FULL_CHAMP_LIST, gson);
        LogUtil.e("saveListData", isWiteSucces + "");
    }

    public void loadChampionFromServer(String region) {
        activity.showDialog();

        RiotService service = ServiceGenerator.createStaticService(RiotService.class);
        Call<ResponseBody> call = service.getAllChampions(region);
        call.enqueue(getAllChampionCallBack);
    }

    Callback<ResponseBody> getAllChampionCallBack = new Callback<ResponseBody>() {

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            JSONObject responseJson = JsonUtil.convertResponseToJson(response);
            if (responseJson != null) {
                List<ChampionEnity> listData = parseChampFromJson(responseJson.toString());
                listenner.getAllChampionSuccess(listData);
                saveListData(listData);
            } else {
                Toast.makeText(activity, activity.getString(R.string.not_internet_connected), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            activity.dismissDialog();
            Toast.makeText(activity, activity.getString(R.string.not_internet_connected), Toast.LENGTH_SHORT).show();
        }
    };

    public List<ChampionEnity> parseChampFromJson(String fullChampStr) {
        List<ChampionEnity> result = new ArrayList<>();
        if (!TextUtils.isEmpty(fullChampStr)) {
            try {
                JSONObject dataJsonObj = new JSONObject(fullChampStr);
                if (dataJsonObj != null) {
                    JSONObject dataObj = JsonUtil.getJsonData(dataJsonObj);
                    if (dataObj != null) {
                        Iterator<String> iter = dataObj.keys();
                        while (iter.hasNext()) {
                            String key = iter.next();
                            JSONObject championObj = dataObj.optJSONObject(key);
                            ChampionEnity champion = new Gson().fromJson(championObj.toString(), ChampionEnity.class);

                            if (champion != null) {
                                result.add(champion);
                            }

                        }
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
