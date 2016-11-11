package com.toandoan.lol.mvp_abstract;

import com.toandoan.lol.base.BaseView;
import com.toandoan.lol.model.rune.PageRunes;
import com.toandoan.lol.model.rune.RuneEnity;

import java.util.List;

/**
 * Created by framgia on 11/11/2016.
 */

public interface SumonerRunesAbstract {
    interface View extends BaseView{
        void initViewPager(List<RuneEnity> runeEnities);
        void updateTabLayout(String title);
        void updateSpinner(List<PageRunes> pageRunes);
    }

    interface Presenter{
        void loadSumonerRunes(String region, String id);
        void onSpinnerSelected(PageRunes page);
    }
}
