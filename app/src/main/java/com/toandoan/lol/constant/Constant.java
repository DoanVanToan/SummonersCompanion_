package com.toandoan.lol.constant;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public class Constant {
    public static class Config {
        public static final String REGION = Region.NORTH_AMERICA;

        public static final String URL = "api.pvp.net";

        public static final boolean DEBUG = true;

        public static final String getBaseUrl() {
            return "https://" + REGION + "." + URL;
        }

        public static final String getStaticBaseUrl() {
            return "https://global.api.pvp.net";
        }

        public static final String getREGION() {
            return Config.REGION;
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
    }


    public static class Api {

        public static final String FIND_BY_NAME = "/api/lol/{" + ApiKey.REGION + "}/v1.4/summoner/by-name/{"
                + ApiKey.SUMMONER_NAMES + "}" + ApiKey.API_KEY_URL;


        public static final String GET_LIST_CHAMPIONS = "/api/lol/static-data/{region}/v1.2/champion" + ApiKey.API_KEY_URL;

        public static final String GET_LIST_ITEMS = "/api/lol/static-data/{region}/v1.2/item?itemListData=all&api_key=" + ApiKey.API_KEY_VALUES;

        public static final String GET_LIST_RUNES = "/api/lol/static-data/{region}/v1.2/rune?runeListData=all&api_key=" + ApiKey.API_KEY_VALUES;

        public static final String GET_LIST_MASTERIES = "/api/lol/static-data/{region}/v1.2/mastery?masteryListData=all&api_key=" + ApiKey.API_KEY_VALUES;

        public static final String GET_CHAMPION_BY_ID = "/api/lol/static-data/{region}/v1.2/champion/{id}?champData=all&api_key=" + ApiKey.API_KEY_VALUES;

        public static final String GET_SUMMONER_MASTERIES = "/api/lol/{region}/v1.4/summoner/{summonerIds}/masteries"+ ApiKey.API_KEY_URL;
    }

    public static class ApiKey {
        public static final String API_KEY = "api_key";
        public static final String API_KEY_VALUES = "RGAPI-950d5f5c-cbae-4b0e-8bea-89daff98c0bf";
        public static final String REGION = "region";
        public static final String SUMMONER_ID = "summonerIds";
        public static final String SUMMONER_NAMES = "summonerNames";
        public static final String API_KEY_URL = "?" + API_KEY + "=" + API_KEY_VALUES;
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String PROFILE_ICON_ID = "profileIconId";
        public static final String REVISION_DATE = "revisionDate";
        public static final String SUMMONER_LEVEL = "summonerLevel";
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
    }

    public static class Data {
        public static final String FULL_CHAMP_LIST = "fullChamp";
        public static final String CHAMPION_ = "champion_";
    }
}
