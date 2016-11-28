package com.toandoan.lol.api.base;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

import com.toandoan.lol.api.listenner.RiotService;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.utility.Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by anhdiepmmk on 11/20/15.
 */
public class ServiceGenerator {

    public static <S> S createService(Class<S> serviceClass, Context context) {
        String region = Utils.getRegion(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.Config.getBaseUrl(region))
                .build();

        return retrofit.create(serviceClass);
    }

    public static <S> S createStaticService(Class<S> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.Config.getStaticBaseUrl())
                .build();

        return retrofit.create(serviceClass);
    }

}
