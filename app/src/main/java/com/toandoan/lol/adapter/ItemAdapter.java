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
import com.toandoan.lol.listenner.ItemListenner;
import com.toandoan.lol.model.item.ItemEnity;
import com.toandoan.lol.utility.Utils;

import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<ItemEnity> mListItem;
    private Context mContext;
    private ItemListenner mItemListenner;

    public ItemAdapter(Context mContext, ItemListenner itemListenner, List<ItemEnity> mListItem) {
        this.mContext = mContext;
        this.mListItem = mListItem;
        this.mItemListenner = itemListenner;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        ItemEnity currentItem = mListItem.get(position);
        String strPrice = currentItem.getGold().getTotal() + "";
        if (currentItem.getGold().getBase() !=  currentItem.getGold().getTotal()) {
            strPrice += " (" + currentItem.getGold().getBase() + ")";
        }
        holder.tvItemName.setText(currentItem.getName());
        holder.tvItemGoldPrice.setText(strPrice);
        Glide.with(mContext)
                .load(Utils.RiotStatic.getItemImage(currentItem.getImage().getFull()))
                .into(holder.ivItemImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemListenner.onItemClickListenner(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListItem == null ? 0 : mListItem.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView ivItemImage;
        TextView tvItemName, tvItemGoldPrice;
        View itemView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.ivItemImage = (ImageView) itemView.findViewById(R.id.ivItemImage);
            this.tvItemName = (TextView) itemView.findViewById(R.id.tvItemName);
            this.tvItemGoldPrice = (TextView) itemView.findViewById(R.id.tvItemGoldPrice);
        }
    }
}
