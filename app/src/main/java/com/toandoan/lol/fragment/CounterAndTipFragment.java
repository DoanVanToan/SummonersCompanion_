package com.toandoan.lol.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toandoan.lol.R;
import com.toandoan.lol.model.champion.ChampionEnity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CounterAndTipFragment extends Fragment {
    private ChampionEnity championEnity;
    private TextView tvTips, tvCounterTips;
    private String allytips, enemytips;

    public static CounterAndTipFragment newInstance(ChampionEnity championEnity) {
        CounterAndTipFragment counterAndTipFragment = new CounterAndTipFragment();
        counterAndTipFragment.setChampionEnity(championEnity);
        return counterAndTipFragment;
    }


    public CounterAndTipFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_counter_and_tip, container, false);
        initViews(v);
        return v;
    }


    private void initViews(View v) {
        tvTips = (TextView) v.findViewById(R.id.tvTips);


        tvCounterTips = (TextView) v.findViewById(R.id.tvCounterTips);

        allytips = "";
        for (String str : championEnity.getAllytips()) {
            allytips += "- " + str + "\n";
        }
        tvTips.setText(allytips);

        enemytips = "";
        for (String str : championEnity.getEnemytips()) {
            enemytips += "- " + str + "\n";
        }
        tvCounterTips.setText(enemytips);


    }

    public ChampionEnity getChampionEnity() {
        return championEnity;
    }

    public void setChampionEnity(ChampionEnity championEnity) {
        this.championEnity = championEnity;
    }
}
