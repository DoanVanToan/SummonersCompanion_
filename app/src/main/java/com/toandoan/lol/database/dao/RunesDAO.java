package com.toandoan.lol.database.dao;

import com.toandoan.lol.model.rune.RuneEnity;

import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public interface RunesDAO {
    String TABLE_RUNE = "tbl_rune";
    String FIELD_ID = "id";
    String FIELD_NAME = "name";
    String FIELD_DESCRIPTION = "description";
    String FIELD_ISRUNE = "isRune";
    String FIELD_TIER = "tier";
    String FIELD_TYPE = "type";
    String FIELD_IMAGE = "image";


    String COMMAND_GET_ALL_ROWS = "SELECT * FROM " + TABLE_RUNE + " ORDER BY " + FIELD_TIER + " DESC";
    String COMMAND_DELETE_TABLE = "DELETE FROM " + TABLE_RUNE;
    String COMMAND_GET_RUNE_BY_ID = "SELECT * FROM " + TABLE_RUNE + " ORDER BY " + FIELD_ID + " =";

    List<RuneEnity> getAllRunes();

    boolean insertRunes(RuneEnity itemEnity);

    boolean deleteAllRuneTable();

    void insertListRunes(List<RuneEnity> list);

    RuneEnity getRuneByID(int id);
}
