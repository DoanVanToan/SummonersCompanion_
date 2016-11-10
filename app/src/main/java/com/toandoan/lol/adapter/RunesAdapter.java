package com.toandoan.lol.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.toandoan.lol.R;
import com.toandoan.lol.model.rune.RuneEnity;
import com.toandoan.lol.utility.Utils;

import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public class RunesAdapter extends RecyclerView.Adapter<RunesAdapter.RunesViewholder> {
    private List<RuneEnity> mRunes;
    private Context mContext;

    public RunesAdapter(Context mContext, List<RuneEnity> mRunes) {
        this.mContext = mContext;
        this.mRunes = mRunes;
    }

    @Override
    public RunesViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.rune_item_layout, null);
        return new RunesViewholder(v);
    }

    @Override
    public void onBindViewHolder(RunesViewholder holder, int position) {
        RuneEnity rune = mRunes.get(position);
        holder.tvRuneName.setText(rune.getName());
        holder.tvRuneTitle.setText(rune.getDescription());
        Glide.with(mContext)
                .load(Utils.RiotStatic.getRuneImage(rune.getImage().getFull()))
                .into(holder.ivRuneTitle);
    }

    @Override
    public int getItemCount() {
        return mRunes == null ? 0 : mRunes.size();
    }

    public class RunesViewholder extends RecyclerView.ViewHolder {
        ImageView ivRuneTitle;
        TextView tvRuneName, tvRuneTitle;

        public RunesViewholder(View itemView) {
            super(itemView);
            ivRuneTitle = (ImageView) itemView.findViewById(R.id.ivRuneTitle);
            tvRuneName = (TextView) itemView.findViewById(R.id.tvRuneName);
            tvRuneTitle = (TextView) itemView.findViewById(R.id.tvRuneTitle);
        }
    }
}
