package com.toandoan.lol.listenner;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public interface IUserOverviewListenner {
    void searchUserByNameSuccess(JSONObject response);

    void searchUserByNameFail(String mesaage);
}
