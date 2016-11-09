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
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.listenner.ISelectChampListenner;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.utility.LogUtil;
import com.toandoan.lol.utility.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public class SelectChampionAdapter extends RecyclerView.Adapter<SelectChampionAdapter.SelectChampionViewHolder> {
    private Context mContext;
    private List<ChampionEnity> listData;
    private IChampionSelectListenner listenner;

    public SelectChampionAdapter(Context mContext,IChampionSelectListenner listenner, List<ChampionEnity> listData) {
        this.mContext = mContext;
        this.listData = listData;
        this.listenner = listenner;
    }

    public void updateViews(List<ChampionEnity> newData){
        listData = new ArrayList<>(newData);
        notifyDataSetChanged();
    }

    @Override
    public SelectChampionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.select_champion_item_layout, null);
        return new SelectChampionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SelectChampionViewHolder holder, final int position) {
        final ChampionEnity championEnity = listData.get(position);
        Glide.with(mContext)
                .load(Utils.RiotStatic.getChampionIcon(championEnity.getKey()))
                .into(holder.getIvChampIcon());
        LogUtil.e("", Utils.RiotStatic.getChampionIcon(championEnity.getKey()));
        holder.tvChampName.setText(championEnity.getName());
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenner.onItemClickListenner(championEnity, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData != null ? listData.size() : 0;
    }

    public class SelectChampionViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivChampIcon;
        private TextView tvChampName;
        private View itemView;

        public SelectChampionViewHolder(View itemView) {
            super(itemView);
            ivChampIcon = (ImageView) itemView.findViewById(R.id.ivChampIcon);
            tvChampName = (TextView) itemView.findViewById(R.id.tvChampName);
            this.itemView = itemView;
        }

        public View getItemView() {
            return itemView;
        }

        public void setItemView(View itemView) {
            this.itemView = itemView;
        }

        public ImageView getIvChampIcon() {
            return ivChampIcon;
        }

        public void setIvChampIcon(ImageView ivChampIcon) {
            this.ivChampIcon = ivChampIcon;
        }

        public TextView getTvChampName() {
            return tvChampName;
        }

        public void setTvChampName(TextView tvChampName) {
            this.tvChampName = tvChampName;
        }
    }

    public interface IChampionSelectListenner{
        void onItemClickListenner(ChampionEnity championEnity, int position);
    }
}
