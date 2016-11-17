package com.toandoan.lol.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toandoan.lol.R;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.base.BaseFragment;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.UserEnity;
import com.toandoan.lol.mvp_abstract.SumonerMasteriesAbstract;
import com.toandoan.lol.mvp_abstract.SumonerMatchesListAbstract;
import com.toandoan.lol.presenter.SumonerMatchesListPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SumonerMatchesListFragment extends Fragment implements SumonerMatchesListAbstract.View {
    private SumonerMatchesListPresenter mPresenter;
    private UserEnity mUser;


    public SumonerMatchesListFragment() {
        // Required empty public constructor
    }

    public static SumonerMatchesListFragment newInstance(UserEnity user) {
        SumonerMatchesListFragment sumonerMatchesListFragment = new SumonerMatchesListFragment();
        sumonerMatchesListFragment.setUser(user);
        return sumonerMatchesListFragment;
    }

    public UserEnity getUser() {
        return mUser;
    }

    public void setUser(UserEnity user) {
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
        mPresenter = new SumonerMatchesListPresenter((BaseActivity) getActivity(), this);
        mPresenter.getAllMatches(Constant.Region.NORTH_AMERICA, String.valueOf(mUser.getId()));
    }


}
