package com.toandoan.lol.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toandoan.lol.R;
import com.toandoan.lol.activity.ChampionDetailActivity;
import com.toandoan.lol.adapter.SelectChampionAdapter;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.base.BaseFragment;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.presenter.ChampionPresenter;
import com.toandoan.lol.listenner.ISelectChampListenner;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.utility.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectChampionFragment extends BaseFragment {
    private final String TAG = "SelectChampionFragment";
    private Context mContext;
    private RecyclerView rvChamps;
    private ChampionPresenter helper;
    private SelectChampionAdapter adapter;
    private List<ChampionEnity> champions;
    private List<ChampionEnity> searchChampions;

    public static SelectChampionFragment newInstance() {
        SelectChampionFragment selectChampionFragment = new SelectChampionFragment();
        return selectChampionFragment;
    }

    public SelectChampionFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_champion, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View v) {
        rvChamps = (RecyclerView) v.findViewById(R.id.rvChamps);
        mContext = getContext();

        helper = new ChampionPresenter((BaseActivity) getActivity(), listenner);
        helper.loadCacheData();
    }

    /**
     * Listenner
     */
    private ISelectChampListenner listenner = new ISelectChampListenner() {
        @Override
        public void getAllChampionSuccess(List<ChampionEnity> fullChampions) {
            if (fullChampions != null) {
                champions = new ArrayList<>(fullChampions);
                adapter = new SelectChampionAdapter(mContext, iSelectChampListenner, champions);
                rvChamps.setLayoutManager(new GridLayoutManager(mContext, 4));
                rvChamps.setAdapter(adapter);
                ((BaseActivity) getActivity()).dismissDialog();
            }
        }
    };


    private SelectChampionAdapter.IChampionSelectListenner iSelectChampListenner = new SelectChampionAdapter.IChampionSelectListenner() {

        @Override
        public void onItemClickListenner(ChampionEnity championEnity, int position) {
            Intent intent = new Intent(getActivity(), ChampionDetailActivity.class);
            intent.putExtra(Constant.IntentKey.CHAMPION_ENITY, championEnity);
            getActivity().startActivity(intent);
            Utils.hidenKeyboard(getActivity());
        }
    };


    @Override
    public void onSearch(String key) {
        if (key == null || key.length() == 0) {
            adapter.updateViews(champions);
            return;
        }

        searchChampions = new ArrayList<>();
        for (ChampionEnity championEnity : champions) {
            if (championEnity.getName().toLowerCase().contains(key.toLowerCase()))
                searchChampions.add(championEnity);
        }

        adapter.updateViews(searchChampions);
    }

}
