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
import com.toandoan.lol.model.item.ItemEnity;
import com.toandoan.lol.utility.Utils;
import com.toandoan.lol.widget.dialog.ItemDialog;

import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public class ItemDialogAdapter extends RecyclerView.Adapter<ItemDialogAdapter.ItemDialogViewholder> {
    private Context mContext;
    private List<ItemEnity> mItemsEnities;
    private ItemDialog.ItemDialogOnClickListenner mListenner;

    public ItemDialogAdapter(Context mContext, List<ItemEnity> mItemsEnities, ItemDialog.ItemDialogOnClickListenner mListenner) {
        this.mContext = mContext;
        this.mItemsEnities = mItemsEnities;
        this.mListenner = mListenner;
    }

    @Override
    public ItemDialogViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_dialog_item_layout, null);

        return new ItemDialogViewholder(v);
    }

    @Override
    public void onBindViewHolder(ItemDialogViewholder holder, int position) {
        final ItemEnity itemEnity = mItemsEnities.get(position);
        Glide.with(mContext)
                .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                .into(holder.ivItemImage);
        holder.tvGoldPrice.setText(itemEnity.getGold().getTotal() + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListenner.onItemClickListenner(itemEnity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemsEnities != null ? mItemsEnities.size() : 0;
    }

    public class ItemDialogViewholder extends RecyclerView.ViewHolder {
        ImageView ivItemImage;
        TextView tvGoldPrice;
        View itemView;

        public ItemDialogViewholder(View itemView) {
            super(itemView);
            ivItemImage = (ImageView) itemView.findViewById(R.id.ivItemImage);
            tvGoldPrice = (TextView) itemView.findViewById(R.id.tvItemGoldPrice);
            this.itemView = itemView;
        }
    }
}
