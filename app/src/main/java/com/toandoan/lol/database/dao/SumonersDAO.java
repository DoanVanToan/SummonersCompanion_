package com.toandoan.lol.database.dao;

import com.toandoan.lol.model.SumonerEnity;
import com.toandoan.lol.model.rune.RuneEnity;

import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public interface SumonersDAO {
    String TABLE_SUMONER = "tbl_sumoner";
    String FIELD_ID = "id";
    String FIELD_NAME = "name";
    String FIELD_REVISION_DATE = "revisionDate";
    String FIELD_PROFILE_ICON_ID = "profileIconId";
    String FIELD_SUMMONER_LEVEL = "summonerLevel";
    String FIELD_ROW_ID = "row_id";
    String FIELD_REGION = "region";


    String COMMAND_GET_ALL_ROWS = "SELECT * FROM " + TABLE_SUMONER + " ORDER BY " + FIELD_ROW_ID + " DESC";
    String COMMAND_DELETE_TABLE = "DELETE FROM " + TABLE_SUMONER;
    String COMMAND_DELETE_SUMONER = "DELETE FROM " + TABLE_SUMONER + " WHERE " + FIELD_ID + " =";
    String COMMAND_GET_SUMONER_BY_ID = "SELECT * FROM " + TABLE_SUMONER + " ORDER BY " + FIELD_ID + " =";

    List<SumonerEnity> getAllSummoners();

    boolean insertSumoner(SumonerEnity sumoner);

    boolean deleteAllTable();

    boolean deleteSumonerByID(int id);

    SumonerEnity getSumonerByID(int id);
}
