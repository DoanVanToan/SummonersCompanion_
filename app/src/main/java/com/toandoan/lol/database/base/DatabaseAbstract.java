package com.toandoan.lol.database.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public abstract class DatabaseAbstract extends SQLiteAssetHelper{
    public static final String DATABASE_NAME = "data.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseAbstract(Context context, String name, String storageDirectory, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, storageDirectory, factory, version);
    }

    public DatabaseAbstract(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseAbstract(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
