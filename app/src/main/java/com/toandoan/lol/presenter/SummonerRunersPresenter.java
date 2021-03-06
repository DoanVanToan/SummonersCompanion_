package com.toandoan.lol.presenter;

import android.widget.Toast;

import com.google.gson.Gson;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.database.impl.RunesImpl;
import com.toandoan.lol.model.rune.PageRunes;
import com.toandoan.lol.model.rune.RuneEnity;
import com.toandoan.lol.mvp_abstract.SumonerRunesAbstract;
import com.toandoan.lol.utility.JsonUtil;
import com.toandoan.lol.utility.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by framgia on 11/11/2016.
 */
public class SummonerRunersPresenter implements SumonerRunesAbstract.Presenter {
    private SumonerRunesAbstract.View mView;
    private BaseActivity mActivity;
    private List<PageRunes> mPageRunes;
    private RunesImpl mRuneDatabase;

    public SummonerRunersPresenter(BaseActivity ctivity, SumonerRunesAbstract.View view) {
        this.mActivity = ctivity;
        this.mView = view;
        this.mRuneDatabase = new RunesImpl(mActivity);
    }

    @Override
    public void loadSumonerRunes(String region, String id) {
        mActivity.showDialog();
        RiotService service = ServiceGenerator.createService(RiotService.class, mActivity);
        Call<ResponseBody> call = service.getSumonnerRunes(region,
            id,
            Constant.ApiKeyValue.API_KEY_VALUE);
        call.enqueue(getSummonerRuneCallBack);
    }

    Callback<ResponseBody> getSummonerRuneCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
            mActivity.dismissDialog();
            JSONObject responseJson = JsonUtil.convertResponseToJson(response);
            if (responseJson != null) {
                JSONObject jsonData = responseJson.optJSONObject(responseJson.keys().next());
                JSONArray jsonPages = jsonData.optJSONArray(Constant.ApiKey.PAGES);
                mPageRunes = new ArrayList<>();
                for (int i = 0; i < jsonPages.length(); i++) {
                    try {
                        mPageRunes.add(new Gson()
                            .fromJson(jsonPages.getJSONObject(i).toString(), PageRunes.class));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                getRuneDetailFromDB();
                PageRunes firstPage = mPageRunes.get(0);
                mView.initViewPager(firstPage.getCountRunes());
                mView.updateTabLayout(firstPage.getPageTitle());
                mView.updateSpinner(mPageRunes);
                LogUtil.e("getSummonerRuneCallBack>>", String.valueOf(mPageRunes.size()));
            } else {
                Toast.makeText(mActivity, mActivity.getString(R.string.not_internet_connected),
                    Toast.LENGTH_SHORT).show();
            }
            mActivity.dismissDialog();
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            mActivity.dismissDialog();
            Toast.makeText(mActivity, mActivity.getString(R.string.not_internet_connected),
                Toast.LENGTH_SHORT).show();
        }
    };

    private void getRuneDetailFromDB() {
        for (PageRunes page : mPageRunes) {
            List<RuneEnity> temps = new ArrayList<>();
            if (page.getRunes() != null) {
                for (RuneEnity rune : page.getRunes()) {
                    int slotID = rune.getmRuneSlotId();
                    rune = mRuneDatabase.getRuneByID(rune.getRuneId());
                    rune.setRuneSlotId(slotID);
                    rune.setRuneId(rune.getId());
                    temps.add(rune);
                }
            }
            page.setRunes(temps);
            page.updateCountRunes();
        }
    }

    @Override
    public void onSpinnerSelected(PageRunes page) {
        mView.initViewPager(page.getCountRunes());
        mView.updateTabLayout(page.getPageTitle());
    }
}
