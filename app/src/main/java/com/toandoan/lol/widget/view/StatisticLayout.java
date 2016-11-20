package com.toandoan.lol.widget.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.toandoan.lol.R;
import com.toandoan.lol.model.match_detail.ParticipantStats;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ToanDoan on 11/20/2016.
 */

public class StatisticLayout extends LinearLayout {
    @BindView(R.id.dame_dealt)
    RobotoTextView mDameDealt;
    @BindView(R.id.damage_taken)
    RobotoTextView mDamageTaken;
    @BindView(R.id.gold_earned)
    RobotoTextView mGoldEarned;
    @BindView(R.id.healing_done)
    RobotoTextView mHealingDone;
    @BindView(R.id.largest_killing_spree)
    RobotoTextView mLargestKillingSpree;
    @BindView(R.id.largest_multi_kill)
    RobotoTextView mLargestMultiKill;
    @BindView(R.id.magic_dame_dealt)
    RobotoTextView mMagicDameDealt;
    @BindView(R.id.physical_damage_dealt)
    RobotoTextView mPhysicalDamageDealt;
    @BindView(R.id.minions_kill)
    RobotoTextView mMinionsKill;
    @BindView(R.id.neatural_minions_killed)
    RobotoTextView mNeaturalMinionsKilled;
    @BindView(R.id.magic_dame_taken)
    RobotoTextView mMagicDameTaken;
    @BindView(R.id.physic_damge_taken)
    RobotoTextView mPhysicDamgeTaken;
    @BindView(R.id.crowd_control_dealt)
    RobotoTextView mCrowdControlDealt;
    @BindView(R.id.inhibitors_destroyed)
    RobotoTextView mInhibitorsDestroyed;
    @BindView(R.id.wards_placed)
    RobotoTextView mWardsPlaced;
    @BindView(R.id.penta_kill)
    RobotoTextView mPentaKill;
    @BindView(R.id.turrets_destroyed)
    RobotoTextView mTurretsDestroyed;
    private ParticipantStats mStats;

    public StatisticLayout(Context context) {
        super(context);
    }

    public StatisticLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StatisticLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StatisticLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.statistic_layout, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setStats(ParticipantStats stats) {
        this.mStats = stats;
        bindStats();
    }

    public void bindStats() {
        mDameDealt.setText(String.valueOf(mStats.getTotalDamageDealt()));
        mDamageTaken.setText(String.valueOf(mStats.getTotalDamageTaken()));
        mGoldEarned.setText(String.valueOf(mStats.getGoldEarned()));
        mHealingDone.setText(String.valueOf(mStats.getTotalHeal()));
        mLargestKillingSpree.setText(String.valueOf(mStats.getLargestKillingSpree()));
        mLargestMultiKill.setText(String.valueOf(mStats.getLargestMultiKill()));
        mMagicDameDealt.setText(String.valueOf(mStats.getMagicDamageDealt()));
        mPhysicalDamageDealt.setText(String.valueOf(mStats.getPhysicalDamageDealt()));
        mMinionsKill.setText(String.valueOf(mStats.getMinionsKilled()));
        mNeaturalMinionsKilled.setText(String.valueOf(mStats.getNeutralMinionsKilled()));
        mMagicDameTaken.setText(String.valueOf(mStats.getMagicDamageTaken()));
        mPhysicDamgeTaken.setText(String.valueOf(mStats.getPhysicalDamageTaken()));
        mCrowdControlDealt.setText(String.valueOf(mStats.getTotalTimeCrowdControlDealt()));
        mInhibitorsDestroyed.setText(String.valueOf(mStats.getInhibitorKills()));
        mWardsPlaced.setText(String.valueOf(mStats.getWardsPlaced()));
        mPentaKill.setText(String.valueOf(mStats.getPentaKills()));
        mTurretsDestroyed.setText(String.valueOf(mStats.getTowerKills()));

    }
}
