package com.toandoan.lol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.toandoan.lol.R;
import com.toandoan.lol.adapter.SumonerRuneAdapter;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.SumonerEnity;
import com.toandoan.lol.model.rune.PageRunes;
import com.toandoan.lol.model.rune.RuneEnity;
import com.toandoan.lol.mvp_abstract.SumonerRunesAbstract;
import com.toandoan.lol.presenter.SummonerRunersPresenter;

import java.util.ArrayList;
import java.util.List;

public class SumonerRunesActivity extends BaseActivity implements SumonerRunesAbstract.View
        , MaterialSpinner.OnItemSelectedListener {
    private SummonerRunersPresenter mPresenter;
    private String mID;
    private MaterialSpinner mRuneSpinner;
    private RecyclerView mRuneRecyclerView;
    private TextView mTitleTextView;
    private SumonerRuneAdapter mAdapter;
    private List<PageRunes> mPageRunes;
    private SumonerEnity mSumonerEnity;

    public static void startActivity(Context context, SumonerEnity userID) {
        Intent intent = new Intent(context, SumonerRunesActivity.class);
        intent.putExtra(Constant.IntentKey.SUMONER, userID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumoner_runes);
        initData();
        initViews();
    }

    private void initData() {
        mSumonerEnity = (SumonerEnity) getIntent().getExtras().getSerializable(Constant.IntentKey.SUMONER);
        mID = String.valueOf(mSumonerEnity.getId());
    }

    @Override
    public void initViews() {
        getSupportActionBar().hide();
        mRuneSpinner = (MaterialSpinner) findViewById(R.id.spinner_runes);
        mRuneRecyclerView = (RecyclerView) findViewById(R.id.recycler_runes);
        mTitleTextView = (TextView) findViewById(R.id.text_title);

        mPresenter = new SummonerRunersPresenter(this, this);
        mPresenter.loadSumonerRunes(Constant.Region.NORTH_AMERICA, mID);
        mRuneSpinner.setOnItemSelectedListener(this);
    }


    @Override
    public void initViewPager(List<RuneEnity> runeEnities) {
        mAdapter = new SumonerRuneAdapter(this, runeEnities);
        mRuneRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRuneRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void updateTabLayout(String title) {
        mTitleTextView.setText(title);
    }

    @Override
    public void updateSpinner(List<PageRunes> pageRunes) {
        mPageRunes = new ArrayList<>(pageRunes);
        List<String> spinnerTitle = new ArrayList<>();
        for (PageRunes page : mPageRunes) {
            spinnerTitle.add(page.getName());
        }

        mRuneSpinner.setItems(spinnerTitle);
    }

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        PageRunes currentPage = mPageRunes.get(position);
        mPresenter.onSpinnerSelected(currentPage);
    }
}
