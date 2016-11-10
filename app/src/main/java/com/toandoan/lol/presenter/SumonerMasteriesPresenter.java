package com.toandoan.lol.presenter;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.database.impl.MasteriesImpl;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.model.matery.MasteryEnity;
import com.toandoan.lol.model.matery.PageMasteries;
import com.toandoan.lol.model.rune.RuneEnity;
import com.toandoan.lol.mvp_abstract.SumonerMasteriesAbstract;
import com.toandoan.lol.utility.JsonUtil;
import com.toandoan.lol.utility.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by framgia on 10/11/2016.
 */

public class SumonerMasteriesPresenter implements SumonerMasteriesAbstract.Presenter {
    private SumonerMasteriesAbstract.View mView;
    private BaseActivity mActivity;
    private List<PageMasteries> mPageMasteries;
    private int mFerocityCount, mCunningCount, mResolveCount;
    private MasteriesImpl masteryDatabase;
    private PageMasteries mCurrentPage;

    public SumonerMasteriesPresenter(BaseActivity mActivity, SumonerMasteriesAbstract.View mView) {
        this.mActivity = mActivity;
        this.mView = mView;
        this.masteryDatabase = new MasteriesImpl(mActivity);
    }

    @Override
    public void loadSumonerMasteries(String region, String id) {
        RiotService service = ServiceGenerator.createStaticService(RiotService.class);
        Call<ResponseBody> call = service.getSumonnerMasteries(region, id);
        call.enqueue(getSummonerMasteryCallBack);
    }

    @Override
    public void getCountMasteries(List<MasteryEnity> listPages) {
        mCunningCount = 0;
        mResolveCount = 0;
        mFerocityCount = 0;
        for (int i = 0; i < listPages.size(); i++) {
            MasteryEnity masteryEnity = listPages.get(i);
            if (masteryEnity != null && masteryEnity.getMasteryTree() != null) {
                if (masteryEnity.getMasteryTree().equalsIgnoreCase(MasteriesImpl.TYPE_CUNNING)) {
                    mCunningCount += masteryEnity.getRank();
                } else {
                    if (masteryEnity.getMasteryTree().equalsIgnoreCase(MasteriesImpl.TYPE_FEROCITY)) {
                        mFerocityCount += masteryEnity.getRank();
                    } else {
                        mResolveCount += masteryEnity.getRank();
                    }
                }
            }

        }
    }


    Callback<ResponseBody> getSummonerMasteryCallBack = new Callback<ResponseBody>() {

        @Override
        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
            mActivity.dismissDialog();
            JSONObject responseJson = JsonUtil.convertResponseToJson(response);
            if (responseJson != null) {
                JSONObject jsonData = responseJson.optJSONObject(responseJson.keys().next());
                JSONArray jsonPages = jsonData.optJSONArray(Constant.ApiKey.PAGES);
                mPageMasteries = new ArrayList<>();
                for (int i = 0; i < jsonPages.length(); i++) {
                    try {
                        mPageMasteries.add(new Gson().fromJson(jsonPages.getJSONObject(i).toString(), PageMasteries.class));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                LogUtil.e("getSummonerMasteryCallBack>>", String.valueOf(mPageMasteries.size()));


                getMasteriesFromDatabase();
                mCurrentPage = mPageMasteries.get(0);
                getCountMasteries(mCurrentPage.getMasteries());

                mView.initViewPager(mCurrentPage.getMasteries());
                mView.updateTabLayout(mFerocityCount, mCunningCount, mResolveCount);


            } else {
                Toast.makeText(mActivity, mActivity.getString(R.string.not_internet_connected), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(mActivity, mActivity.getString(R.string.not_internet_connected), Toast.LENGTH_SHORT).show();
        }
    };

    void getMasteriesFromDatabase() {
        for (int i = 0; i < mPageMasteries.size(); i++) {
            PageMasteries pages = mPageMasteries.get(i);
            List<MasteryEnity> temps = new ArrayList<>();
            for (int j = 0; j < pages.getMasteries().size(); j++) {
                MasteryEnity masteryEnity = pages.getMasteries().get(j);

                String id = String.valueOf(masteryEnity.getId());
                if (id.startsWith("4")) {
                    id = id.substring(1, id.length());
                    id = "6" + id;
                }

                MasteryEnity masteryEnityDB = masteryDatabase.getMasteryByID(id);

                if (masteryEnityDB != null) {
                    masteryEnityDB.setRank(masteryEnity.getRank());
                    temps.add(masteryEnityDB);
                }else {
                    temps.add(masteryEnity);
                }

            }
            pages.setMasteries(temps);
        }
    }
}
