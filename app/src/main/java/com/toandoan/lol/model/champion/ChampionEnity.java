package com.toandoan.lol.model.champion;

import com.toandoan.lol.model.ImageEnity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public class ChampionEnity implements Serializable{
    private String id;
    private String key;
    private String name;
    private String title;
    private ImageEnity image;
    private List<ChampionSkinEnity> skins;
    private String lore;
    private String blurb;
    private List<String> allytips;
    private List<String> enemytips;
    private List<String> tags;
    private String partype;
    private ChampionInfoEnity info;
    private ChampionStats stats;
    private List<ChampionSpell> spells;
    private ChampionPassive passive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageEnity getImage() {
        return image;
    }

    public void setImage(ImageEnity image) {
        this.image = image;
    }

    public List<ChampionSkinEnity> getSkins() {
        return skins;
    }

    public void setSkins(List<ChampionSkinEnity> skins) {
        this.skins = skins;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public List<String> getAllytips() {
        return allytips;
    }

    public void setAllytips(List<String> allytips) {
        this.allytips = allytips;
    }

    public List<String> getEnemytips() {
        return enemytips;
    }

    public void setEnemytips(List<String> enemytips) {
        this.enemytips = enemytips;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getPartype() {
        return partype;
    }

    public void setPartype(String partype) {
        this.partype = partype;
    }

    public ChampionInfoEnity getInfo() {
        return info;
    }

    public void setInfo(ChampionInfoEnity info) {
        this.info = info;
    }

    public ChampionStats getStats() {
        return stats;
    }

    public void setStats(ChampionStats stats) {
        this.stats = stats;
    }

    public List<ChampionSpell> getSpells() {
        return spells;
    }

    public void setSpells(List<ChampionSpell> spells) {
        this.spells = spells;
    }

    public ChampionPassive getPassive() {
        return passive;
    }

    public void setPassive(ChampionPassive passive) {
        this.passive = passive;
    }
}
