package com.toandoan.lol.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toandoan.lol.R;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.SumonerEnity;
import com.toandoan.lol.model.sumoner_overview.LeagueEnity;
import com.toandoan.lol.mvp_abstract.SumonerOverviewContract;
import com.toandoan.lol.presenter.SumonerOverviewPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SumonerOverviewFragment extends Fragment implements SumonerOverviewContract.View {
    private Context mContext;
    private SumonerEnity mSumonerEnity;
    private SumonerOverviewPresenter mPresenter;

    public void setSumonerEnity(SumonerEnity sumonerEnity) {
        mSumonerEnity = sumonerEnity;
    }

    public static SumonerOverviewFragment newInstance(SumonerEnity sumonerEnity) {
        SumonerOverviewFragment userOverviewFragment = new SumonerOverviewFragment();
        userOverviewFragment.setSumonerEnity(sumonerEnity);
        return userOverviewFragment;
    }

    public SumonerOverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_overview, container, false);
        initViews(view);
        return view;
    }


    private void initViews(View v) {
        mContext = getContext();
        mPresenter = new SumonerOverviewPresenter((BaseActivity) mContext, this);
        mPresenter.loadSumonerStats(Constant.Region.NORTH_AMERICA, String.valueOf(mSumonerEnity.getId()));
    }

    @Override
    public void loadSumonerStats(LeagueEnity leagueEnity) {

    }
}
