package com.toandoan.lol.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.toandoan.lol.R;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.matery.MasteryEnity;
import com.toandoan.lol.model.matery.PageMasteries;
import com.toandoan.lol.model.rune.PageRunes;
import com.toandoan.lol.model.rune.RuneEnity;
import com.toandoan.lol.mvp_abstract.SumonerMasteriesAbstract;
import com.toandoan.lol.mvp_abstract.SumonerRunesAbstract;
import com.toandoan.lol.presenter.SummonerRunersPresenter;

import java.util.List;

public class SumonerRunesActivity extends BaseActivity implements SumonerRunesAbstract.View{
    private SummonerRunersPresenter mPresenter;
    private String mID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumoner_runes);
        initData();
        initViews();
    }

    private void initData(){
        mID = String.valueOf(26700896);
    }

    @Override
    public void initViews() {
        mPresenter = new SummonerRunersPresenter(this, this);
        mPresenter.loadSumonerMasteries(Constant.Region.NORTH_AMERICA, mID);
    }


    @Override
    public void initViewPager(List<RuneEnity> runeEnities) {

    }

    @Override
    public void updateTabLayout(String title) {

    }

    @Override
    public void updateSpinner(List<PageRunes> pageRunes) {

    }
}
