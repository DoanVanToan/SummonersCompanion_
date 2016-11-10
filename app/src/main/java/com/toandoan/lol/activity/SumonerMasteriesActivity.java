package com.toandoan.lol.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.TextView;

import com.toandoan.lol.R;
import com.toandoan.lol.adapter.MasteriesPagerAdapter;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.database.impl.MasteriesImpl;
import com.toandoan.lol.listenner.MasteriesListenner;
import com.toandoan.lol.model.matery.MasteryEnity;
import com.toandoan.lol.mvp_abstract.SumonerMasteriesAbstract;
import com.toandoan.lol.presenter.SumonerMasteriesPresenter;
import com.toandoan.lol.utility.Utils;

import java.util.ArrayList;
import java.util.List;

public class SumonerMasteriesActivity extends BaseActivity implements SumonerMasteriesAbstract.View,
        MasteriesListenner {
    private ViewPager mMasteryViewPager;
    private TabLayout mMasteryTablayout;
    private MasteriesPagerAdapter mMasteryAdapter;
    private String mSumonerID;
    private SumonerMasteriesPresenter mPresenter;
    private TextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumoner_masteries);
        initData();
        initViews();
    }

    private void initData() {
        mSumonerID = String.valueOf(29617123);
    }

    @Override
    public void initViews() {
        mMasteryViewPager = (ViewPager) findViewById(R.id.viewpager_mastery);
        mMasteryTablayout = (TabLayout) findViewById(R.id.tab_mastery_title);
        mTitleTextView = (TextView) findViewById(R.id.title_text_view);
        mPresenter = new SumonerMasteriesPresenter(this, this);
        mPresenter.loadSumonerMasteries(Constant.Region.NORTH_AMERICA, mSumonerID);
    }

    @Override
    public void initViewPager(List<MasteryEnity> masteryEnities) {
        mMasteryAdapter = new MasteriesPagerAdapter(this, masteryEnities, this);
        mMasteryViewPager.setAdapter(mMasteryAdapter);
        mMasteryTablayout.setupWithViewPager(mMasteryViewPager);
    }

    @Override
    public void updateTabLayout(int countFerocity, int countCunning, int countReslve) {


        mTitleTextView.setText(countFerocity + " / " + countCunning + " / " + countReslve);
    }


    @Override
    public void getAllMasteriesSuccessful(List<MasteryEnity> listData) {
        // To do

    }

    @Override
    public void getAllMasteriesFail(String message) {

    }

    @Override
    public void onItemClickListenner(MasteryEnity masteryEnity) {
        Utils.showMasteryDialog(activity, masteryEnity);
    }
}
