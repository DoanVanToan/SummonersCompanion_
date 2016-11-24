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
import com.toandoan.lol.model.SumonerEnity;
import com.toandoan.lol.mvp_abstract.SearchSumonerContract;
import com.toandoan.lol.presenter.SearchSumonerPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchSumonerFragment extends BaseFragment implements SearchSumonerContract.View {
    private SearchSumonerPresenter mPresenter;

    public static final SearchSumonerFragment newInstance() {
        return new SearchSumonerFragment();
    }


    public SearchSumonerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_sumoner, container, false);
        mPresenter = new SearchSumonerPresenter((BaseActivity) getActivity(), this);
        return view;

    }

    @Override
    public void updateUISumoner(SumonerEnity sumonerEnity) {

    }

    @Override
    public void onSearch(String key) {

    }

    @Override
    public void onSearchSubmit(String key) {
        super.onSearchSubmit(key);
        mPresenter.searchSumonerByName(Constant.Region.NORTH_AMERICA, key);
    }
}
