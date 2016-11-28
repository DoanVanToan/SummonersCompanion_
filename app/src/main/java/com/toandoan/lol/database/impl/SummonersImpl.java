package com.toandoan.lol.database.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.toandoan.lol.database.base.DatabaseAbstract;
import com.toandoan.lol.database.dao.RunesDAO;
import com.toandoan.lol.database.dao.SumonersDAO;
import com.toandoan.lol.model.SumonerEnity;
import com.toandoan.lol.model.rune.RuneEnity;
import com.toandoan.lol.utility.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public class SummonersImpl extends DatabaseAbstract implements SumonersDAO {
    private final String TAG = "SummonersImpl";

    public SummonersImpl(Context context) {
        super(context);
    }

    @Override
    public List<SumonerEnity> getAllSummoners() {
        List<SumonerEnity> result = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(COMMAND_GET_ALL_ROWS, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                SumonerEnity sumonerEnity = new SumonerEnity(cursor);
                result.add(sumonerEnity);
            } while ((cursor.moveToNext()));
            cursor.close();
        }
        return result;
    }

    @Override
    public boolean insertSumoner(SumonerEnity sumoner) {
        if (getSumonerByID(sumoner.getId()) != null) {
            deleteSumonerByID(sumoner.getId());
        }

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FIELD_ID, sumoner.getId());
        contentValues.put(FIELD_NAME, sumoner.getName());
        contentValues.put(FIELD_PROFILE_ICON_ID, sumoner.getProfileIconId());
        contentValues.put(FIELD_REVISION_DATE, sumoner.getRevisionDate());
        contentValues.put(FIELD_SUMMONER_LEVEL, sumoner.getSummonerLevel());


        long rowID = sqLiteDatabase.insert(TABLE_SUMONER, null, contentValues);
        if (rowID > -1) {
            LogUtil.e(TAG, "insertSumoners " + sumoner + "true");
            return true;
        }
        LogUtil.e(TAG, "insertSumoners " + sumoner + "false");
        return false;

    }

    @Override
    public boolean deleteAllTable() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(COMMAND_DELETE_TABLE);
        return false;
    }

    @Override
    public boolean deleteSumonerByID(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(COMMAND_DELETE_SUMONER + id);
        return true;
    }

    @Override
    public SumonerEnity getSumonerByID(int id) {
        SumonerEnity result = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(COMMAND_GET_SUMONER_BY_ID + id, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                result = new SumonerEnity(cursor);
            } while ((cursor.moveToNext()));
            cursor.close();
        }

        return result;
    }
}
