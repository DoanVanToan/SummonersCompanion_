package com.toandoan.lol.model.item;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.toandoan.lol.database.dao.MyItemDAO;
import com.toandoan.lol.model.ImageEnity;
import com.toandoan.lol.utility.Utils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public class ItemEnity {
    @SerializedName("id")
    private int id;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("consumeOnFull")
    private boolean consumeOnFull;
    @SerializedName("consumed")
    private boolean consumed;
    @SerializedName("depth")
    private int depth;
    @SerializedName("description")
    private String description;
    @SerializedName("effect")
    private HashMap<String, String> effect;
    @SerializedName("gold")
    private GoldEnity gold;
    @SerializedName("group")
    private String group;
    @SerializedName("hideFromAll")
    private boolean hideFromAll;
    @SerializedName("masteries_image")
    private ImageEnity image;
    @SerializedName("inStore")
    private boolean inStore;
    @SerializedName("into")
    private List<String> into;
    @SerializedName("from")
    private List<String> from;
    @SerializedName("maps")
    private HashMap<String, Boolean> maps;
    @SerializedName("name")
    private String name;
    @SerializedName("plaintext")
    private String plaintext;
    @SerializedName("requiredChampion")
    private String requiredChampion;
    @SerializedName("rune")
    private String rune;
    @SerializedName("sanitizedDescription")
    private String sanitizedDescription;
    @SerializedName("specialRecipe")
    private int specialRecipe;

    public ItemEnity(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex(MyItemDAO.FIELD_ID));
        this.name = cursor.getString(cursor.getColumnIndex(MyItemDAO.FIELD_NAME));
        this.description = cursor.getString(cursor.getColumnIndex(MyItemDAO.FIELD_DESCRIPTION));
        int totalGold = cursor.getInt(cursor.getColumnIndex(MyItemDAO.FIELD_GOLD_TOTAL));
        boolean purchaseable = cursor.getInt(cursor.getColumnIndex(MyItemDAO.FIELD_GOLD_PURCHASABLE)) == 1 ? true : false;
        int sellGold = cursor.getInt(cursor.getColumnIndex(MyItemDAO.FIELD_GOLD_SELL));
        int baseGold = cursor.getInt(cursor.getColumnIndex(MyItemDAO.FIELD_GOLD_BASE));
        this.gold = new GoldEnity(baseGold, totalGold, sellGold, purchaseable);
        this.sanitizedDescription = cursor.getString(cursor.getColumnIndex(MyItemDAO.FIELD_SANITIZEDDESCRIPTION));
        this.from = Utils.getListFromString(cursor.getString(cursor.getColumnIndex(MyItemDAO.FIELD_FROM)));
        this.into = Utils.getListFromString(cursor.getString(cursor.getColumnIndex(MyItemDAO.FIELD_INTO)));
        this.tags = Utils.getListFromString(cursor.getString(cursor.getColumnIndex(MyItemDAO.FIELD_TAGS)));
        this.plaintext = cursor.getString(cursor.getColumnIndex(MyItemDAO.FIELD_PLAINTEXT));
        this.image = new ImageEnity();
        this.image.setFull(cursor.getString(cursor.getColumnIndex(MyItemDAO.FIELD_IMAGE)));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isConsumeOnFull() {
        return consumeOnFull;
    }

    public void setConsumeOnFull(boolean consumeOnFull) {
        this.consumeOnFull = consumeOnFull;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, String> getEffect() {
        return effect;
    }

    public void setEffect(HashMap<String, String> effect) {
        this.effect = effect;
    }

    public GoldEnity getGold() {
        return gold;
    }

    public void setGold(GoldEnity gold) {
        this.gold = gold;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public boolean isHideFromAll() {
        return hideFromAll;
    }

    public void setHideFromAll(boolean hideFromAll) {
        this.hideFromAll = hideFromAll;
    }

    public ImageEnity getImage() {
        return image;
    }

    public void setImage(ImageEnity image) {
        this.image = image;
    }

    public boolean isInStore() {
        return inStore;
    }

    public void setInStore(boolean inStore) {
        this.inStore = inStore;
    }

    public List<String> getInto() {
        return into;
    }

    public void setInto(List<String> into) {
        this.into = into;
    }

    public List<String> getFrom() {
        return from;
    }

    public void setFrom(List<String> from) {
        this.from = from;
    }

    public HashMap<String, Boolean> getMaps() {
        return maps;
    }

    public void setMaps(HashMap<String, Boolean> maps) {
        this.maps = maps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public String getRequiredChampion() {
        return requiredChampion;
    }

    public void setRequiredChampion(String requiredChampion) {
        this.requiredChampion = requiredChampion;
    }

    public String getRune() {
        return rune;
    }

    public void setRune(String rune) {
        this.rune = rune;
    }

    public String getSanitizedDescription() {
        return sanitizedDescription;
    }

    public void setSanitizedDescription(String sanitizedDescription) {
        this.sanitizedDescription = sanitizedDescription;
    }

    public int getSpecialRecipe() {
        return specialRecipe;
    }

    public void setSpecialRecipe(int specialRecipe) {
        this.specialRecipe = specialRecipe;
    }
}
