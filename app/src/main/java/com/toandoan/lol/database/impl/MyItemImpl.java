package com.toandoan.lol.database.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.toandoan.lol.database.base.DatabaseAbstract;
import com.toandoan.lol.database.dao.MyItemDAO;
import com.toandoan.lol.model.item.ItemEnity;
import com.toandoan.lol.utility.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public class MyItemImpl extends DatabaseAbstract implements MyItemDAO {
    private final String TAG = "MyItemImpl";

    public MyItemImpl(Context context) {
        super(context);
    }

    @Override
    public List<ItemEnity> getAllItem() {
        List<ItemEnity> result = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(COMMAND_GET_ALL_ROWS, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                ItemEnity itemEntity = new ItemEnity(cursor);
                result.add(itemEntity);
            } while ((cursor.moveToNext()));
            cursor.close();
        }
        return result;
    }

    @Override
    public ItemEnity getItemById(int id) {
        ItemEnity itemEnity = null;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(COMMAND_GET_ITEM_BY_ID, new String[]{String.valueOf(id)});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                itemEnity = new ItemEnity(cursor);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return itemEnity;
    }

    @Override
    public boolean insertItem(ItemEnity itemEnity) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FIELD_ID, itemEnity.getId());
        contentValues.put(FIELD_NAME, itemEnity.getName());
        contentValues.put(FIELD_DESCRIPTION, itemEnity.getDescription());
        contentValues.put(FIELD_GOLD_TOTAL, itemEnity.getGold().getTotal());
        contentValues.put(FIELD_GOLD_PURCHASABLE, itemEnity.getGold().isPurchasable() ? 1 : 0);
        contentValues.put(FIELD_GOLD_SELL, itemEnity.getGold().getSell());
        contentValues.put(FIELD_GOLD_BASE, itemEnity.getGold().getBase());
        contentValues.put(FIELD_SANITIZEDDESCRIPTION, itemEnity.getSanitizedDescription());

        if (itemEnity.getFrom() != null) {
            String from = "";
            for (int i = 0; i < itemEnity.getFrom().size(); i++) {
                from += itemEnity.getFrom().get(i) + ";";
            }
            // Remove the last character ";""
            from = from.substring(0, from.length() - 1);
            contentValues.put(FIELD_FROM, from);
        }

        if (itemEnity.getInto() != null) {
            String into = "";
            for (int i = 0; i < itemEnity.getInto().size(); i++) {
                into += itemEnity.getInto().get(i) + ";";
            }
            // Remove the last character ";""
            into = into.substring(0, into.length() - 1);
            contentValues.put(FIELD_INTO, into);
        }

        if (itemEnity.getTags() != null) {
            String tags = "";
            for (int i = 0; i < itemEnity.getTags().size(); i++) {
                tags += itemEnity.getTags().get(i) + ";";
            }
            // Remove the last character ";""
            tags = tags.substring(0, tags.length() - 1);
            contentValues.put(FIELD_TAGS, tags);
        }

        contentValues.put(FIELD_PLAINTEXT, itemEnity.getPlaintext());
        contentValues.put(FIELD_IMAGE, itemEnity.getImage().getFull());

        long rowID = sqLiteDatabase.insert(TABLE_ITEM, null, contentValues);
        if (rowID > -1) {
            LogUtil.e(TAG, "insertItem " + itemEnity + "true");
            return true;
        }
        LogUtil.e(TAG, "insertItem " + itemEnity + "false");
        return false;
    }

    @Override
    public boolean deleteAllItemTable() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(COMMAND_DELETE_TABLE);
        return false;
    }

    @Override
    public void insertItem(List<ItemEnity> list) {
        if (list != null && list.size() > 0) {
            deleteAllItemTable();
            for (int i = 0; i < list.size(); i++) {
                insertItem(list.get(i));
            }
        }
    }
}
