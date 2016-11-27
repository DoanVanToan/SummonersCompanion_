package com.toandoan.lol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.toandoan.lol.R;
import com.toandoan.lol.adapter.ChallengeAdapter;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.SumonerEnity;
import com.toandoan.lol.model.sumoner_overview.LeagueEntryEnity;
import com.toandoan.lol.mvp_abstract.ChallengerContract;
import com.toandoan.lol.presenter.ChallengerPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChallengerActivity extends BaseActivity implements ChallengerContract.View
        , ChallengeAdapter.OnItemClickListenner, SearchView.OnQueryTextListener {
    @BindView(R.id.challange_recyclerview)
    RecyclerView mChallangeRecyclerview;

    private ChallengerPresenter mPresenter;
    private ChallengeAdapter mAdapter;
    private List<LeagueEntryEnity> mEntries;
    private SumonerEnity mSumoner;
    private String mRankType;
    private boolean isChallengerank;

    public static void startActivity(Context context, SumonerEnity sumoner, String type) {
        Intent intent = new Intent(context, ChallengerActivity.class);
        if (sumoner != null) intent.putExtra(Constant.IntentKey.SUMONER, sumoner);
        if (type != null) intent.putExtra(Constant.IntentKey.RANK_TYPE, type);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenger);
        ButterKnife.bind(this);
        getIntentData();
        initViews();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            mSumoner = (SumonerEnity) intent.getExtras().getSerializable(Constant.IntentKey.SUMONER);
            mRankType = intent.getExtras().getString(Constant.IntentKey.RANK_TYPE);
        }
        if (mRankType != null && mRankType.equals(Constant.SumonerStaticData.CHALLENGE_RANK)) {
            isChallengerank = true;
        }
    }

    @Override
    public void initViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPresenter = new ChallengerPresenter(this, this);

        if (isChallengerank) {
            mPresenter.loadChallengerSumoner(Constant.Region.NORTH_AMERICA);
        } else {
            mPresenter.loadSumonerRank(Constant.Region.NORTH_AMERICA, String.valueOf(mSumoner.getId()), mRankType);
        }
    }

    @Override
    public void updateChallengeSumoner(List<LeagueEntryEnity> entries) {
        mEntries = new ArrayList<>(entries);
        mEntries.add(0, null);
        mAdapter = new ChallengeAdapter(this, mEntries, this);
        mChallangeRecyclerview.setLayoutManager(new LinearLayoutManager(activity));
        mChallangeRecyclerview.setAdapter(mAdapter);
        mChallangeRecyclerview.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void scrollToCurrentPosition() {
        if (mEntries == null || mEntries.size() == 0) return;
        for (int i = 0; i < mEntries.size(); i++) {
            LeagueEntryEnity leagu = mEntries.get(i);
            if (leagu != null && leagu.getPlayerOrTeamName() != null
                    && mEntries.get(i).getPlayerOrTeamName().equalsIgnoreCase(mSumoner.getName())) {
                mChallangeRecyclerview.scrollToPosition(i);
                break;
            }
        }

    }

    @Override
    public void onItemClick(int position, LeagueEntryEnity leagueEntryEnity) {
        SumonerEnity sumonerEnity = new SumonerEnity();
        sumonerEnity.setName(leagueEntryEnity.getPlayerOrTeamName());
        SumonerDetailActivity.startActivity(this, sumonerEnity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuSearch = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuSearch.getActionView();
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mPresenter.searchSumoner(newText);
        return false;
    }
}
