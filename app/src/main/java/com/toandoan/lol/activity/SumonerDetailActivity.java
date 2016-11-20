package com.toandoan.lol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.toandoan.lol.R;
import com.toandoan.lol.adapter.MyFragmentPagerAdapter;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.fragment.SumonerMatchesListFragment;
import com.toandoan.lol.model.UserEnity;
import com.toandoan.lol.mvp_abstract.SumonerDetailAsbtract;
import com.toandoan.lol.presentation.match.ChampionDetailBySeasonFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SumonerDetailActivity extends BaseActivity implements SumonerDetailAsbtract.View, View.OnClickListener {

    @BindView(R.id.sumoner_viewpager)
    ViewPager sumonerViewpager;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    private FloatingActionButton mRunesButton, mMasteriesButton;
    private Toolbar mToolbar;
    private UserEnity mUserEnity;
    private FloatingActionMenu mMenu;
    private CollapsingToolbarLayout mCollapsingTollbar;
    private SumonerMatchesListFragment mMatchesListFragment;
    private ChampionDetailBySeasonFragment mChampionStatsFragment;
    private MyFragmentPagerAdapter mPagerAdapter;

    public static void startActivity(Context context, UserEnity userID) {
        Intent intent = new Intent(context, SumonerDetailActivity.class);
        intent.putExtra(Constant.IntentKey.SUMONER, userID);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            mUserEnity = (UserEnity) intent.getExtras().getSerializable(Constant.IntentKey.SUMONER);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumoner_detail);
        ButterKnife.bind(this);
        getData();
        initViews();
    }


    @Override
    public void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        mCollapsingTollbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCollapsingTollbar.setTitle(mUserEnity.getName());

        mRunesButton = (FloatingActionButton) findViewById(R.id.menu_runes);
        mMasteriesButton = (FloatingActionButton) findViewById(R.id.menu_masteries);
        mMenu = (FloatingActionMenu) findViewById(R.id.menu);
        mRunesButton.setOnClickListener(this);
        mMasteriesButton.setOnClickListener(this);

        mMatchesListFragment = SumonerMatchesListFragment.newInstance(mUserEnity);
        mChampionStatsFragment = ChampionDetailBySeasonFragment.newInstance(mUserEnity);

        mPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFrag(mMatchesListFragment, getString(R.string.match_story));
        mPagerAdapter.addFrag(mChampionStatsFragment, getString(R.string.champions));
        sumonerViewpager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(sumonerViewpager);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_runes:
                SumonerRunesActivity.startActivity(this, mUserEnity);
                mMenu.close(true);
                break;
            case R.id.menu_masteries:
                SumonerMasteriesActivity.startActivity(this, mUserEnity);
                mMenu.close(true);
                break;
            case R.id.menu:

                break;
        }
    }
}
