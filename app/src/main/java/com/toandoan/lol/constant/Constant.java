package com.toandoan.lol.constant;

/**
 * Created by ToanDoan on 9/29/2016.
 */
public class Constant {

    public static class Charactor {
        public static final String PLUS = "+";
        public static final String SPACE = " ";
        public static final String PERCENT = "%";
        public static final String LINE_BREAK = "\n";
        public static final String DIV = "/";
        public static final String MOD = ":";
        public static final String LEVEL = "Level";
        public static final String W = "W";
    }

    public static class SharePreference {
        public static final String PRE_REGION = "pre_region";
    }

    public static class SumonerStaticData {
        public static final String RANKED_SOLO_5X5 = "RANKED_SOLO_5x5";
        public static final String RANKED_FLEX_SR = "RANKED_FLEX_SR";
        public static final String RANKED_TEAM_3X3 = "RANKED_TEAM_3x3";
        public static final String RANKED_TEAM_5X5 = "RANKED_TEAM_5x5";
        public static final String CHALLENGE_RANK = "challenge_rank";
        public static final String POINT = "LP";
    }

    public static class SumonerSumary {
        public static final String RANKEDSOLO5X5 = "RankedSolo5x5";
    }

    public static class Config {
        public static final String URL = "api.pvp.net";
        public static final boolean DEBUG = true;

        public static final String getBaseUrl(String region) {
            return "https://" + region + "." + URL;
        }

        public static final String getStaticBaseUrl() {
            return "https://global.api.pvp.net";
        }
    }

    public static class Region {
        public static final String BRAZIL = "br";
        public static final String EU_NORDIC = "eune";
        public static final String EU_WEST = "euw";
        public static final String JAPAN = "jp";
        public static final String KOREAN = "kr";
        public static final String LATIN_AMERICA_NORTH = "lan";
        public static final String LATIN_AMERICA_SOUTH = "las";
        public static final String NORTH_AMERICA = "na";
        public static final String OCEANIA = "oce";
        public static final String RUSSIA = "ru";
        public static final String TURKEY = "tr";
    }

    public static class IntentKey {
        public static final String CHAMPION_ENITY = "champion_enity";
        public static final String URL_VIDEO = "url_video";
        public static final String URL_THUMB_VIDEO = "url_thumb_video";
        public static final String TITLE = "title";
        public static final String SUMONER_ID = "id";
        public static final String SUMONER = "sumoner";
        public static final String PARTICIPANT_STATS = "participant_stats";
        public static final String PARTICIPANT = "participant";
        public static final String MATCHDETAIL = "matchdetail";
        public static final String GAME_ENITY = "game_enity";
        public static final String RANK_TYPE = "rank_type";
    }

