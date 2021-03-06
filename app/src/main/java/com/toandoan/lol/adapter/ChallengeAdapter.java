package com.toandoan.lol.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toandoan.lol.R;
import com.toandoan.lol.model.sumoner_overview.LeagueEntryEnity;
import com.toandoan.lol.widget.view.RobotoTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by framgia on 25/11/2016.
 */

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ViewHolder> {

    private List<LeagueEntryEnity> mLeagues;
    private Context mContext;
    private OnItemClickListenner mListenner;

    private int HEADER = 0;
    private int ITEM = 1;

    public ChallengeAdapter(Context context, List<LeagueEntryEnity> leagues, OnItemClickListenner listenner) {
        mContext = context;
        mLeagues = leagues;
        mListenner = listenner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.challenger_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == HEADER) holder.bindHeader();
        else {
            LeagueEntryEnity league = mLeagues.get(position);
            holder.bindViewItem(league);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mLeagues.get(position) != null) return ITEM;
        else return HEADER;
    }

    @Override
    public int getItemCount() {
        return mLeagues != null ? mLeagues.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sumoner_id)
        RobotoTextView mSumonerId;
        @BindView(R.id.sumoner_name)
        RobotoTextView mSumonerName;
        @BindView(R.id.sumoner_win_lose)
        RobotoTextView mSumonerWinLose;
        @BindView(R.id.sumoner_point)
        RobotoTextView mSumonerPoint;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            if (getAdapterPosition() % 2 == 0) {
                itemView.setBackgroundColor(mContext.getResources().getColor(R.color.trans_80));
            } else {
                itemView.setBackgroundColor(mContext.getResources().getColor(R.color.yellow_D4BC6C));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ChallengeAdapter.this.getItemViewType(getAdapterPosition()) != HEADER)
                        mListenner.onItemClick(getAdapterPosition(), mLeagues.get(getAdapterPosition()));
                }
            });
        }

        public void bindViewItem(LeagueEntryEnity leagueEntryEnity) {
            mSumonerId.setText(getAdapterPosition() + "");
            mSumonerName.setText(leagueEntryEnity.getPlayerOrTeamName());
            mSumonerPoint.setText(leagueEntryEnity.getLeaguePoints() + "");
            mSumonerWinLose.setText(leagueEntryEnity.getWins() + "/" + leagueEntryEnity.getLosses());
            if (getAdapterPosition() % 2 == 0) {
                itemView.setBackgroundColor(mContext.getResources().getColor(R.color.trans_80));
            } else {
                itemView.setBackgroundColor(mContext.getResources().getColor(R.color.yellow_D4BC6C));
            }
        }

        public void bindHeader() {
            mSumonerId.setText(R.string.id);
            mSumonerId.setTextSize(16);
            mSumonerName.setText(R.string.name);
            mSumonerName.setTextSize(16);
            mSumonerPoint.setText(R.string.points);
            mSumonerPoint.setTextSize(16);
            mSumonerWinLose.setText(R.string.w_l);
            mSumonerWinLose.setTextSize(16);
            itemView.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public interface OnItemClickListenner {
        void onItemClick(int position, LeagueEntryEnity leagueEntryEnity);
    }
}
