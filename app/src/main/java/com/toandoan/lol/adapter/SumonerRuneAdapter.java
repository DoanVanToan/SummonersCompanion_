package com.toandoan.lol.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.toandoan.lol.R;
import com.toandoan.lol.model.rune.PageRunes;
import com.toandoan.lol.model.rune.RuneEnity;
import com.toandoan.lol.utility.Utils;

import java.util.List;

/**
 * Created by framgia on 11/11/2016.
 */

public class SumonerRuneAdapter extends RecyclerView.Adapter<SumonerRuneAdapter.SumonerViewHolder> {
    private List<RuneEnity> mRuneEnities;
    private Context mContext;

    public SumonerRuneAdapter(Context context, List<RuneEnity> runeEnities) {
        mContext = context;
        mRuneEnities = runeEnities;
    }


    @Override
    public SumonerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.sumoner_rune_item_layout, parent, false);
        return new SumonerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SumonerViewHolder holder, int position) {
        RuneEnity rune = mRuneEnities.get(position);
        holder.bindData(rune);
    }

    @Override
    public int getItemCount() {
        return mRuneEnities != null ? mRuneEnities.size() : 0;
    }


    public class SumonerViewHolder extends RecyclerView.ViewHolder {
        private TextView mRuneCount;
        private ImageView mRuneAvtar;
        private TextView mRuneName, mRuneTitle;

        public SumonerViewHolder(View itemView) {
            super(itemView);
            mRuneCount = (TextView) itemView.findViewById(R.id.tv_rune_count);
            mRuneTitle = (TextView) itemView.findViewById(R.id.tvRuneTitle);
            mRuneName = (TextView) itemView.findViewById(R.id.tvRuneName);
            mRuneAvtar = (ImageView) itemView.findViewById(R.id.ivRuneAvatar);
        }

        public void bindData(RuneEnity rune) {
            mRuneCount.setText(rune.getmCount() + "x");
            mRuneName.setText(rune.getName());
            mRuneTitle.setText(rune.getDescription());
            Glide.with(mContext)
                    .load(Utils.RiotStatic.getRuneImage(rune.getImage().getFull()))
                    .into(mRuneAvtar);
        }
    }
}
