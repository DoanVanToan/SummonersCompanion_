package com.toandoan.lol.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toandoan.lol.R;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.model.match_detail.Participant;
import com.toandoan.lol.model.match_detail.ParticipantIdentity;
import com.toandoan.lol.model.match_detail.Player;
import com.toandoan.lol.model.recent_match.GameEnity;
import com.toandoan.lol.model.recent_match.PlayerEnity;
import com.toandoan.lol.utility.FileOperations;
import com.toandoan.lol.utility.Utils;
import com.toandoan.lol.widget.view.RobotoTextView;
import com.toandoan.lol.widget.view.SquareImageView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ToanDoan on 11/20/2016.
 */

public class MatchDetailTeamAdapter extends RecyclerView.Adapter<MatchDetailTeamAdapter.MatchDetailTeamViewHolder> {
    private List<PlayerEnity> mPlayers;
    private Context mContext;
    private OnItemClickListenner mListenner;
    private final int LEFT_VIEW = 0;
    private final int RIGHT_VIEW = 1;
    private List<ChampionEnity> mChampions;

    public MatchDetailTeamAdapter(Context mContext, List<PlayerEnity> players
            , OnItemClickListenner listenner) {
        this.mContext = mContext;
        this.mPlayers = players;
        this.mListenner = listenner;
        this.loadChampions();
    }

    public void loadChampions() {
        String fullChampStr = new FileOperations(mContext).readData(Constant.Data.FULL_CHAMP_LIST);
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

    @Override
    public MatchDetailTeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == LEFT_VIEW) {
            v = LayoutInflater.from(mContext)
                    .inflate(R.layout.detail_team_left_items_layout, null);
        } else {
            v = LayoutInflater.from(mContext)
                    .inflate(R.layout.detail_team_right_items_layout, null);
        }
        return new MatchDetailTeamViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MatchDetailTeamViewHolder holder, int position) {
        PlayerEnity player = mPlayers.get(position);
        holder.bindData(player);
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) return LEFT_VIEW;
        else return RIGHT_VIEW;
    }

    @Override
    public int getItemCount() {
        return mPlayers != null ? mPlayers.size() : 0;
    }

    public class MatchDetailTeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.player_icon)
        SquareImageView mPlayerIcon;
        @BindView(R.id.playerName)
        RobotoTextView mPlayerName;

        public MatchDetailTeamViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindData(PlayerEnity playerEnity) {
            mPlayerName.setText(playerEnity.getSumonerName());
            ChampionEnity championEnity = getChampionByID(String.valueOf(playerEnity.getChampionId()));
            if (championEnity != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getChampionIcon(championEnity.getKey()))
                        .into(mPlayerIcon);
            }
        }

        @Override
        public void onClick(View v) {
            mListenner.onItemClick(v, getAdapterPosition(),
                    mPlayers.get(getAdapterPosition()));
        }
    }

    public interface OnItemClickListenner {
        void onItemClick(View v, int position, PlayerEnity player);
    }

}
