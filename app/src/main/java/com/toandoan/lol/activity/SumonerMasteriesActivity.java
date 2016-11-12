package com.toandoan.lol.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.toandoan.lol.R;
import com.toandoan.lol.adapter.MasteriesPagerAdapter;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.listenner.MasteriesListenner;
import com.toandoan.lol.model.UserEnity;
import com.toandoan.lol.model.matery.MasteryEnity;
import com.toandoan.lol.model.matery.PageMasteries;
import com.toandoan.lol.mvp_abstract.SumonerMasteriesAbstract;
import com.toandoan.lol.presenter.SumonerMasteriesPresenter;
import com.toandoan.lol.utility.Utils;

import java.util.ArrayList;
import java.util.List;

public class SumonerMasteriesActivity extends BaseActivity implements SumonerMasteriesAbstract.View,
        MasteriesListenner, MaterialSpinner.OnItemSelectedListener {
    private ViewPager mMasteryViewPager;
    private TabLayout mMasteryTablayout;
    private MasteriesPagerAdapter mMasteryAdapter;
    private String mSumonerID;
    private SumonerMasteriesPresenter mPresenter;
    private TextView mTitleTextView;
    private MaterialSpinner mMasterySpinner;
    private List<PageMasteries> mPageMasteries;
    private UserEnity mUserEnity;

    public static void startActivity(Context context, UserEnity userID) {
        Intent intent = new Intent(context, SumonerMasteriesActivity.class);
        intent.putExtra(Constant.IntentKey.SUMONER, userID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumoner_masteries);
        initData();
        initViews();
    }

    private void initData() {
        mUserEnity = (UserEnity) getIntent().getExtras().getSerializable(Constant.IntentKey.SUMONER);
        mSumonerID = String.valueOf(mUserEnity.getId());
    }

    @Override
    public void initViews() {
        mMasteryViewPager = (ViewPager) findViewById(R.id.viewpager_mastery);
        mMasteryTablayout = (TabLayout) findViewById(R.id.tab_mastery_title);
        mTitleTextView = (TextView) findViewById(R.id.title_text_view);
        mMasterySpinner = (MaterialSpinner) findViewById(R.id.matery_spinner);
        getSupportActionBar().hide();
        mPresenter = new SumonerMasteriesPresenter(this, this);
        mPresenter.loadSumonerMasteries(Constant.Region.NORTH_AMERICA, mSumonerID);
        mMasterySpinner.setOnItemSelectedListener(this);

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
    public void updateSpinner(List<PageMasteries> pageMasteries) {
        this.mPageMasteries = new ArrayList<>(pageMasteries);
        List<String> spinnerTitle = new ArrayList<>();
        for (PageMasteries page : pageMasteries) {
            spinnerTitle.add(page.getName());
        }

        mMasterySpinner.setItems(spinnerTitle);
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

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        PageMasteries currentPage = mPageMasteries.get(position);
        mPresenter.onSpinnerSelected(currentPage);
    }
}
