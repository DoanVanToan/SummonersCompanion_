package com.toandoan.lol.model.champion;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public class ChampionSkinEnity {
    private int id;
    private int num;
    private String name;
    private boolean chromas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChromas() {
        return chromas;
    }

    public void setChromas(boolean chromas) {
        this.chromas = chromas;
    }
}
