package com.toandoan.lol.database.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;

import com.toandoan.lol.database.base.DatabaseAbstract;
import com.toandoan.lol.database.dao.RunesDAO;
import com.toandoan.lol.model.item.ItemEnity;
import com.toandoan.lol.model.rune.RuneEnity;
import com.toandoan.lol.utility.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public class RunesImpl extends DatabaseAbstract implements RunesDAO {
    private final String TAG = "RunesImpl";

    public RunesImpl(Context context) {
        super(context);
    }

    @Override
    public List<RuneEnity> getAllRunes() {
        List<RuneEnity> result = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(COMMAND_GET_ALL_ROWS, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                RuneEnity runeEnity = new RuneEnity(cursor);
                result.add(runeEnity);
            } while ((cursor.moveToNext()));
            cursor.close();
        }
        return result;
    }

    @Override
    public boolean insertRunes(RuneEnity runeEnity) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FIELD_ID, runeEnity.getId());
        contentValues.put(FIELD_DESCRIPTION, runeEnity.getDescription());
        contentValues.put(FIELD_ISRUNE, runeEnity.getRune().isRune() ? 1 : 0);
        contentValues.put(FIELD_TIER, runeEnity.getRune().getTier());
        contentValues.put(FIELD_TYPE, runeEnity.getRune().getType());
        contentValues.put(FIELD_NAME, runeEnity.getName());
        contentValues.put(FIELD_IMAGE, runeEnity.getImage().getFull());

        long rowID = sqLiteDatabase.insert(TABLE_RUNE, null, contentValues);
        if (rowID > -1) {
            LogUtil.e(TAG, "insertRunes " + runeEnity + "true");
            return true;
        }
        LogUtil.e(TAG, "insertRunes " + runeEnity + "false");
        return false;

    }

    @Override
    public boolean deleteAllRuneTable() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(COMMAND_DELETE_TABLE);
        return false;
    }

    @Override
    public void insertListRunes(List<RuneEnity> list) {
        if (list != null && list.size() > 0) {
            deleteAllRuneTable();
            for (int i = 0; i < list.size(); i++) {
                insertRunes(list.get(i));
            }
        }
    }
}
