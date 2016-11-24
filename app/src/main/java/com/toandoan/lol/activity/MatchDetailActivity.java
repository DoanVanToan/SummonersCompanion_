package com.toandoan.lol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.toandoan.lol.R;
import com.toandoan.lol.adapter.MatchDetailTeamAdapter;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.SumonerEnity;
import com.toandoan.lol.model.recent_match.GameEnity;
import com.toandoan.lol.model.recent_match.PlayerEnity;
import com.toandoan.lol.mvp_abstract.MatchDetailContract;
import com.toandoan.lol.widget.view.MatchItemHeaderLayout;
import com.toandoan.lol.widget.view.StatisticLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchDetailActivity extends BaseActivity implements MatchDetailContract.View, MatchDetailTeamAdapter.OnItemClickListenner {
    @BindView(R.id.statistic_layout)
    StatisticLayout mStatisticLayout;
    @BindView(R.id.match_header_layout)
    MatchItemHeaderLayout mMatchHeaderLayout;
    @BindView(R.id.team_recyclerview)
    RecyclerView mTeamRecyclerview;

    private MatchDetailTeamAdapter mAdapter;
    private GameEnity mGame;
    private int mCurrentID;
    private List<PlayerEnity> mPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);
        ButterKnife.bind(this);
        getData();
        initViews();
    }

    public static void startActivity(Context context, GameEnity gameEnity, int currentID) {
        Intent intent = new Intent(context, MatchDetailActivity.class);
        intent.putExtra(Constant.IntentKey.GAME_ENITY, gameEnity);
        intent.putExtra(Constant.IntentKey.SUMONER_ID, currentID);
        context.startActivity(intent);
    }

    private void getData() {
        Intent data = getIntent();
        if (data != null) {
            mGame = (GameEnity) data.getExtras().getSerializable(Constant.IntentKey.GAME_ENITY);
            mCurrentID = data.getExtras().getInt(Constant.IntentKey.SUMONER_ID);
            mPlayers = getListPlayer();
        }
    }

    private List<PlayerEnity> getListPlayer() {
        List<PlayerEnity> result = new ArrayList<>();
        List<PlayerEnity> firstTeam = new ArrayList<>();
        List<PlayerEnity> secondTeam = new ArrayList<>();
        for (PlayerEnity player : mGame.getFellowPlayers()) {
            if (player.getTeamId() == 100) {
                firstTeam.add(player);
            } else {
                secondTeam.add(player);
            }
        }
        PlayerEnity currentPlayer = new PlayerEnity();
        currentPlayer.setChampionId(mGame.getChampionId());
        currentPlayer.setSummonerId(mCurrentID);

        if (mGame.getTeamId() == 100) {
            currentPlayer.setTeamId(100);
            firstTeam.add(currentPlayer);
        } else {
            currentPlayer.setTeamId(200);
            secondTeam.add(currentPlayer);
        }

        result.addAll(firstTeam);
        result.addAll(secondTeam);
        return result;
    }

    @Override
    public void initViews() {
        getSupportActionBar().hide();
        mStatisticLayout.setGame(mGame);
        mMatchHeaderLayout.setGame(mGame);
        mAdapter = new MatchDetailTeamAdapter(this, mPlayers, this);
        mTeamRecyclerview.setLayoutManager(new GridLayoutManager(activity, 2));
        mTeamRecyclerview.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View v, int position, PlayerEnity player) {
        SumonerEnity sumonerEnity = new SumonerEnity();
        sumonerEnity.setName(player.getSumonerName());
        sumonerEnity.setId((int) player.getSummonerId());
        SumonerDetailActivity.startActivity(activity, sumonerEnity);
    }
}
