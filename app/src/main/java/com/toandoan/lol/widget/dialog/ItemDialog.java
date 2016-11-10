package com.toandoan.lol.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.toandoan.lol.R;
import com.toandoan.lol.adapter.ItemDialogAdapter;
import com.toandoan.lol.database.impl.MyItemImpl;
import com.toandoan.lol.model.item.ItemEnity;
import com.toandoan.lol.utility.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public class ItemDialog extends Dialog {
    private ImageView ivItemImage;
    private TextView tvItemName, tvItemGoldPrice, tvItemTitle;
    private LinearLayout llBuiltFrom, llBuiltInto;
    private RecyclerView mBuiltFromRyRecyclerView, mBuiltIntoRecyclerView;

    private Context mContext;
    private ItemEnity mItemEnity;
    private MyItemImpl mItemController;
    private List<ItemEnity> mBuiltFromItems;
    private List<ItemEnity> mBuiltIntoItems;
    private int mBuiltFromCols, mBuiltIntoCols;
    private int mMaxCols = 5;
    private ItemDialogAdapter mBuiltFromAdapter, mBuiltIntoAdapter;
    private ItemDialogOnClickListenner mListenner;

    public ItemDialog(Context context) {
        super(context);
        init(context);
    }

    public ItemDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected ItemDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        mBuiltFromCols = 0;
        mBuiltIntoCols = 0;
        this.mContext = context;
        mItemController = new MyItemImpl(mContext);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.item_dialog_layout);
        ivItemImage = (ImageView) findViewById(R.id.ivItemImage);
        tvItemName = (TextView) findViewById(R.id.tvItemName);
        tvItemGoldPrice = (TextView) findViewById(R.id.tvItemGoldPrice);
        tvItemTitle = (TextView) findViewById(R.id.tvItemTitle);
        llBuiltFrom = (LinearLayout) findViewById(R.id.llBuiltFrom);
        llBuiltInto = (LinearLayout) findViewById(R.id.llBuiltInto);
        tvItemTitle= (TextView) findViewById(R.id.tvItemTitle);
        mBuiltFromRyRecyclerView = (RecyclerView) findViewById(R.id.mBuiltFromRyRecyclerView);
        mBuiltIntoRecyclerView = (RecyclerView) findViewById(R.id.mBuiltIntoRecyclerView);
    }

    public void setUp(ItemEnity itemEnity, ItemDialogOnClickListenner listenner) {
        this.mItemEnity = itemEnity;
        this.mListenner = listenner;
        this.getData();
        this.setUpViews();
    }

    public void getData() {
        if (mItemEnity.getFrom() != null && mItemEnity.getFrom().size() != 0) {
            mBuiltFromItems = new ArrayList<>();
            for (int i = 0; i < mItemEnity.getFrom().size(); i++) {
                ItemEnity currentItem = mItemController.getItemById(Integer.parseInt(mItemEnity.getFrom().get(i)));
                if (currentItem != null) mBuiltFromItems.add(currentItem);
            }

            if (mItemEnity.getFrom().size() > mMaxCols) {
                mBuiltFromCols = mMaxCols;
            } else {
                mBuiltFromCols = mItemEnity.getFrom().size();
            }
        }

        if (mItemEnity.getInto() != null && mItemEnity.getInto().size() != 0) {
            mBuiltIntoItems = new ArrayList<>();
            for (int i = 0; i < mItemEnity.getInto().size(); i++) {
                ItemEnity currentItem = mItemController.getItemById(Integer.parseInt(mItemEnity.getInto().get(i)));
                if (currentItem != null) mBuiltIntoItems.add(currentItem);
            }

            if (mItemEnity.getInto().size() > mMaxCols) {
                mBuiltIntoCols = mMaxCols;
            } else {
                mBuiltIntoCols = mItemEnity.getInto().size();
            }
        }


    }

    public void setUpViews() {
        tvItemName.setText(mItemEnity.getName());
        String strPrice = mItemEnity.getGold().getTotal() + "";
        if (mItemEnity.getGold().getBase() != mItemEnity.getGold().getTotal()) {
            strPrice += " (" + mItemEnity.getGold().getBase() + ")";
        }

        tvItemTitle.setText(Html.fromHtml(mItemEnity.getDescription()));

        tvItemGoldPrice.setText(strPrice);
        Glide.with(mContext)
                .load(Utils.RiotStatic.getItemImage(mItemEnity.getImage().getFull()))
                .into(ivItemImage);

        if (mItemEnity.getFrom() == null || mItemEnity.getFrom().size() == 0) {
            llBuiltFrom.setVisibility(View.GONE);
        } else {
            llBuiltFrom.setVisibility(View.VISIBLE);
            mBuiltFromAdapter = new ItemDialogAdapter(mContext, mBuiltFromItems, mListenner);
            mBuiltFromRyRecyclerView.setLayoutManager(new GridLayoutManager(mContext, mBuiltFromCols));
            mBuiltFromRyRecyclerView.setAdapter(mBuiltFromAdapter);
        }

        if (mItemEnity.getInto() == null || mItemEnity.getInto().size() == 0) {
            llBuiltInto.setVisibility(View.GONE);
        } else {
            llBuiltInto.setVisibility(View.VISIBLE);
            mBuiltIntoAdapter = new ItemDialogAdapter(mContext, mBuiltIntoItems, mListenner);
            mBuiltIntoRecyclerView.setLayoutManager(new GridLayoutManager(mContext, mBuiltIntoCols));
            mBuiltIntoRecyclerView.setAdapter(mBuiltIntoAdapter);
        }

    }

    public interface ItemDialogOnClickListenner {
        void onItemClickListenner(ItemEnity itemEnity);
    }

}
