package com.toandoan.lol.database.dao;

import com.toandoan.lol.model.rune.RuneEnity;

import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public interface RunesDAO {
    public static final String TABLE_RUNE = "tbl_rune";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_ISRUNE = "isRune";
    public static final String FIELD_TIER = "tier";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_IMAGE = "image";


    public static final String COMMAND_GET_ALL_ROWS = "SELECT * FROM " + TABLE_RUNE + " ORDER BY " + FIELD_TIER + " DESC";
    public static final String COMMAND_DELETE_TABLE = "DELETE FROM " + TABLE_RUNE;

    public List<RuneEnity> getAllRunes();

    public boolean insertRunes(RuneEnity itemEnity);

    boolean deleteAllRuneTable();

    void insertListRunes(List<RuneEnity> list);
}
