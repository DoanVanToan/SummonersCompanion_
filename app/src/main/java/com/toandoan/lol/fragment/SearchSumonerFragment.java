package com.toandoan.lol.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.toandoan.lol.R;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.base.BaseFragment;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.SumonerEnity;
import com.toandoan.lol.mvp_abstract.SearchSumonerContract;
import com.toandoan.lol.presenter.SearchSumonerPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchSumonerFragment extends BaseFragment implements SearchSumonerContract.View {
    @BindView(R.id.sumoner_1)
    Button mSumoner1;
    @BindView(R.id.sumoner_2)
    Button mSumoner2;
    @BindView(R.id.sumoner_3)
    Button mSumoner3;
    @BindView(R.id.sumoner_4)
    Button mSumoner4;
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
        ButterKnife.bind(this, view);
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

    @OnClick({R.id.sumoner_1, R.id.sumoner_2, R.id.sumoner_3, R.id.sumoner_4})
    public void onClick(View view) {
        Button btn = (Button) view;
        mPresenter.searchSumonerByName(Constant.Region.NORTH_AMERICA, btn.getText().toString());

    }
}
