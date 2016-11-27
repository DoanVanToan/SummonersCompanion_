package com.toandoan.lol.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.toandoan.lol.R;
import com.toandoan.lol.activity.ChallengerActivity;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.SumonerEnity;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.model.champion_by_season.ChampionStatsEnity;
import com.toandoan.lol.model.sumoner_overview.LeagueEnity;
import com.toandoan.lol.model.sumoner_sumary.PlayerStatsSummaryEnity;
import com.toandoan.lol.mvp_abstract.SumonerOverviewContract;
import com.toandoan.lol.presenter.SumonerOverviewPresenter;
import com.toandoan.lol.utility.Utils;
import com.toandoan.lol.widget.view.RobotoTextView;
import com.toandoan.lol.widget.view.SquareImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SumonerOverviewFragment extends Fragment implements SumonerOverviewContract.View {
    @BindView(R.id.sumonerAvatr)
    SquareImageView sumonerAvatr;
    @BindView(R.id.sumoner_name)
    RobotoTextView sumonerName;
    @BindView(R.id.sumnoner_level)
    RobotoTextView sumnonerLevel;
    @BindView(R.id.sumoner_kda)
    RobotoTextView sumonerKda;
    @BindView(R.id.sumoner_take_down)
    RobotoTextView sumonerTakeDown;
    @BindView(R.id.sumoner_minions)
    RobotoTextView sumonerMinions;
    @BindView(R.id.sumoner_winrate)
    RobotoTextView sumonerWinrate;
    @BindView(R.id.robotoTextView4)
    RobotoTextView robotoTextView4;
    @BindView(R.id.most_play_image_1)
    SquareImageView mostPlayImage1;
    @BindView(R.id.most_play_kda_1)
    RobotoTextView mostPlayKda1;
    @BindView(R.id.most_play_win_1)
    RobotoTextView mostPlayWin1;
    @BindView(R.id.most_play_image_2)
    SquareImageView mostPlayImage2;
    @BindView(R.id.most_play_kda_2)
    RobotoTextView mostPlayKda2;
    @BindView(R.id.most_play_win_2)
    RobotoTextView mostPlayWin2;
    @BindView(R.id.most_play_image_3)
    SquareImageView mostPlayImage3;
    @BindView(R.id.most_play_kda_3)
    RobotoTextView mostPlayKda3;
    @BindView(R.id.most_play_win_3)
    RobotoTextView mostPlayWin3;
    @BindView(R.id.most_play_image_4)
    SquareImageView mostPlayImage4;
    @BindView(R.id.most_play_kda_4)
    RobotoTextView mostPlayKda4;
    @BindView(R.id.most_play_win_4)
    RobotoTextView mostPlayWin4;
    @BindView(R.id.rank_solo_image)
    SquareImageView rankSoloImage;
    @BindView(R.id.rank_solo_title)
    RobotoTextView rankSoloTitle;
    @BindView(R.id.rank_solo_point)
    RobotoTextView rankSoloPoint;
    @BindView(R.id.rank_solo_win)
    RobotoTextView rankSoloWin;
    @BindView(R.id.rank_5v5_image)
    SquareImageView rank5v5Image;
    @BindView(R.id.rank_5v5_title)
    RobotoTextView rank5v5Title;
    @BindView(R.id.rank_3v3_image)
    SquareImageView rank3v3Image;
    @BindView(R.id.rank_3v3_title)
    RobotoTextView rank3v3Title;
    @BindView(R.id.rank_5v5_point)
    RobotoTextView rank5v5Point;
    @BindView(R.id.rank_5v5_name)
    RobotoTextView rank5v5Name;
    @BindView(R.id.rank_5v5_subtitle)
    LinearLayout rank5v5Subtitle;
    @BindView(R.id.rank_3v3_point)
    RobotoTextView rank3v3Point;
    @BindView(R.id.rank_3v3_name)
    RobotoTextView rank3v3Name;
    @BindView(R.id.rank_3v3_subtitle)
    LinearLayout rank3v3Subtitle;

    private SumonerEnity mSumonerEnity;
    private SumonerOverviewPresenter mPresenter;
    private Context mContext;
    private final int MAX_CHAMPION_REVIEWS = 5;

    public void setSumonerEnity(SumonerEnity sumonerEnity) {
        mSumonerEnity = sumonerEnity;
    }

    public static SumonerOverviewFragment newInstance(SumonerEnity sumonerEnity) {
        SumonerOverviewFragment userOverviewFragment = new SumonerOverviewFragment();
        userOverviewFragment.setSumonerEnity(sumonerEnity);
        return userOverviewFragment;
    }

    public SumonerOverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_overview, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }


    private void initViews() {
        mContext = getContext();
        mPresenter = new SumonerOverviewPresenter((BaseActivity) mContext, this);
        mPresenter.loadSumonerStats(Constant.Region.NORTH_AMERICA, String.valueOf(mSumonerEnity.getId()));
        mPresenter.loadSumonerSumary(Constant.Region.NORTH_AMERICA, String.valueOf(mSumonerEnity.getId()));
    }

    @Override
    public void updateSumonerStats(List<LeagueEnity> leagueEnity) {
        updateSumoner(mSumonerEnity);
        for (LeagueEnity league : leagueEnity) {
            switch (league.getQueue()) {
                case Constant.SumonerStaticData.RANKED_SOLO_5X5:
                    rankSoloTitle.setText(league.getTier() + Constant.Charactor.SPACE + league.getEntries().get(0).getDivision());
                    rankSoloPoint.setText(league.getEntries().get(0).getLeaguePoints() + Constant.Charactor.SPACE + Constant.SumonerStaticData.POINT);
                    rankSoloWin.setText(mContext.getString(R.string.wins_lose)
                            + Constant.Charactor.MOD
                            + Constant.Charactor.SPACE
                            + league.getEntries().get(0).getWins()
                            + Constant.Charactor.DIV
                            + Constant.Charactor.SPACE
                            + league.getEntries().get(0).getLosses());
                    String fileName = league.getTier() + "_" + league.getEntries().get(0).getDivision() + ".png";
                    Utils.loadImageAssets(mContext, rankSoloImage, fileName);
                    break;

                case Constant.SumonerStaticData.RANKED_TEAM_3X3:
                    rank3v3Title.setText(league.getTier() + Constant.Charactor.SPACE + league.getEntries().get(0).getDivision());
                    rank3v3Subtitle.setVisibility(View.VISIBLE);
                    rank3v3Point.setText(league.getEntries().get(0).getLeaguePoints() + Constant.Charactor.SPACE + Constant.SumonerStaticData.POINT);
                    rank3v3Name.setText(league.getName());
                    fileName = league.getTier() + "_" + league.getEntries().get(0).getDivision() + ".png";
                    Utils.loadImageAssets(mContext, rank3v3Image, fileName);
                    break;


                case Constant.SumonerStaticData.RANKED_TEAM_5X5:
                    rank5v5Title.setText(league.getTier() + Constant.Charactor.SPACE + league.getEntries().get(0).getDivision());
                    fileName = league.getTier() + "_" + league.getEntries().get(0).getDivision() + ".png";
                    Utils.loadImageAssets(mContext, rank5v5Image, fileName);
                    rank5v5Subtitle.setVisibility(View.VISIBLE);
                    rank5v5Point.setText(league.getEntries().get(0).getLeaguePoints() + Constant.Charactor.SPACE + Constant.SumonerStaticData.POINT);
                    rank5v5Name.setText(league.getName());
                    break;
            }
        }
    }

    @Override
    public void updateMostChampionPlayed(List<ChampionStatsEnity> champions) {
        for (int i = 0; i < MAX_CHAMPION_REVIEWS; i++) {
            if (i < champions.size()) {
                ChampionStatsEnity champion = champions.get(i);
                switch (i) {
                    case 0:
                        loadMostPlay(champion, mostPlayImage1, mostPlayKda1, mostPlayWin1);
                        break;
                    case 1:
                        loadMostPlay(champion, mostPlayImage2, mostPlayKda2, mostPlayWin2);
                        break;
                    case 2:
                        loadMostPlay(champion, mostPlayImage3, mostPlayKda3, mostPlayWin3);
                        break;
                    case 3:
                        loadMostPlay(champion, mostPlayImage4, mostPlayKda4, mostPlayWin4);
                        break;
                }
            }
        }
    }

    @Override
    public void updateSumonerSumary(PlayerStatsSummaryEnity stats) {
        int totalGame = stats.getWins() + stats.getLosses();
        if (totalGame == 0) totalGame = 1;
        int winPercent = 0;
        winPercent = (stats.getWins() * 100 / totalGame);
        sumonerWinrate.setText(winPercent + Constant.Charactor.SPACE + Constant.Charactor.PERCENT);

        float turret = stats.getAggregatedStats().getTotalTurretsKilled();
        float totalGameFloat = totalGame;
        turret /= totalGameFloat;
        int minions = (stats.getAggregatedStats().getTotalMinionKills() + stats.getAggregatedStats().getTotalNeutralMinionsKilled())
                / totalGame;
        sumonerMinions.setText(minions + "");
        sumonerTakeDown.setText(String.format("%.2f", turret));

        StringBuilder builder = new StringBuilder()
                .append(stats.getAggregatedStats().getTotalChampionKills() / totalGame)
                .append(Constant.Charactor.DIV)
                .append(stats.getAggregatedStats().getTotalAssists() / totalGame)
                .append(Constant.Charactor.DIV)
                .append(stats.getAggregatedStats().getTotalDeathsPerSession() / totalGame);
        sumonerKda.setText(builder);
    }

    @Override
    public void updateSumoner(SumonerEnity sumoner) {
        mSumonerEnity = sumoner;
        Glide.with(mContext)
                .load(Utils.RiotStatic.getProfileIcon(mSumonerEnity.getProfileIconId()))
                .into(sumonerAvatr);
        sumonerName.setText(mSumonerEnity.getName());
        sumnonerLevel.setText(Constant.Charactor.LEVEL
                + Constant.Charactor.MOD
                + Constant.Charactor.SPACE
                + mSumonerEnity.getSummonerLevel());
    }

    private void loadMostPlay(ChampionStatsEnity championStats, ImageView championAvt, TextView tvKDA, TextView tvWinPercent) {
        ChampionEnity champion = Utils.getChampionByID(mContext, String.valueOf(championStats.getId()));
        if (champion != null) {
            Glide.with(mContext)
                    .load(Utils.RiotStatic.getChampionIcon(champion.getKey()))
                    .into(championAvt);
        }
        StringBuilder builder = new StringBuilder();
        builder
                .append(championStats.getStats().getTotalChampionKills() / championStats.getStats().getTotalSessionsPlayed())
                .append(Constant.Charactor.SPACE)
                .append(Constant.Charactor.DIV)
                .append(Constant.Charactor.SPACE)
                .append(championStats.getStats().getTotalDeathsPerSession() / championStats.getStats().getTotalSessionsPlayed())
                .append(Constant.Charactor.SPACE)
                .append(Constant.Charactor.DIV)
                .append(Constant.Charactor.SPACE)
                .append(championStats.getStats().getTotalAssists() / championStats.getStats().getTotalSessionsPlayed());

        tvKDA.setText(builder);

        builder = new StringBuilder();
        builder
                .append(Constant.Charactor.W + ": ")
                .append(championStats.getStats().getTotalSessionsWon() * 100 / championStats.getStats().getTotalSessionsPlayed())
                .append(Constant.Charactor.PERCENT);
        tvWinPercent.setText(builder);
    }

    @OnClick({R.id.rank_solo_image, R.id.rank_5v5_image, R.id.rank_3v3_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rank_solo_image:
                ChallengerActivity.startActivity(getContext(), mSumonerEnity, Constant.SumonerStaticData.RANKED_SOLO_5X5);
                break;
            case R.id.rank_5v5_image:
                ChallengerActivity.startActivity(getContext(), mSumonerEnity, Constant.SumonerStaticData.RANKED_TEAM_5X5);
                break;
            case R.id.rank_3v3_image:
                ChallengerActivity.startActivity(getContext(), mSumonerEnity, Constant.SumonerStaticData.RANKED_TEAM_3X3);
                break;
        }
    }
}
