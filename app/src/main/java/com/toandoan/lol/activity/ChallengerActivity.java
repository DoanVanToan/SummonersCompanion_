package com.toandoan.lol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.toandoan.lol.R;
import com.toandoan.lol.adapter.ChallengeAdapter;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.sumoner_overview.LeagueEntryEnity;
import com.toandoan.lol.mvp_abstract.ChallengerContract;
import com.toandoan.lol.presenter.ChallengerPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChallengerActivity extends BaseActivity implements ChallengerContract.View {
    @BindView(R.id.challange_recyclerview)
    RecyclerView mChallangeRecyclerview;

    private ChallengerPresenter mPresenter;
    private ChallengeAdapter mAdapter;
    private List<LeagueEntryEnity> mEntries;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ChallengerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenger);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    public void initViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPresenter = new ChallengerPresenter(this, this);
        mPresenter.loadChallengerSumoner(Constant.Region.NORTH_AMERICA);
    }

    @Override
    public void updateChallengeSumoner(List<LeagueEntryEnity> entries) {
        mEntries = new ArrayList<>(entries);
        mEntries.add(0, null);
        mAdapter = new ChallengeAdapter(this, mEntries);
        mChallangeRecyclerview.setLayoutManager(new LinearLayoutManager(activity));
        mChallangeRecyclerview.setAdapter(mAdapter);
    }
}
