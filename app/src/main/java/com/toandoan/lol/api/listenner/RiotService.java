package com.toandoan.lol.api.listenner;

import com.toandoan.lol.constant.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public interface RiotService {
    @GET(Constant.Api.FIND_BY_NAME)
    Call<ResponseBody> findUserByName(@Path(Constant.ApiKey.REGION) String region
            , @Path(Constant.ApiKey.SUMMONER_NAMES) String userName);

    @GET(Constant.Api.GET_LIST_CHAMPIONS)
    Call<ResponseBody> getAllChampions(@Path(Constant.ApiKey.REGION) String region);

    @GET(Constant.Api.GET_LIST_ITEMS)
    Call<ResponseBody> getAllItems(@Path(Constant.ApiKey.REGION) String region);

    @GET(Constant.Api.GET_CHAMPION_BY_ID)
    Call<ResponseBody> getChampionByID(@Path(Constant.ApiKey.REGION) String region
            , @Path(Constant.ApiKey.ID) String id);

    @GET(Constant.Api.GET_LIST_RUNES)
    Call<ResponseBody> getAllRunes(@Path(Constant.ApiKey.REGION) String region);

    @GET(Constant.Api.GET_LIST_SPELLS)
    Call<ResponseBody> getAllSpells(@Path(Constant.ApiKey.REGION) String region);

    @GET(Constant.Api.GET_LIST_MASTERIES)
    Call<ResponseBody> getAllMasteries(@Path(Constant.ApiKey.REGION) String region);

    @GET(Constant.Api.GET_SUMMONER_MASTERIES)
    Call<ResponseBody> getSumonnerMasteries(@Path(Constant.ApiKey.REGION) String region,
                                            @Path(Constant.ApiKey.SUMMONER_IDS) String summonerID);

    @GET(Constant.Api.GET_SUMMONER_RUNES)
    Call<ResponseBody> getSumonnerRunes(@Path(Constant.ApiKey.REGION) String region,
                                        @Path(Constant.ApiKey.SUMMONER_IDS) String summonerID);

    @GET(Constant.Api.GET_SUMMONER_MATCHES_LIST)
    Call<ResponseBody> getSumonnerMatchesList(@Path(Constant.ApiKey.REGION) String region,
                                              @Path(Constant.ApiKey.SUMMONER_ID) String summonerID);

    @GET(Constant.Api.GET_SUMMONER_MATCHE_BY_ID)
    Call<ResponseBody> getSumonnerMatcheByID(@Path(Constant.ApiKey.REGION) String region,
                                             @Path(Constant.ApiKey.MATCH_ID) String matchID);


    @GET(Constant.Api.GET_SUMMONER_CHAMPIONS_STATS_BY_ID)
    Call<ResponseBody> getSumonnerChampionStatsByID(@Path(Constant.ApiKey.REGION) String region,
                                             @Path(Constant.ApiKey.SUMMONER_ID) String sumonerID);
}
