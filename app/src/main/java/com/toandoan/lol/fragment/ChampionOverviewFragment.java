package com.toandoan.lol.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toandoan.lol.R;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.model.champion.ChampionStats;
import com.toandoan.lol.widget.view.ProgressView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChampionOverviewFragment extends Fragment {
    private Context mContext;
    private TextView tvLore;
    private TextView tvAcctack, tvDefence, tvAbility, tvDificulty;
    private ProgressView pvAcctack, pvDefence, pvAbility, pvDificulty;
    private TextView tvHealthValue, tvEneryValue, tvAttackValue, tvArmorValue, tvRange, tvHealthRegenValue, tvManaRegenValue, tvAttackSpeechValue, tvMagicResistValue, tvManaMovementValue;

    private TextView tvEnergy, tvManaRegen;
    private ChampionEnity championEnity;


    public ChampionOverviewFragment() {
        // Required empty public constructor
    }

    public static ChampionOverviewFragment newInstance(ChampionEnity championEnity) {
        ChampionOverviewFragment championOverviewFragment = new ChampionOverviewFragment();
        championOverviewFragment.setChampionEnity(championEnity);
        return championOverviewFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_champion_overview, container, false);
        initView(view);
        return view;
    }

    private void initView(View v) {
        tvLore = (TextView) v.findViewById(R.id.tvLore);
        pvAcctack = (ProgressView) v.findViewById(R.id.pvAcctack);
        pvDefence = (ProgressView) v.findViewById(R.id.pvDefence);
        pvAbility = (ProgressView) v.findViewById(R.id.pvAbility);
        pvDificulty = (ProgressView) v.findViewById(R.id.pvDificulty);

        tvAcctack = (TextView) v.findViewById(R.id.tvAcctack);
        tvDefence = (TextView) v.findViewById(R.id.tvDefence);
        tvAbility = (TextView) v.findViewById(R.id.tvAbility);
        tvDificulty = (TextView) v.findViewById(R.id.tvDificulty);

        /** Stats
         *
         */

        tvHealthValue = (TextView) v.findViewById(R.id.tvHealthValue);
        tvEneryValue = (TextView) v.findViewById(R.id.tvEneryValue);
        tvAttackValue = (TextView) v.findViewById(R.id.tvAttackValue);
        tvArmorValue = (TextView) v.findViewById(R.id.tvArmorValue);
        tvRange = (TextView) v.findViewById(R.id.tvRange);
        tvHealthRegenValue = (TextView) v.findViewById(R.id.tvHealthRegenValue);
        tvManaRegenValue = (TextView) v.findViewById(R.id.tvManaRegenValue);
        tvAttackSpeechValue = (TextView) v.findViewById(R.id.tvAttackSpeechValue);
        tvMagicResistValue = (TextView) v.findViewById(R.id.tvMagicResistValue);
        tvManaMovementValue = (TextView) v.findViewById(R.id.tvManaMovementValue);
        tvEnergy = (TextView) v.findViewById(R.id.tvEnergy);
        tvManaRegen = (TextView) v.findViewById(R.id.tvManaRegen);


        mContext = getContext();
        if (championEnity == null) {
            return;
        }

        tvLore.setText(Html.fromHtml(championEnity.getLore()));
        pvAcctack.setProgress(championEnity.getInfo().getAttack());
        tvAcctack.setText(championEnity.getInfo().getAttack() + "");

        pvDefence.setProgress(championEnity.getInfo().getDefense());
        tvDefence.setText(championEnity.getInfo().getDefense() + "");

        pvAbility.setProgress(championEnity.getInfo().getMagic());
        tvAbility.setText(championEnity.getInfo().getMagic() + "");

        pvDificulty.setProgress(championEnity.getInfo().getDifficulty());
        tvDificulty.setText(championEnity.getInfo().getDifficulty() + "");

        fillDataStatsUI(championEnity.getStats());

    }

    private void fillDataStatsUI(ChampionStats stats) {
        tvHealthValue.setText(stats.getHp() + " (" + stats.getHpperlevel() + ")");
        tvHealthRegenValue.setText(stats.getHpregen() + " (" + stats.getHpperlevel() + ")");
        tvAttackValue.setText(stats.getAttackdamage() + " (" + stats.getAttackdamageperlevel() + ")");
        tvAttackSpeechValue.setText(stats.getAttackspeedoffset() + " (" + stats.getAttackspeedperlevel() + "%)");
        tvArmorValue.setText(stats.getArmor() + " (" + stats.getArmorperlevel() + ")");
        tvManaMovementValue.setText(stats.getMovespeed() + "");
        tvRange.setText(stats.getAttackrange() + "");
        tvMagicResistValue.setText(stats.getMp() + " (" + stats.getMpperlevel() + ")");
    }

    public ChampionEnity getChampionEnity() {
        return championEnity;
    }

    public void setChampionEnity(ChampionEnity championEnity) {
        this.championEnity = championEnity;
    }

}
