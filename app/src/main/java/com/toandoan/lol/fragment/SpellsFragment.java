package com.toandoan.lol.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.database.impl.SpellsImpl;
import com.toandoan.lol.model.SpellEnity;
import com.toandoan.lol.utility.JsonUtil;
import com.toandoan.lol.utility.Utils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpellsFragment extends Fragment {
    private BaseActivity mBaseActivity;


    public SpellsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String region = Utils.getRegion(getContext());
        getAllSpellsFromServer(region);
        return inflater.inflate(R.layout.fragment_spells, container, false);
    }

    public void getAllSpellsFromServer(String region) {
        mBaseActivity.showDialog();

        RiotService service = ServiceGenerator.createStaticService(RiotService.class);
        Call<ResponseBody> call = service.getAllSpells(region);
        call.enqueue(getAllSpellsCallBack);
    }

    Callback<ResponseBody> getAllSpellsCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            JSONObject jsonRespones = JsonUtil.convertResponseToJson(response);
            if (jsonRespones != null) {
                JSONObject jsonData = JsonUtil.getJsonData(jsonRespones);
                HashMap<String, SpellEnity> hmData = new Gson()
                        .fromJson(jsonData.toString(), new TypeToken<HashMap<String, SpellEnity>>() {
                        }.getType());
                List<SpellEnity> spells = new ArrayList<>(hmData.values());
                SpellsImpl database = new SpellsImpl(getContext());
                database.insertListSpells(spells);
            } else {
                Toast.makeText(mBaseActivity, getString(R.string.not_internet_connected), Toast.LENGTH_SHORT).show();
            }

            mBaseActivity.dismissDialog();
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            mBaseActivity.dismissDialog();
            Toast.makeText(mBaseActivity, getString(R.string.not_internet_connected), Toast.LENGTH_SHORT).show();
        }
    };

    public static SpellsFragment newInstance(BaseActivity baseActivity) {
        SpellsFragment spellsFragment  = new SpellsFragment();
        spellsFragment.setBaseActivity(baseActivity);
        return  spellsFragment;
    }

    public BaseActivity getBaseActivity() {
        return mBaseActivity;
    }

    public void setBaseActivity(BaseActivity baseActivity) {
        mBaseActivity = baseActivity;
    }
}