    public static class Api {
        public static final String FIND_BY_NAME =
            "/api/lol/{region}/v1.4/summoner/by-name/{summonerNames}";
        public static final String GET_LIST_CHAMPIONS =
            "/api/lol/static-data/{region}/v1.2/champion";
        public static final String GET_LIST_ITEMS =
            "/api/lol/static-data/{region}/v1.2/item";
        public static final String GET_LIST_RUNES =
            "/api/lol/static-data/{region}/v1.2/rune";
        public static final String GET_LIST_SPELLS =
            "/api/lol/static-data/{region}/v1.2/summoner-spell";
        public static final String GET_LIST_MASTERIES =
            "/api/lol/static-data/{region}/v1.2/mastery";
        public static final String GET_CHAMPION_BY_ID =
            "/api/lol/static-data/{region}/v1.2/champion/{id}";
        public static final String GET_SUMMONER_MASTERIES =
            "/api/lol/{region}/v1.4/summoner/{summonerIds}/masteries";
        public static final String GET_SUMMONER_RUNES =
            " /api/lol/{region}/v1.4/summoner/{summonerIds}/runes";
        public static final String GET_SUMMONER_MATCHES_LIST =
            "/api/lol/{region}/v2.2/matchlist/by-summoner/{summonerId}";
        public static final String GET_SUMMONER_MATCHE_BY_ID =
            "/api/lol/{region}/v2.2/match/{matchId}";
        public static final String GET_SUMMONER_CHAMPIONS_STATS_BY_ID =
            "/api/lol/{region}/v1.3/stats/by-summoner/{summonerId}/ranked";
        public static final String GET_SUMMONER_RECENT_MATCHES =
            "api/lol/{region}/v1.3/game/by-summoner/{summonerId}/recent";
        public static final String GET_SUMMONER_RANK_STATS =
            "/api/lol/{region}/v2.5/league/by-summoner/{summonerIds}/entry";
        public static final String GET_SUMMONER_RANK_SUMARY =
            "/api/lol/{region}/v1.3/stats/by-summoner/{summonerId}/summary";
        public static final String GET_SUMMONER_BY_ID =
            "/api/lol/{region}/v1.4/summoner/{summonerIds}";
        public static final String GET_CHALLANGE_RANK =
            "api/lol/{region}/v2.5/league/challenger";
        public static final String GET_SUMONER_RANK =
            "/api/lol/{region}/v2.5/league/by-summoner/{summonerIds}";
    }

    public static class ApiKeyValue {
        public static final String ALL = "all";
        public static final String SPELL_DATA_IMAGE = "image";
        public static final String SEASON_VALUE = "SEASON2016";
        public static final String RANK_TYPE_5_5 = "RANKED_SOLO_5x5";
        public static final String API_KEY_VALUE = "RGAPI-950d5f5c-cbae-4b0e-8bea-89daff98c0bf";
    }

    public static class ApiKey {
        public static final String API_KEY = "api_key";
        public static final String REGION = "region";
        public static final String SUMMONER_IDS = "summonerIds";
        public static final String SUMMONER_ID = "summonerId";
        public static final String MATCH_ID = "matchId";
        public static final String SUMMONER_NAMES = "summonerNames";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String PROFILE_ICON_ID = "profileIconId";
        public static final String REVISION_DATE = "revisionDate";
        public static final String SUMMONER_LEVEL = "summonerLevel";
        public static final String ITEM_LIST_DATA = "itemListData";
        public static final String CHAMP_DATA = "champData";
        public static final String RUNE_LIST_DATA = "runeListData";
        public static final String SPELL_DATA = "spellData";
        public static final String MASTERY_LIST_DATA = "masteryListData";
        public static final String SEASON = "season";
        public static final String RANK_TYPE = "type";
        /**
         * ChampionStats
         **/
        public static final String HP = "hp";
        public static final String HPPERLEVEL = "hp";
        public static final String MP = "hp";
        public static final String MPPERLEVEL = "hp";
        public static final String MOVESPEED = "hp";
        public static final String ARMOR = "hp";
        public static final String ARMORPERLEVEL = "hp";
        public static final String SPELLBLOCK = "hp";
        public static final String SPELLBLOCKPERLEVEL = "hp";
        public static final String ATTACKRANGE = "hp";
        public static final String HPREGEN = "hp";
        public static final String HPREGENPERLEVEL = "hp";
        public static final String MPREGEN = "hp";
        public static final String MPREGENPERLEVEL = "hp";
        public static final String CRIT = "hp";
        public static final String CRITPERLEVEL = "hp";
        public static final String ATTACKDAMAGE = "hp";
        public static final String ATTACKDAMAGEPERLEVEL = "hp";
        public static final String ATTACKSPEEDOFFSET = "hp";
        public static final String ATTACKSPEEDPERLEVEL = "hp";
        public static final String DATA = "data";
        public static final String PAGES = "pages";
        public static final String SLOTS = "slots";
    }

    public static class Data {
        public static final String FULL_CHAMP_LIST = "fullChamp";
        public static final String CHAMPION_ = "champion_";
    }
}
