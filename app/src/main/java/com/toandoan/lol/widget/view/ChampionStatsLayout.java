package com.toandoan.lol.widget.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toandoan.lol.R;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.model.champion_by_season.ChampionStatsEnity;
import com.toandoan.lol.utility.FileOperations;
import com.toandoan.lol.utility.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ToanDoan on 11/20/2016.
 */

public class ChampionStatsLayout extends LinearLayout implements View.OnClickListener {
    @BindView(R.id.penta_kill)
    RobotoTextView pentaKill;
    @BindView(R.id.quadra_kills)
    RobotoTextView quadraKills;
    @BindView(R.id.triple_kills)
    RobotoTextView tripleKills;
    @BindView(R.id.double_kills)
    RobotoTextView doubleKills;
    @BindView(R.id.kills)
    RobotoTextView kills;
    @BindView(R.id.deaths)
    RobotoTextView deaths;
    @BindView(R.id.assists)
    RobotoTextView assists;
    @BindView(R.id.minions_kill)
    RobotoTextView minionsKill;
    @BindView(R.id.neatural_minions_killed)
    RobotoTextView neaturalMinionsKilled;
    @BindView(R.id.most_dealt)
    RobotoTextView mostDealt;
    @BindView(R.id.most_kill)
    RobotoTextView mostKill;
    @BindView(R.id.gold_earned)
    RobotoTextView goldEarned;
    @BindView(R.id.dame_dealt)
    RobotoTextView dameDealt;
    @BindView(R.id.physical_damage_dealt)
    RobotoTextView physicalDamageDealt;
    @BindView(R.id.magic_dame_dealt)
    RobotoTextView magicDameDealt;
    @BindView(R.id.healing_done)
    RobotoTextView healingDone;
    @BindView(R.id.damage_taken)
    RobotoTextView damageTaken;
    @BindView(R.id.champion_avatar)
    SquareImageView championAvatar;
    @BindView(R.id.kda_title)
    RobotoTextView kdaTitle;
    @BindView(R.id.win_lose_title)
    RobotoTextView winLoseTitle;
    @BindView(R.id.gold_earned_title)
    RobotoTextView goldEarnedTitle;
    @BindView(R.id.minion_kill_title)
    RobotoTextView minionKillTitle;
    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.robotoTextView)
    RobotoTextView robotoTextView;
    @BindView(R.id.stats_layout)
    LinearLayout statsLayout;


    private boolean isCollap = true;
    private ChampionStatsEnity mChampionStats;
    private List<ChampionEnity> mChampions;

    public ChampionStatsLayout(Context context) {
        super(context);
        init();
    }

    public ChampionStatsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChampionStatsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ChampionStatsLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.champions_statistic_view, this);
        ButterKnife.bind(this);
        loadChampions();
        setOnClickListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setStatsLayout() {
        if (isCollap) {
            statsLayout.setVisibility(GONE);
            ivTitle.setImageResource(R.drawable.ic_up);
        } else {
            statsLayout.setVisibility(VISIBLE);
            ivTitle.setImageResource(R.drawable.ic_down);
        }
    }

    @Override
    public void onClick(View v) {
        isCollap = !isCollap;
        setStatsLayout();
    }

    public void setChampionStats(ChampionStatsEnity championStats) {
        this.mChampionStats = championStats;
    }

    public void bindData(ChampionStatsEnity championStats) {
        this.setChampionStats(championStats);
        pentaKill.setText(Utils.formatDouble(mChampionStats.getStats().getTotalPentaKills()));
        quadraKills.setText(Utils.formatDouble(mChampionStats.getStats().getTotalQuadraKills()));
        tripleKills.setText(Utils.formatDouble(mChampionStats.getStats().getTotalTripleKills()));
        doubleKills.setText(Utils.formatDouble(mChampionStats.getStats().getTotalDoubleKills()));
        kills.setText(Utils.formatDouble(mChampionStats.getStats().getTotalChampionKills()));
        deaths.setText(Utils.formatDouble(mChampionStats.getStats().getTotalDeathsPerSession()));
        assists.setText(Utils.formatDouble(mChampionStats.getStats().getTotalAssists()));
        minionKillTitle.setText(Utils.formatDouble(mChampionStats.getStats().getTotalMinionKills()));
        neaturalMinionsKilled.setText(Utils.formatDouble(mChampionStats.getStats().getTotalNeutralMinionsKilled()));
        mostDealt.setText(Utils.formatDouble(mChampionStats.getStats().getMaxNumDeaths()));
        mostKill.setText(Utils.formatDouble(mChampionStats.getStats().getAverageChampionsKilled()));
        goldEarned.setText(Utils.formatDouble(mChampionStats.getStats().getTotalGoldEarned()));
        dameDealt.setText(Utils.formatDouble(mChampionStats.getStats().getTotalDamageDealt()));
        physicalDamageDealt.setText(Utils.formatDouble(mChampionStats.getStats().getTotalPhysicalDamageDealt()));
        magicDameDealt.setText(Utils.formatDouble(mChampionStats.getStats().getTotalMagicDamageDealt()));
        healingDone.setText(Utils.formatDouble(mChampionStats.getStats().getTotalHeal()));
        damageTaken.setText(Utils.formatDouble(mChampionStats.getStats().getTotalDamageTaken()));
        StringBuilder builder = new StringBuilder();
        builder
                .append(getResources().getString(R.string.wins))
                .append(Constant.Charactor.MOD)
                .append(Constant.Charactor.SPACE)
                .append(mChampionStats.getStats().getTotalSessionsWon())
                .append(Constant.Charactor.DIV)
                .append(mChampionStats.getStats().getTotalSessionsPlayed());

        winLoseTitle.setText(builder);
        goldEarnedTitle.setText(Utils.formatDouble(mChampionStats.getStats().getTotalGoldEarned()
                / mChampionStats.getStats().getTotalSessionsPlayed()));
        minionKillTitle.setText(Utils.formatDouble(mChampionStats.getStats().getTotalMinionKills()
                / mChampionStats.getStats().getTotalSessionsPlayed()));

        builder = new StringBuilder("");
        builder
                .append(mChampionStats.getStats().getTotalChampionKills() / mChampionStats.getStats().getTotalSessionsPlayed())
                .append(Constant.Charactor.DIV)
                .append(mChampionStats.getStats().getTotalDeathsPerSession() / mChampionStats.getStats().getTotalSessionsPlayed())
                .append(Constant.Charactor.DIV)
                .append(mChampionStats.getStats().getTotalAssists() / mChampionStats.getStats().getTotalSessionsPlayed());
        kdaTitle.setText(builder);
        ChampionEnity championEnity = getChampionByID(String.valueOf(mChampionStats.getId()));
        if (championEnity != null) {
            Glide.with(getContext())
                    .load(Utils.RiotStatic.getChampionIcon(championEnity.getKey()))
                    .into(championAvatar);
        }
    }


    public void loadChampions() {
        String fullChampStr = new FileOperations(getContext()).readData(Constant.Data.FULL_CHAMP_LIST);
        if (fullChampStr != null) {
            Type listType = new TypeToken<ArrayList<ChampionEnity>>() {
            }.getType();
            mChampions = new Gson().fromJson(fullChampStr, listType);
        }
    }

    public ChampionEnity getChampionByID(String id) {
        for (ChampionEnity championEnity : mChampions) {
            if (championEnity.getId().equalsIgnoreCase(id)) {
                return championEnity;
            }
        }
        return null;
    }
}
