package com.toandoan.lol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.toandoan.lol.R;
import com.toandoan.lol.adapter.MatchDetailTeamAdapter;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.UserEnity;
import com.toandoan.lol.model.match_detail.MatchDetail;
import com.toandoan.lol.model.match_detail.Participant;
import com.toandoan.lol.model.match_detail.ParticipantIdentity;
import com.toandoan.lol.model.match_detail.ParticipantStats;
import com.toandoan.lol.model.match_detail.Player;
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

    private MatchDetail mMatchDetail;
    private MatchDetailTeamAdapter mAdapter;
    private List<ParticipantIdentity> mParticipantIdentities;
    private Participant mParticipant;
    private ParticipantStats mParticipantStats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);
        ButterKnife.bind(this);
        getData();
        initViews();
    }

    public static void startActivity(Context context, MatchDetail matchDetail, Participant participant) {
        Intent intent = new Intent(context, MatchDetailActivity.class);
        intent.putExtra(Constant.IntentKey.PARTICIPANT, participant);
        intent.putExtra(Constant.IntentKey.MATCHDETAIL, matchDetail);
        context.startActivity(intent);
    }

    private void getData() {
        Intent data = getIntent();
        if (data != null) {
            mParticipant =
                    (Participant) data.getExtras().getSerializable(Constant.IntentKey.PARTICIPANT);
            mParticipantStats = mParticipant.getStats();
            mMatchDetail = (MatchDetail)
                    data.getExtras().getSerializable(Constant.IntentKey.MATCHDETAIL);
            mParticipantIdentities = getListParticipant();
        }
    }

    private List<ParticipantIdentity> getListParticipant() {
        List<ParticipantIdentity> result = new ArrayList<>();
        if (mMatchDetail.getParticipantIdentities() != null) {
            int halfSize = mMatchDetail.getParticipantIdentities().size() / 2;
            for (int i = 0; i < halfSize; i++) {
                ParticipantIdentity firstParticipantIdentity = mMatchDetail.getParticipantIdentities().get(i);
                ParticipantIdentity secondParticipantIdentity = mMatchDetail.getParticipantIdentities().get(i + halfSize);

                for (Participant participant : mMatchDetail.getParticipants()) {
                    if (firstParticipantIdentity.getParticipantId() == participant.getParticipantId()){
                        firstParticipantIdentity.getPlayer().setChampionId(participant.getChampionId());
                    }else {
                        if (secondParticipantIdentity.getParticipantId() == participant.getParticipantId()){
                            secondParticipantIdentity.getPlayer().setChampionId(participant.getChampionId());
                        }
                    }
                }
                result.add(firstParticipantIdentity);
                result.add(secondParticipantIdentity);


            }
        }


        return result;
    }

    @Override
    public void initViews() {
        getSupportActionBar().hide();
        mStatisticLayout.setStats(mParticipantStats);
        mMatchHeaderLayout.setParticipant(mParticipant);
        mAdapter = new MatchDetailTeamAdapter(this, mParticipantIdentities, this);
        mTeamRecyclerview.setLayoutManager(new GridLayoutManager(activity, 2));
        mTeamRecyclerview.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View v, int position, Player player) {
        UserEnity userEnity = new UserEnity();
        userEnity.setName(player.getSummonerName());
        userEnity.setId((int) player.getSummonerId());
        SumonerDetailActivity.startActivity(activity, userEnity);
    }
}
