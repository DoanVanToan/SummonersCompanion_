package com.toandoan.lol.database.dao;

import com.toandoan.lol.model.matery.MasteryEnity;

import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public interface MasteryDAO {
    String TABLE_MASTERIES = "tbl_masteries";
    String FIELD_ID = "id";
    String FIELD_RANKS = "ranks";
    String FIELD_NAME = "name";
    String FIELD_IMAGE = "image";
    String FIELD_PREREQ = "prereq";
    String FIELD_MASTERYTREE = "masteryTree";
    String FIELD_SANITIZEDDESCRIPTION = "sanitizedDescription";
    String FIELD_DESCRIPTION = "description";

    String TYPE_FEROCITY = "Ferocity";
    String TYPE_CUNNING = "Cunning";
    String TYPE_RESOLVE = "Resolve";

    String COMMAND_GET_ALL_ROWS = "SELECT * FROM " + TABLE_MASTERIES;
    String COMMAND_DELETE_TABLE = "DELETE FROM " + TABLE_MASTERIES;
    String COMMAND_GET_ITEM_BY_MASTERYTREE = "SELECT * FROM " + TABLE_MASTERIES + " WHERE " + FIELD_MASTERYTREE + " = ?";
    String COMMAND_GET_ITEM_BY_ID = "SELECT * FROM " + TABLE_MASTERIES + " WHERE " + FIELD_ID + " = ";
    List<MasteryEnity> getAllMasteries();

    List<MasteryEnity> getMasteriesByType(String type);

    boolean insertMastery(MasteryEnity masteryEnity);

    boolean deleteAllMasteriesTable();

    void insertListMasteries(List<MasteryEnity> list);
}
