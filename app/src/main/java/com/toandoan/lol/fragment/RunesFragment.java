package com.toandoan.lol.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toandoan.lol.R;
import com.toandoan.lol.adapter.RunesAdapter;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.base.BaseFragment;
import com.toandoan.lol.presenter.RunesPresenter;
import com.toandoan.lol.listenner.RuneListenner;
import com.toandoan.lol.model.rune.RuneEnity;
import com.toandoan.lol.utility.LogUtil;
import com.toandoan.lol.utility.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RunesFragment extends BaseFragment {
    private RunesPresenter mHelper;
    private BaseActivity mBaseActivity;
    private final String TAG = "RunesFragment";
    private RecyclerView mReyclerView;
    private RunesAdapter mAdapter;
    private List<RuneEnity> mRunes;

    public static RunesFragment newInstance() {
        RunesFragment runesFragment = new RunesFragment();
        return runesFragment;
    }

    public RunesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_runes, container, false);
        initViews(v);
        return v;
    }

    private void initViews(View v) {
        mReyclerView = (RecyclerView) v.findViewById(R.id.mReyclerView);
        mBaseActivity = (BaseActivity) getActivity();
        mHelper = new RunesPresenter(mBaseActivity, mListenner);
        mHelper.getALlRunesFromDatabase();
    }

    private RuneListenner mListenner = new RuneListenner() {
        @Override
        public void getAllRunesSuccessful(List<RuneEnity> listData) {
            LogUtil.e(TAG, listData.size() + "");
            mRunes = new ArrayList<>(listData);
            mAdapter = new RunesAdapter(getContext(), mRunes);
            mReyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mReyclerView.setAdapter(mAdapter);
        }

        @Override
        public void getAllRunesFail(String message) {
            Utils.show(mBaseActivity, message);
        }
    };


    @Override
    public void onSearch(String key) {
        Utils.show(getContext(), TAG + key);
    }


}
