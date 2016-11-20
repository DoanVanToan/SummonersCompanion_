package com.toandoan.lol.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.toandoan.lol.R;
import com.toandoan.lol.model.champion.ChampionStats;
import com.toandoan.lol.model.champion_by_season.ChampionStatsEnity;
import com.toandoan.lol.widget.view.ChampionStatsLayout;

import java.util.List;

/**
 * Created by ToanDoan on 11/21/2016.
 */

public class ChampionDetailStatsAdapter extends RecyclerView.Adapter<ChampionDetailStatsAdapter.ViewHolder> {
    private Context mContext;
    private List<ChampionStatsEnity> mChampions;

    public ChampionDetailStatsAdapter(Context mContext, List<ChampionStatsEnity> mChampions) {
        this.mContext = mContext;
        this.mChampions = mChampions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ChampionStatsLayout v = new ChampionStatsLayout(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(params);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChampionStatsEnity championStatsEnity = mChampions.get(position);
        holder.bindView(championStatsEnity);
    }

    @Override
    public int getItemCount() {
        return mChampions != null ? mChampions.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ChampionStatsLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = (ChampionStatsLayout) itemView;
        }

        public void bindView(ChampionStatsEnity championStatsEnity) {
            layout.bindData(championStatsEnity);
        }
    }
}
