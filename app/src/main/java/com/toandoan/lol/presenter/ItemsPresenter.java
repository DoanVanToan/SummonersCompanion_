package com.toandoan.lol.presenter;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.toandoan.lol.R;
import com.toandoan.lol.api.base.ServiceGenerator;
import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.database.impl.MyItemImpl;
import com.toandoan.lol.listenner.ItemListenner;
import com.toandoan.lol.model.item.ItemEnity;
import com.toandoan.lol.utility.JsonUtil;

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

public class ItemsPresenter {
    private final BaseActivity mBaseActivity;
    private ItemListenner mListenner;
    private String TAG;
    private MyItemImpl mMyItemImpl;

    public ItemsPresenter(BaseActivity mBaseActivity, ItemListenner mListenner) {
        this.mBaseActivity = mBaseActivity;
        this.mListenner = mListenner;
        this.TAG = mBaseActivity.getClass().getName();
    }

    public void getALlItemFromDatabase() {
        List<ItemEnity> listData;
        mMyItemImpl = new MyItemImpl(mBaseActivity);
        listData = mMyItemImpl.getAllItem();
        mListenner.getAllItemSuccessful(listData);
    }

    public void getAllItemFromServer(String region) {
        mBaseActivity.showDialog();

        RiotService service = ServiceGenerator.createStaticService(RiotService.class);
        Call<ResponseBody> call = service.getAllItems(region);
        call.enqueue(getAllItemCallBack);
    }

    Callback<ResponseBody> getAllItemCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            JSONObject jsonObject = JsonUtil.convertResponseToJson(response);
            if (jsonObject != null) {
                JSONObject data = JsonUtil.getJsonData(jsonObject);
                if (data != null) {
                    List<ItemEnity> listData;
                    Type listType = new TypeToken<Map<String, ItemEnity>>() {
                    }.getType();
                    LinkedTreeMap<String, ItemEnity> hmTemp = new Gson().fromJson(data.toString(), listType);
                    listData = new ArrayList<>(hmTemp.values());

                    mListenner.getAllItemSuccessful(listData);
                    saveItemToDB(listData);
                } else {
                    mListenner.getAllItemFail(JsonUtil.getStatusMsg(jsonObject));
                }
            } else {
                mListenner.getAllItemFail(mBaseActivity.getString(R.string.not_internet_connected));
            }

            mBaseActivity.dismissDialog();

        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            mBaseActivity.dismissDialog();
            mListenner.getAllItemFail(mBaseActivity.getString(R.string.not_internet_connected));
        }
    };

    public void saveItemToDB(List<ItemEnity> list) {
        MyItemImpl database = new MyItemImpl(mBaseActivity);
        database.insertItem(list);
    }
}
