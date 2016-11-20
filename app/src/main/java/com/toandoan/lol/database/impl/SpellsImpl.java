package com.toandoan.lol.database.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.toandoan.lol.database.base.DatabaseAbstract;
import com.toandoan.lol.database.dao.RunesDAO;
import com.toandoan.lol.database.dao.SpellsDAO;
import com.toandoan.lol.model.SpellEnity;
import com.toandoan.lol.model.rune.RuneEnity;
import com.toandoan.lol.utility.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public class SpellsImpl extends DatabaseAbstract implements SpellsDAO {
    private final String TAG = "SpellsImpl";

    public SpellsImpl(Context context) {
        super(context);
    }


    @Override
    public List<SpellEnity> getAllSpells() {
        List<SpellEnity> result = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(COMMAND_GET_ALL_ROWS, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                SpellEnity spellEnity = new SpellEnity(cursor);
                result.add(spellEnity);
            } while ((cursor.moveToNext()));
            cursor.close();
        }
        return result;
    }

    @Override
    public boolean insertSpells(SpellEnity spellEnity) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FIELD_ID, spellEnity.getId());
        contentValues.put(FIELD_DESCRIPTION, spellEnity.getDescription());
        contentValues.put(FIELD_SUMMONER_LEVEL, spellEnity.getSummonerLevel());
        contentValues.put(FIELD_NAME, spellEnity.getName());
        contentValues.put(FIELD_IMAGE, spellEnity.getImage().getFull());

        long rowID = sqLiteDatabase.insert(TABLE_SPELLS, null, contentValues);
        if (rowID > -1) {
            LogUtil.e(TAG, "insertRunes " + spellEnity + "true");
            return true;
        }
        LogUtil.e(TAG, "insertRunes " + spellEnity + "false");
        return false;
    }

    @Override
    public boolean deleteAllRuneTable() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(COMMAND_DELETE_TABLE);
        return false;
    }

    @Override
    public void insertListSpells(List<SpellEnity> list) {
        if (list != null && list.size() > 0) {
            deleteAllRuneTable();
            for (int i = 0; i < list.size(); i++) {
                insertSpells(list.get(i));
            }
        }
    }

    @Override
    public SpellEnity getSpellByID(int id) {
        SpellEnity result = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(COMMAND_GET_SPELL_BY_ID + id, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                result = new SpellEnity(cursor);
            } while ((cursor.moveToNext()));
            cursor.close();
        }

        return result;
    }

}
