package com.toandoan.lol.presentation.match;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toandoan.lol.R;
import com.toandoan.lol.adapter.ChampionDetailStatsAdapter;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.listenner.ChampionDetailActivityListenner;
import com.toandoan.lol.model.UserEnity;
import com.toandoan.lol.model.champion_by_season.ChampionStatsEnity;
import com.toandoan.lol.presenter.ChampionDetailActivityPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChampionDetailBySeasonFragment extends Fragment implements ChampionDetailBySeasonContract.View {


    @BindView(R.id.champion_stats_recyclerview)
    RecyclerView mChampionStatsRecyclerview;

    private UserEnity mUser;
    private ChampionDetailBySeasonPresenter mPresenter;
    private ChampionDetailStatsAdapter mAdapter;

    public UserEnity getmUser() {
        return mUser;
    }

    public void setmUser(UserEnity mUser) {
        this.mUser = mUser;
    }

    public static ChampionDetailBySeasonFragment newInstance(UserEnity mUserEnity) {
        ChampionDetailBySeasonFragment fragment = new ChampionDetailBySeasonFragment();
        fragment.setmUser(mUserEnity);
        return fragment;
    }

    public ChampionDetailBySeasonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_champion_detail_by_season, container, false);
        initViews(v);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void updateListChampionStats(List<ChampionStatsEnity> champions) {
        mAdapter = new ChampionDetailStatsAdapter(getActivity(), champions);
        mChampionStatsRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mChampionStatsRecyclerview.setAdapter(mAdapter);
    }

    private void initViews(View v) {
        ButterKnife.bind(v);
        mPresenter = new ChampionDetailBySeasonPresenter((BaseActivity) getActivity(), this);
        mPresenter.loadChampionDetailStats(Constant.Region.NORTH_AMERICA, String.valueOf(mUser.getId()));
    }

    @Override
    public void initViews() {

    }


}
