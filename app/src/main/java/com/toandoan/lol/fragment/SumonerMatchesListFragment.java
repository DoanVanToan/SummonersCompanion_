package com.toandoan.lol.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toandoan.lol.R;
import com.toandoan.lol.adapter.SumonerMatchesAdapter;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.SumonerEnity;
import com.toandoan.lol.model.recent_match.GameEnity;
import com.toandoan.lol.mvp_abstract.SumonerMatchesListAbstract;
import com.toandoan.lol.presenter.SumonerMatchesListPresenter;
import com.toandoan.lol.utility.Utils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SumonerMatchesListFragment extends Fragment implements SumonerMatchesListAbstract.View, SumonerMatchesAdapter.OnItemClickListenner {
    private SumonerMatchesListPresenter mPresenter;
    private SumonerEnity mUser;
    private SumonerMatchesAdapter mAdapter;
    private RecyclerView mRecyclerView;


    public SumonerMatchesListFragment() {
        // Required empty public constructor
    }

    public static SumonerMatchesListFragment newInstance(SumonerEnity user) {
        SumonerMatchesListFragment sumonerMatchesListFragment = new SumonerMatchesListFragment();
        sumonerMatchesListFragment.setUser(user);
        return sumonerMatchesListFragment;
    }

    public SumonerEnity getUser() {
        return mUser;
    }

    public void setUser(SumonerEnity user) {
        mUser = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sumoner_matches_list, container, false);
        initViews(v);
        return v;
    }


    @Override
    public void initViews(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.sumoner_match_recyclerview);
        mPresenter = new SumonerMatchesListPresenter((BaseActivity) getActivity(), this, mUser);
        String region = Utils.getRegion(getContext());
        mPresenter.getAllMatches(region, String.valueOf(mUser.getId()));
    }

    @Override
    public void updateHistoryMatches(List<GameEnity> list) {
        mAdapter = new SumonerMatchesAdapter(getContext(), list, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onItemClick(View v, int position, GameEnity participant) {
        mPresenter.onItemClickListenner(v, position, participant);
    }
}
