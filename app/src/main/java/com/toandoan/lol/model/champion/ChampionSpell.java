package com.toandoan.lol.model.champion;

import com.toandoan.lol.model.ImageEnity;

import java.util.List;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public class ChampionSpell {
    private String id;
    private String name;
    private String description;
    private String tooltip;
    private SpellLevelTip leveltip;
    private int maxrank;
    private List<Float> cooldown;
    private String cooldownBurn;
    private List<Float> cost;
    private String costBurn;
    private List<List<Float>> effect;
    private List<String> effectBurn;
    private String costType;
    private Float maxammo;
    private List<Float> range;
    private String rangeBurn;
    private ImageEnity image;
    private String resource;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public SpellLevelTip getLeveltip() {
        return leveltip;
    }

    public void setLeveltip(SpellLevelTip leveltip) {
        this.leveltip = leveltip;
    }

    public int getMaxrank() {
        return maxrank;
    }

    public void setMaxrank(int maxrank) {
        this.maxrank = maxrank;
    }

    public List<Float> getCooldown() {
        return cooldown;
    }

    public void setCooldown(List<Float> cooldown) {
        this.cooldown = cooldown;
    }

    public String getCooldownBurn() {
        return cooldownBurn;
    }

    public void setCooldownBurn(String cooldownBurn) {
        this.cooldownBurn = cooldownBurn;
    }

    public List<Float> getCost() {
        return cost;
    }

    public void setCost(List<Float> cost) {
        this.cost = cost;
    }

    public String getCostBurn() {
        return costBurn;
    }

    public void setCostBurn(String costBurn) {
        this.costBurn = costBurn;
    }

    public List<List<Float>> getEffect() {
        return effect;
    }

    public void setEffect(List<List<Float>> effect) {
        this.effect = effect;
    }

    public List<String> getEffectBurn() {
        return effectBurn;
    }

    public void setEffectBurn(List<String> effectBurn) {
        this.effectBurn = effectBurn;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public Float getMaxammo() {
        return maxammo;
    }

    public void setMaxammo(Float maxammo) {
        this.maxammo = maxammo;
    }

    public List<Float> getRange() {
        return range;
    }

    public void setRange(List<Float> range) {
        this.range = range;
    }

    public String getRangeBurn() {
        return rangeBurn;
    }

    public void setRangeBurn(String rangeBurn) {
        this.rangeBurn = rangeBurn;
    }

    public ImageEnity getImage() {
        return image;
    }

    public void setImage(ImageEnity image) {
        this.image = image;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public class SpellLevelTip{
        private List<String> label;
        private List<String> effect;
    }

    public class SpellVar{
        private String link;
        private float coeff;
        private String key;


        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public float getCoeff() {
            return coeff;
        }

        public void setCoeff(float coeff) {
            this.coeff = coeff;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}


