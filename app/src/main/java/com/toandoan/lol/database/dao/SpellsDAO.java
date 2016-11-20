package com.toandoan.lol.database.dao;

import com.toandoan.lol.model.SpellEnity;

import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public interface SpellsDAO {
    String TABLE_SPELLS = "tbl_spell";
    String FIELD_ID = "id";
    String FIELD_NAME = "name";
    String FIELD_DESCRIPTION = "description";
    String FIELD_SUMMONER_LEVEL = "summonerLevel";
    String FIELD_IMAGE = "image";


    String COMMAND_GET_ALL_ROWS = "SELECT * FROM " + TABLE_SPELLS;
    String COMMAND_DELETE_TABLE = "DELETE FROM " + TABLE_SPELLS;
    String COMMAND_GET_SPELL_BY_ID = "SELECT * FROM " + TABLE_SPELLS + " ORDER BY " + FIELD_ID + " =";

    List<SpellEnity> getAllSpells();

    boolean insertSpells(SpellEnity spellEnity);

    boolean deleteAllRuneTable();

    void insertListSpells(List<SpellEnity> list);

    SpellEnity getSpellByID(int id);
}
