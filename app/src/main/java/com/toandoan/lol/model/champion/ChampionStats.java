package com.toandoan.lol.model.champion;

import com.toandoan.lol.constant.Constant;

import org.json.JSONObject;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public class ChampionStats {
    private float hp;
    private float hpperlevel;
    private float mp;
    private float mpperlevel;
    private float movespeed;
    private float armor;
    private float armorperlevel;
    private float spellblock;
    private float spellblockperlevel;
    private float attackrange;
    private float hpregen;
    private float hpregenperlevel;
    private float mpregen;
    private float mpregenperlevel;
    private float crit;
    private float critperlevel;
    private float attackdamage;
    private float attackdamageperlevel;
    private float attackspeedoffset;
    private float attackspeedperlevel;

    public ChampionStats(JSONObject jsonObject) {
        if (jsonObject != null) {
            if (!jsonObject.isNull(Constant.ApiKey.HP)) {
                hp = (float) jsonObject.optDouble(Constant.ApiKey.HP);
            }

            if (!jsonObject.isNull(Constant.ApiKey.HPPERLEVEL)) {
                hpperlevel = (float) jsonObject.optDouble(Constant.ApiKey.HPPERLEVEL);
            }

            if (!jsonObject.isNull(Constant.ApiKey.MP)) {
                mp = (float) jsonObject.optDouble(Constant.ApiKey.MP);
            }

            if (!jsonObject.isNull(Constant.ApiKey.MPPERLEVEL)) {
                mpperlevel = (float) jsonObject.optDouble(Constant.ApiKey.MPPERLEVEL);
            }

            if (!jsonObject.isNull(Constant.ApiKey.MOVESPEED)) {
                movespeed = (float) jsonObject.optDouble(Constant.ApiKey.MOVESPEED);
            }

            if (!jsonObject.isNull(Constant.ApiKey.ARMOR)) {
                armor = (float) jsonObject.optDouble(Constant.ApiKey.ARMOR);
            }

            if (!jsonObject.isNull(Constant.ApiKey.ARMORPERLEVEL)) {
                armorperlevel = (float) jsonObject.optDouble(Constant.ApiKey.ARMORPERLEVEL);
            }

            if (!jsonObject.isNull(Constant.ApiKey.SPELLBLOCK)) {
                spellblock = (float) jsonObject.optDouble(Constant.ApiKey.SPELLBLOCK);
            }

            if (!jsonObject.isNull(Constant.ApiKey.SPELLBLOCKPERLEVEL)) {
                spellblockperlevel = (float) jsonObject.optDouble(Constant.ApiKey.SPELLBLOCKPERLEVEL);
            }

            if (!jsonObject.isNull(Constant.ApiKey.ATTACKRANGE)) {
                attackrange = (float) jsonObject.optDouble(Constant.ApiKey.ATTACKRANGE);
            }

            if (!jsonObject.isNull(Constant.ApiKey.HPREGEN)) {
                hpregen = (float) jsonObject.optDouble(Constant.ApiKey.HPREGEN);
            }

            if (!jsonObject.isNull(Constant.ApiKey.HPREGENPERLEVEL)) {
                hpregenperlevel = (float) jsonObject.optDouble(Constant.ApiKey.HPREGENPERLEVEL);
            }

            if (!jsonObject.isNull(Constant.ApiKey.CRIT)) {
                crit = (float) jsonObject.optDouble(Constant.ApiKey.CRIT);
            }

            if (!jsonObject.isNull(Constant.ApiKey.CRITPERLEVEL)) {
                critperlevel = (float) jsonObject.optDouble(Constant.ApiKey.CRITPERLEVEL);
            }

            if (!jsonObject.isNull(Constant.ApiKey.ATTACKDAMAGE)) {
                attackdamage = (float) jsonObject.optDouble(Constant.ApiKey.ATTACKDAMAGE);
            }

            if (!jsonObject.isNull(Constant.ApiKey.ATTACKDAMAGEPERLEVEL)) {
                attackdamageperlevel = (float) jsonObject.optDouble(Constant.ApiKey.ATTACKDAMAGEPERLEVEL);
            }

            if (!jsonObject.isNull(Constant.ApiKey.ATTACKSPEEDOFFSET)) {
                attackspeedoffset = (float) jsonObject.optDouble(Constant.ApiKey.ATTACKSPEEDOFFSET);
            }

            if (!jsonObject.isNull(Constant.ApiKey.ATTACKSPEEDPERLEVEL)) {
                attackspeedperlevel = (float) jsonObject.optDouble(Constant.ApiKey.ATTACKSPEEDPERLEVEL);
            }

        }
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getHpperlevel() {
        return hpperlevel;
    }

    public void setHpperlevel(float hpperlevel) {
        this.hpperlevel = hpperlevel;
    }

    public float getMp() {
        return mp;
    }

    public void setMp(float mp) {
        this.mp = mp;
    }

    public float getMpperlevel() {
        return mpperlevel;
    }

    public void setMpperlevel(float mpperlevel) {
        this.mpperlevel = mpperlevel;
    }

    public float getMovespeed() {
        return movespeed;
    }

    public void setMovespeed(float movespeed) {
        this.movespeed = movespeed;
    }

    public float getArmor() {
        return armor;
    }

    public void setArmor(float armor) {
        this.armor = armor;
    }

    public float getArmorperlevel() {
        return armorperlevel;
    }

    public void setArmorperlevel(float armorperlevel) {
        this.armorperlevel = armorperlevel;
    }

    public float getSpellblock() {
        return spellblock;
    }

    public void setSpellblock(float spellblock) {
        this.spellblock = spellblock;
    }

    public float getSpellblockperlevel() {
        return spellblockperlevel;
    }

    public void setSpellblockperlevel(float spellblockperlevel) {
        this.spellblockperlevel = spellblockperlevel;
    }

    public float getAttackrange() {
        return attackrange;
    }

    public void setAttackrange(float attackrange) {
        this.attackrange = attackrange;
    }

    public float getHpregen() {
        return hpregen;
    }

    public void setHpregen(float hpregen) {
        this.hpregen = hpregen;
    }

    public float getHpregenperlevel() {
        return hpregenperlevel;
    }

    public void setHpregenperlevel(float hpregenperlevel) {
        this.hpregenperlevel = hpregenperlevel;
    }

    public float getMpregen() {
        return mpregen;
    }

    public void setMpregen(float mpregen) {
        this.mpregen = mpregen;
    }

    public float getMpregenperlevel() {
        return mpregenperlevel;
    }

    public void setMpregenperlevel(float mpregenperlevel) {
        this.mpregenperlevel = mpregenperlevel;
    }

    public float getCrit() {
        return crit;
    }

    public void setCrit(float crit) {
        this.crit = crit;
    }

    public float getCritperlevel() {
        return critperlevel;
    }

    public void setCritperlevel(float critperlevel) {
        this.critperlevel = critperlevel;
    }

    public float getAttackdamage() {
        return attackdamage;
    }

    public void setAttackdamage(float attackdamage) {
        this.attackdamage = attackdamage;
    }

    public float getAttackdamageperlevel() {
        return attackdamageperlevel;
    }

    public void setAttackdamageperlevel(float attackdamageperlevel) {
        this.attackdamageperlevel = attackdamageperlevel;
    }

    public float getAttackspeedoffset() {
        return attackspeedoffset;
    }

    public void setAttackspeedoffset(float attackspeedoffset) {
        this.attackspeedoffset = attackspeedoffset;
    }

    public float getAttackspeedperlevel() {
        return attackspeedperlevel;
    }

    public void setAttackspeedperlevel(float attackspeedperlevel) {
        this.attackspeedperlevel = attackspeedperlevel;
    }
}
