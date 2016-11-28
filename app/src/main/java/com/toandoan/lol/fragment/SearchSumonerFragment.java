package com.toandoan.lol.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.toandoan.lol.R;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.SumonerEnity;
import com.toandoan.lol.mvp_abstract.SearchSumonerContract;
import com.toandoan.lol.presenter.SearchSumonerPresenter;
import com.toandoan.lol.utility.Utils;
import com.toandoan.lol.widget.dialog.RegionDialog;
import com.toandoan.lol.widget.view.RobotoTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchSumonerFragment extends Fragment implements SearchSumonerContract.View
        , EditText.OnEditorActionListener {

    @BindView(R.id.search_sumoner_editext)
    EditText mSearchSumonerEditext;
    @BindView(R.id.region_textview)
    RobotoTextView mRegionTextview;
    @BindView(R.id.search_image)
    ImageView mSearchImage;
    @BindView(R.id.sumoner_recycler_view)
    RecyclerView mSumonerRecyclerView;

    private SearchSumonerPresenter mPresenter;
    private RegionDialog mRegionDialog;

    public static SearchSumonerFragment newInstance() {
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
        initViews();
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initViews() {
        mPresenter = new SearchSumonerPresenter((BaseActivity) getActivity(), this);
        mSearchSumonerEditext.clearFocus();
        Utils.hidenKeyboard((Activity) getContext());
        mSearchSumonerEditext.setOnEditorActionListener(this);
        mRegionTextview.setText(Utils.getRegion(getContext()));
        mRegionDialog = new RegionDialog(getContext(), new RegionDialog.OnItemClickListenner() {
            @Override
            public void onItemClick(int position, String code) {
                PreferenceManager.getDefaultSharedPreferences(getContext())
                        .edit()
                        .putString(Constant.SharePreference.PRE_REGION, code)
                        .commit();
                mRegionTextview.setText(code);
            }
        });
    }

    @Override
    public void updateUISumoner(SumonerEnity sumonerEnity) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (mSearchSumonerEditext.getText().toString().length() > 0) {
                String region = Utils.getRegion(getContext());
                mPresenter.searchSumonerByName(region, mSearchSumonerEditext.getText().toString());
            }
            return true;
        }
        return false;
    }

    @OnClick({R.id.region_textview, R.id.search_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.region_textview:
                mRegionDialog.show();
                break;
            case R.id.search_image:
                if (mSearchSumonerEditext.getText().toString().length() > 0) {
                    String region = Utils.getRegion(getContext());
                    mPresenter.searchSumonerByName(region, mSearchSumonerEditext.getText().toString());
                }
                break;
        }
    }
}
