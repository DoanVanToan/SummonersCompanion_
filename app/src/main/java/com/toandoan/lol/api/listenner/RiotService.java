package com.toandoan.lol.api.listenner;

import com.toandoan.lol.constant.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ToanDoan on 9/29/2016.
 */
public interface RiotService {
    @GET(Constant.Api.FIND_BY_NAME)
    Call<ResponseBody> getSumonerByName(@Path(Constant.ApiKey.REGION) String region,
                                        @Path(Constant.ApiKey.SUMMONER_NAMES) String userName,
                                        @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_LIST_CHAMPIONS)
    Call<ResponseBody> getAllChampions(@Path(Constant.ApiKey.REGION) String region,
                                       @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_LIST_ITEMS)
    Call<ResponseBody> getAllItems(@Path(Constant.ApiKey.REGION) String region,
                                   @Query(Constant.ApiKey.ITEM_LIST_DATA) String itemListData,
                                   @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_CHAMPION_BY_ID)
    Call<ResponseBody> getChampionByID(@Path(Constant.ApiKey.REGION) String region,
                                       @Path(Constant.ApiKey.ID) String id,
                                       @Query(Constant.ApiKey.CHAMP_DATA) String champData,
                                       @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_LIST_RUNES)
    Call<ResponseBody> getAllRunes(@Path(Constant.ApiKey.REGION) String region,
                                   @Query(Constant.ApiKey.RUNE_LIST_DATA) String runeListData,
                                   @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_LIST_SPELLS)
    Call<ResponseBody> getAllSpells(@Path(Constant.ApiKey.REGION) String region,
                                    @Query(Constant.ApiKey.SPELL_DATA) String spellData,
                                    @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_LIST_MASTERIES)
    Call<ResponseBody> getAllMasteries(@Path(Constant.ApiKey.REGION) String region,
                                       @Query(Constant.ApiKey.MASTERY_LIST_DATA) String
                                           masteryListData,
                                       @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_SUMMONER_MASTERIES)
    Call<ResponseBody> getSumonnerMasteries(@Path(Constant.ApiKey.REGION) String region,
                                            @Path(Constant.ApiKey.SUMMONER_IDS) String summonerID,
                                            @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_SUMMONER_RUNES)
    Call<ResponseBody> getSumonnerRunes(@Path(Constant.ApiKey.REGION) String region,
                                        @Path(Constant.ApiKey.SUMMONER_IDS) String summonerID,
                                        @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_SUMMONER_MATCHES_LIST)
    Call<ResponseBody> getSumonnerMatchesList(@Path(Constant.ApiKey.REGION) String region,
                                              @Path(Constant.ApiKey.SUMMONER_ID) String
                                                  summonerID,
                                              @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_SUMMONER_MATCHE_BY_ID)
    Call<ResponseBody> getSumonnerMatcheByID(@Path(Constant.ApiKey.REGION) String region,
                                             @Path(Constant.ApiKey.MATCH_ID) String matchID,
                                             @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_SUMMONER_CHAMPIONS_STATS_BY_ID)
    Call<ResponseBody> getSumonnerChampionStatsByID(@Path(Constant.ApiKey.REGION) String region,
                                                    @Path(Constant.ApiKey.SUMMONER_ID)
                                                        String sumonerID,
                                                    @Query(Constant.ApiKey.SEASON) String season,
                                                    @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_SUMMONER_RECENT_MATCHES)
    Call<ResponseBody> getSumonnerRecentMatches(@Path(Constant.ApiKey.REGION) String region,
                                                @Path(Constant.ApiKey.SUMMONER_ID)
                                                    String sumonerID,
                                                @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_SUMMONER_RANK_STATS)
    Call<ResponseBody> getSumonnerRankStats(@Path(Constant.ApiKey.REGION) String region,
                                            @Path(Constant.ApiKey.SUMMONER_IDS) String sumonerID,
                                            @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_SUMMONER_RANK_SUMARY)
    Call<ResponseBody> getSumonnerRankSumary(@Path(Constant.ApiKey.REGION) String region,
                                             @Path(Constant.ApiKey.SUMMONER_ID) String sumonerID,
                                             @Query(Constant.ApiKey.SEASON) String season,
                                             @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_SUMMONER_BY_ID)
    Call<ResponseBody> getSumonnerByID(@Path(Constant.ApiKey.REGION) String region,
                                       @Path(Constant.ApiKey.SUMMONER_IDS) String sumonerID,
                                       @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_CHALLANGE_RANK)
    Call<ResponseBody> getRankChallange(@Path(Constant.ApiKey.REGION) String region,
                                        @Query(Constant.ApiKey.RANK_TYPE) String type,
                                        @Query(Constant.ApiKey.API_KEY) String apiKey);
    @GET(Constant.Api.GET_SUMONER_RANK)
    Call<ResponseBody> getRankBySumoner(@Path(Constant.ApiKey.REGION) String region,
                                        @Path(Constant.ApiKey.SUMMONER_IDS) String sumonerID,
                                        @Query(Constant.ApiKey.API_KEY) String apiKey);
}
