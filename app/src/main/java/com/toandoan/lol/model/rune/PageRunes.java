package com.toandoan.lol.model.rune;

import com.google.gson.annotations.SerializedName;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.utility.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 11/11/2016.
 */

public class PageRunes {
    @SerializedName("id")
    private int mID;
    @SerializedName("slots")
    private List<RuneEnity> mRunes;
    @SerializedName("name")
    private String mName;
    @SerializedName("current")
    private boolean mCurrent;
    private List<RuneEnity> mCountRunes;
    private String mPageTitle;


    public int getID() {
        return mID;
    }

    public void setmID(int ID) {
        this.mID = ID;
    }

    public List<RuneEnity> getRunes() {
        return mRunes;
    }

    public void setRunes(List<RuneEnity> runes) {
        this.mRunes = runes;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public boolean isCurrent() {
        return mCurrent;
    }

    public void setCurrent(boolean current) {
        this.mCurrent = current;
    }

    public void updateCountRunes() {
        mCountRunes = new ArrayList<>();
        for (RuneEnity rune : mRunes) {
            int positionOfRune = positionOnList(rune, mCountRunes);
            if (positionOfRune == -1) {
                rune.setmCount(1);
                mCountRunes.add(rune);
            } else {
                mCountRunes.get(positionOfRune).setmCount(mCountRunes.get(positionOfRune).getmCount() + 1);
            }
        }
    }

    public List<RuneEnity> getCountRunes() {
        return mCountRunes;
    }

    public void setCountRunes(List<RuneEnity> countRunes) {
        this.mCountRunes = countRunes;
    }

    public int positionOnList(RuneEnity checkRune, List<RuneEnity> runes) {
        if (runes != null && runes.size() > 0) {
            for (int i = 0; i < runes.size(); i++) {
                RuneEnity rune = runes.get(i);
                if (rune.getId() == checkRune.getId()) return i;
            }
        }
        return -1;
    }

    public String getPageTitle() {
        StringBuilder stringBuilder = new StringBuilder();
        for (RuneEnity rune : mCountRunes) {
            String description = rune.getDescription();
            String runeHeader = description.substring(0, 1);
            int firstSpace = description.indexOf(Constant.Charactor.SPACE);
            String runeFooter = description.substring(firstSpace, description.length());
            String runeValueStr = description.substring(1, firstSpace);

            if (runeValueStr.endsWith(Constant.Charactor.PERCENT)) {
                runeFooter = Constant.Charactor.PERCENT + runeFooter;
                runeValueStr = runeValueStr.substring(0, runeValueStr.length() - 1);
            }

            double runValue = Double.parseDouble(runeValueStr);
            runValue = rune.getmCount() * runValue;
            runeValueStr = Utils.formatDouble(runValue);

            int index = runeFooter.indexOf("(");
            if (index == -1) index = runeFooter.indexOf("/");

            if (index != -1) {
                StringBuilder builder = new StringBuilder();
                builder.append(runeFooter.substring(0, index + 1));

                runeFooter = runeFooter.substring(index + 2, runeFooter.length());
                int space = runeFooter.indexOf(Constant.Charactor.SPACE);

                String value = runeFooter.substring(0, space);
                String lastFooter = runeFooter.substring(space, runeFooter.length());
                if (value.endsWith(Constant.Charactor.PERCENT)) {
                    lastFooter = Constant.Charactor.PERCENT + lastFooter;
                    value.replace(Constant.Charactor.PERCENT, Constant.Charactor.SPACE);
                }

                double valuePerLevel = Double.parseDouble(value);
                valuePerLevel = rune.getmCount() * valuePerLevel;
                value = Utils.formatDouble(valuePerLevel);
                if (valuePerLevel > 0) value = Constant.Charactor.PLUS + value;

                builder
                        .append(value)
                        .append(lastFooter);

                runeFooter = builder.toString();
            }

            stringBuilder
                    .append(runeHeader)
                    .append(Constant.Charactor.SPACE)
                    .append(runeValueStr)
                    .append(Constant.Charactor.SPACE)
                    .append(runeFooter)
                    .append(Constant.Charactor.LINE_BREAK);
        }

        mPageTitle = stringBuilder.toString();
        if (mPageTitle.endsWith(Constant.Charactor.LINE_BREAK)) {
            mPageTitle = mPageTitle.substring(0, mPageTitle.length() - 1);
        }
        return mPageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.mPageTitle = pageTitle;
    }
}
