package com.toandoan.lol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.toandoan.lol.R;
import com.toandoan.lol.adapter.ChampionFragmentPagerAdapter;
import com.toandoan.lol.adapter.SkinPagerAdapter;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.fragment.MasteriesFragment;
import com.toandoan.lol.presenter.ChampionDetailActivityPresenter;
import com.toandoan.lol.fragment.AbilitiesFragment;
import com.toandoan.lol.fragment.ChampionOverviewFragment;
import com.toandoan.lol.fragment.CounterAndTipFragment;
import com.toandoan.lol.listenner.ChampionDetailActivityListenner;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.utility.LogUtil;
import com.toandoan.lol.utility.Utils;
import com.toandoan.lol.widget.view.AutoScrollViewPager;

public class ChampionDetailActivity extends BaseActivity implements ViewPager.OnPageChangeListener
        , SwipeRefreshLayout.OnRefreshListener {
    private ChampionEnity championEnity;
    private ChampionOverviewFragment championOverviewFragment;
    private CounterAndTipFragment counterAndTipFragment;
    private AbilitiesFragment abilitiesFragment;
    private Toolbar mToolbar;
    private AutoScrollViewPager vpSkin;
    private ChampionDetailActivityPresenter helper;
    private SkinPagerAdapter bannerAdaper;
    private ViewPager vpContent;
    private ChampionFragmentPagerAdapter championAdapter;
    private TabLayout tlChampion;
    private ImageView ivChampIcon;
    private TextView tvChampName, tvChampTitle, tvChampSkin;
    private SwipeRefreshLayout srlChampion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_detail);
        getDataIntent();
        initViews();
    }

    private void initViews() {
        vpSkin = (AutoScrollViewPager) findViewById(R.id.vpSkin);
        vpContent = (ViewPager) findViewById(R.id.vpContent);
        mToolbar = (Toolbar) findViewById(R.id.mToolbar);

        ivChampIcon = (ImageView) findViewById(R.id.ivChampIcon);
        Glide.with(activity)
                .load(Utils.RiotStatic.getChampionIcon(championEnity.getKey()))
                .into(ivChampIcon);

        tvChampName = (TextView) findViewById(R.id.tvChampName);
        tvChampName.setText(championEnity.getName());


        tvChampTitle = (TextView) findViewById(R.id.tvChampTitle);
        tvChampTitle.setText(championEnity.getTitle());

        tvChampSkin = (TextView) findViewById(R.id.tvChampSkin);
        tvChampSkin.setText(getResources().getString(R.string._default));

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        srlChampion = (SwipeRefreshLayout) findViewById(R.id.srlChampion);
        srlChampion.setOnRefreshListener(this);
        srlChampion.setEnabled(false);

        initHelper();

    }

    private void initHelper() {
        helper = new ChampionDetailActivityPresenter(this, listenner);
        helper.loadCacheData(championEnity.getId());
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            championEnity = (ChampionEnity) intent.getExtras()
                    .getSerializable(Constant.IntentKey.CHAMPION_ENITY);

        }
    }

    public void updateUI(final ChampionEnity championEnity) {
        this.championEnity = championEnity;
        vpSkin.setScrollPeriod(5000);
        bannerAdaper = new SkinPagerAdapter(activity, championEnity);
        vpSkin.setAdapter(bannerAdaper);
        vpSkin.startScroll();
        vpSkin.setOnItemClickListener(new AutoScrollViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onPageSelected(int position) {
                tvChampSkin.setText(championEnity.getSkins().get(position).getName() + "");
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getSupportActionBar().setTitle(championEnity.getSkins().get(position).getName());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpViewPager(ChampionEnity championEnity) {
        championOverviewFragment = ChampionOverviewFragment.newInstance(championEnity);
        counterAndTipFragment = CounterAndTipFragment.newInstance(championEnity);
        abilitiesFragment = AbilitiesFragment.newInstance(championEnity);
        MasteriesFragment masteriesFragment =  MasteriesFragment.newInstance();

        championAdapter = new ChampionFragmentPagerAdapter(getSupportFragmentManager());
        championAdapter.addFrag(championOverviewFragment, getString(R.string.overview));
        championAdapter.addFrag(counterAndTipFragment, getString(R.string.counter_and_tip));
        championAdapter.addFrag(abilitiesFragment, getString(R.string.abilities));
        championAdapter.addFrag(masteriesFragment, getString(R.string.abilities));

        vpContent.setAdapter(championAdapter);

    }

    private void setUpTabLayout() {
        tlChampion = (TabLayout) findViewById(R.id.tabLayout);
        tlChampion.setupWithViewPager(vpContent);

        tlChampion.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpContent.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    /**
     * ChampionHelper
     */
    ChampionDetailActivityListenner listenner = new ChampionDetailActivityListenner() {
        @Override
        public void getChampionByIDSucces(ChampionEnity championEnity) {
            updateUI(championEnity);
            setUpViewPager(championEnity);
            setUpTabLayout();
            srlChampion.setRefreshing(false);
            LogUtil.e(championEnity.getName()+"___1", Utils.RiotStatic.getChampionAbiliteUrlVideo(championEnity, 1));
            LogUtil.e(championEnity.getName()+"___2", Utils.RiotStatic.getChampionAbiliteUrlVideo(championEnity, 2));
            LogUtil.e(championEnity.getName()+"___3", Utils.RiotStatic.getChampionAbiliteUrlVideo(championEnity, 3));
            LogUtil.e(championEnity.getName()+"___4", Utils.RiotStatic.getChampionAbiliteUrlVideo(championEnity, 4));
            LogUtil.e(championEnity.getName()+"___5", Utils.RiotStatic.getChampionAbiliteUrlVideo(championEnity, 5));
        }

        @Override
        public void getChampionByIDFail() {
            srlChampion.setRefreshing(false);
        }

    };

    @Override
    public void onRefresh() {
        helper.getChampionByID(Constant.Region.NORTH_AMERICA, championEnity.getId());
    }
}
