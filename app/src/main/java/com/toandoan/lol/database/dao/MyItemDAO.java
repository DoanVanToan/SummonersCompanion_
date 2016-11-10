package com.toandoan.lol.database.dao;

import com.toandoan.lol.model.item.ItemEnity;

import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public interface MyItemDAO {
    public static final String TABLE_ITEM = "tbl_item";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_GOLD_TOTAL = "gold_total";
    public static final String FIELD_GOLD_PURCHASABLE = "gold_purchasable";
    public static final String FIELD_GOLD_SELL = "gold_sell";
    public static final String FIELD_GOLD_BASE = "gold_base";
    public static final String FIELD_SANITIZEDDESCRIPTION = "sanitizedDescription";
    public static final String FIELD_FROM = "from_";
    public static final String FIELD_INTO = "into_";
    public static final String FIELD_TAGS = "tags";
    public static final String FIELD_PLAINTEXT = "plaintext";
    public static final String FIELD_IMAGE = "image";

    public static final String COMMAND_GET_ALL_ROWS = "SELECT * FROM " + TABLE_ITEM + " ORDER BY " + FIELD_ID + " ASC";
    public static final String COMMAND_GET_ITEM_BY_ID = "SELECT * FROM " + TABLE_ITEM + " WHERE " + FIELD_ID + " = ?";
    public static final String COMMAND_DELETE_TABLE = "DELETE FROM " + TABLE_ITEM;

    public List<ItemEnity> getAllItem();

    public ItemEnity getItemById(int id);

    public boolean insertItem(ItemEnity itemEnity);

    boolean deleteAllItemTable();

    void insertItem(List<ItemEnity> list);
}
