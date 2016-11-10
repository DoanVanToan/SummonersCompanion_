package com.toandoan.lol.database.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.toandoan.lol.database.base.DatabaseAbstract;
import com.toandoan.lol.database.dao.MasteryDAO;
import com.toandoan.lol.model.matery.MasteryEnity;
import com.toandoan.lol.utility.LogUtil;
import com.toandoan.lol.utility.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ToanDoan on 10/30/2016.
 */

public class MasteriesImpl extends DatabaseAbstract implements MasteryDAO {
    private static final String TAG = "MasteriesImpl";

    public MasteriesImpl(Context context) {
        super(context);
    }

    @Override
    public List<MasteryEnity> getAllMasteries() {
        List<MasteryEnity> result = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(COMMAND_GET_ALL_ROWS, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                MasteryEnity masteryEnity = new MasteryEnity(cursor);
                result.add(masteryEnity);
            } while ((cursor.moveToNext()));
            cursor.close();
        }
        return result;
    }

    public MasteryEnity getMasteryByID(String id) {
        MasteryEnity masteryEnity = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(COMMAND_GET_ITEM_BY_ID + id, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                masteryEnity = new MasteryEnity(cursor);
            } while ((cursor.moveToNext()));
            cursor.close();
        }
        return masteryEnity;
    }

    @Override
    public List<MasteryEnity> getMasteriesByType(String type) {
        List<MasteryEnity> result = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(COMMAND_GET_ITEM_BY_MASTERYTREE, new String[]{type});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                MasteryEnity masteryEnity = new MasteryEnity(cursor);
                result.add(masteryEnity);
            } while ((cursor.moveToNext()));
            cursor.close();
        }
        return result;
    }

    @Override
    public boolean insertMastery(MasteryEnity masteryEnity) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FIELD_ID, masteryEnity.getId());
        contentValues.put(FIELD_RANKS, masteryEnity.getRanks());
        contentValues.put(FIELD_NAME, masteryEnity.getName());
        contentValues.put(FIELD_IMAGE, masteryEnity.getImage().getFull());

        contentValues.put(FIELD_PREREQ, masteryEnity.getPrereq());
        contentValues.put(FIELD_MASTERYTREE, masteryEnity.getMasteryTree());
        contentValues.put(FIELD_SANITIZEDDESCRIPTION, Utils.getStringFromList(masteryEnity.getSanitizedDescription()));
        contentValues.put(FIELD_DESCRIPTION, Utils.getStringFromList(masteryEnity.getDescription()));

        long rowID = sqLiteDatabase.insert(TABLE_MASTERIES, null, contentValues);
        if (rowID > -1) {
            LogUtil.e(TAG, "insertMastery " + masteryEnity.getId() + "true");
            return true;
        }
        LogUtil.e(TAG, "insertMastery " + masteryEnity.getId() + "false");
        return false;
    }

    @Override
    public boolean deleteAllMasteriesTable() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(COMMAND_DELETE_TABLE);
        return false;
    }

    @Override
    public void insertListMasteries(List<MasteryEnity> list) {
        if (list != null && list.size() > 0) {
            deleteAllMasteriesTable();
            for (int i = 0; i < list.size(); i++) {
                insertMastery(list.get(i));
            }
        }
    }
}
