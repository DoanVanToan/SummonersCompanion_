package com.toandoan.lol.model.item;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public class GoldEnity {
    @SerializedName("base")
    private int base;
    @SerializedName("purchasable")
    private boolean purchasable;
    @SerializedName("total")
    private int total;
    @SerializedName("sell")
    private int sell;

    public GoldEnity() {
    }

    public GoldEnity(int base, int total, int sell, boolean purchasable) {
        this.base = base;
        this.purchasable = purchasable;
        this.total = total;
        this.sell = sell;
    }

    public GoldEnity(Cursor cursor) {

    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public boolean isPurchasable() {
        return purchasable;
    }

    public void setPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSell() {
        return sell;
    }

    public void setSell(int sell) {
        this.sell = sell;
    }


}
