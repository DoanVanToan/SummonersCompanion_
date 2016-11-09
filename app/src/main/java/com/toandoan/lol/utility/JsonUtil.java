package com.toandoan.lol.utility;

import android.content.Context;


import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Response;


public class JsonUtil {
    private static final String CODE_USER_BLOCK = "1074";
    private static final String CODE_USER_NOT_FOUND = "1009";
    private static final String CODE_LIKE_STATUS_INCORRECT = "1026";
    private static final String CODE_POST_NOT_FOUND = "1017";
    private static final String CODE_REQUEST_SUCCESS = "1000";
    private static final String CODE_REQUEST_SUCCESS_LAZADA = "200";
    private static final String CODE_REQUEST_EXISTED = "1058";
    private static final String CODE_JSON_ERROR = "8766";
    private static final String CODE_AUTHEN_TOKEN = "1007";
    private static final String CODE_REMOVE_FRIEND_NOT_OK = "1033";

    private static final String STATUS = "status";
    private static final String CODE = "code";
    private static final String MESSAGE = "message";
    private static final String DATA = "data";
    private static final String USERS = "users";
    private static final String OPTION = "options";
    private static final String REFERRERS = "referrers";
    private static final String MEMBERS = "members";
    private static final String CHATGROUP = "ChatGroup";
    private static final String PAKAGES = "packages";
    private static final String SLIDER_IMGS = "slider_imgs";

    public static JSONObject convertResponseToJson(Response<ResponseBody> response) {
        JSONObject jsonObject = new JSONObject();
        try {
            String dataResponse = response.body().string();
            jsonObject = new JSONObject(dataResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONObject getJsonData(JSONObject jsonObject) {
        JSONObject result = null;
        if (jsonObject != null&& !jsonObject.isNull(DATA)) {
            result = jsonObject.optJSONObject(DATA);
        }
        return result;
    }

    public static JSONArray getJsonArrayData(JSONObject jsonObject) {
        JSONArray result = null;
        if (jsonObject != null&& !jsonObject.isNull(DATA)) {
            result = jsonObject.optJSONArray(DATA);
        }
        return result;
    }


    public static JSONObject getJsonStatus(JSONObject json) {
        if (json != null) {
            JSONObject jStatus = json.optJSONObject(STATUS);
            return jStatus;
        } else {
            return null;
        }
    }

    public static String getStatusCode(JSONObject json) {
        try {
            if (json != null) {
                JSONObject jStatus = json.optJSONObject(STATUS);
                if (jStatus != null) {
                    return jStatus.optString(CODE);
                } else {
                    return CODE_JSON_ERROR;
                }
            } else {
                return CODE_JSON_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CODE_JSON_ERROR;
        }
    }

    public static String getStatusMsg(JSONObject json) {
        String message = "";
        if (json != null) {
            JSONObject jStatus = json.optJSONObject(STATUS);
            if (jStatus != null) {
                message = jStatus.optString(MESSAGE);
            }
        }
        return message;
    }


}
