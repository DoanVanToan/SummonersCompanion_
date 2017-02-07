package com.toandoan.lol.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.toandoan.lol.R;
import com.toandoan.lol.adapter.MasteriesPagerAdapter;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.presenter.MasteriesPresenter;
import com.toandoan.lol.listenner.MasteriesListenner;
import com.toandoan.lol.model.matery.MasteryEnity;
import com.toandoan.lol.utility.Utils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasteriesFragment extends Fragment {
    private MasteriesPresenter mHelper;
    private BaseActivity mActivity;
    private LinearLayout mRootView;
    private ViewPager mMasteryViewPager;
    private MasteriesPagerAdapter mAdapter;
    private TabLayout mTitleTabLayout;
    private final int DELAY_TIME = 4000;


    public static MasteriesFragment newInstance() {
        MasteriesFragment masteriesFragment = new MasteriesFragment();
        return masteriesFragment;
    }

    public MasteriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = (LinearLayout) inflater.inflate(R.layout.fragment_masteries, container, false);
        initView(mRootView);
        return mRootView;
    }

    private void initView(View v) {
        mActivity = (BaseActivity) getActivity();
        mMasteryViewPager = (ViewPager) v.findViewById(R.id.viewpager_mastery);
        mTitleTabLayout = (TabLayout) v.findViewById(R.id.tablayout_title);
        mHelper = new MasteriesPresenter(mActivity, mListenner);
        mHelper.getALlMasteriesFromDatabase();

    }


    private MasteriesListenner mListenner = new MasteriesListenner() {
        @Override
        public void getAllMasteriesSuccessful(List<MasteryEnity> listData) {
            mAdapter = new MasteriesPagerAdapter(getContext(), listData, mListenner);
            mMasteryViewPager.setAdapter(mAdapter);
            mTitleTabLayout.setupWithViewPager(mMasteryViewPager);
        }

        @Override
        public void getAllMasteriesFail(String message) {

        }

        @Override
        public void onItemClickListenner(MasteryEnity masteryEnity) {
            Utils.showMasteryDialog(getActivity(), masteryEnity);
        }

    };

}
