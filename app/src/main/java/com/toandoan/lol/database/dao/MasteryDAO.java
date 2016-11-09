package com.toandoan.lol.database.dao;

import com.toandoan.lol.model.MasteryEnity;

import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public interface MasteryDAO {
    public static final String TABLE_MASTERIES = "tbl_masteries";
    public static final String FIELD_ID = "id";
    public static final String FIELD_RANKS = "ranks";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_IMAGE = "image";
    public static final String FIELD_PREREQ = "prereq";
    public static final String FIELD_MASTERYTREE = "masteryTree";
    public static final String FIELD_SANITIZEDDESCRIPTION = "sanitizedDescription";
    public static final String FIELD_DESCRIPTION = "description";

    public static final String TYPE_FEROCITY = "Ferocity";
    public static final String TYPE_CUNNING ="Cunning";
    public static final String TYPE_RESOLVE= "Resolve";
    
    public static final String COMMAND_GET_ALL_ROWS = "SELECT * FROM " + TABLE_MASTERIES;
    public static final String COMMAND_DELETE_TABLE = "DELETE FROM " + TABLE_MASTERIES;
    public static final String COMMAND_GET_ITEM_BY_MASTERYTREE = "SELECT * FROM " + TABLE_MASTERIES + " WHERE " + FIELD_MASTERYTREE + " = ?";

    public List<MasteryEnity> getAllMasteries();

    public List<MasteryEnity> getMasteriesByType(String type);

    public boolean insertMastery(MasteryEnity masteryEnity);

    boolean deleteAllMasteriesTable();

    void insertListMasteries(List<MasteryEnity> list);
}
