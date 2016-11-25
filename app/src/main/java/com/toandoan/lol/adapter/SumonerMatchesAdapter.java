package com.toandoan.lol.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toandoan.lol.R;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.database.impl.MyItemImpl;
import com.toandoan.lol.database.impl.SpellsImpl;
import com.toandoan.lol.model.SpellEnity;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.model.item.ItemEnity;
import com.toandoan.lol.model.recent_match.GameEnity;
import com.toandoan.lol.utility.FileOperations;
import com.toandoan.lol.utility.Utils;
import com.toandoan.lol.widget.dialog.ItemDialog;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 17/11/2016.
 */

public class SumonerMatchesAdapter extends RecyclerView.Adapter<SumonerMatchesAdapter.SumonerMatchesViewHolder> {
    private List<GameEnity> mGames;
    private Context mContext;
    private List<ChampionEnity> mChampions;
    private MyItemImpl mDatabase;
    private SpellsImpl mSpellDatabase;
    private ItemDialog mItemDialog;
    private OnItemClickListenner mListenner;


    public SumonerMatchesAdapter(Context context, List<GameEnity> games, OnItemClickListenner listenner) {
        mContext = context;
        mGames = games;
        mListenner = listenner;
        loadChampions();
        mDatabase = new MyItemImpl(mContext);
        mSpellDatabase = new SpellsImpl(mContext);
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
    public SumonerMatchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.sumoner_match_item_layout, parent, false);
        return new SumonerMatchesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SumonerMatchesViewHolder holder, int position) {
        GameEnity gameEnity = mGames.get(position);
        holder.bindView(gameEnity);
    }

    @Override
    public int getItemCount() {
        return mGames != null ? mGames.size() : 0;
    }


    public interface OnItemClickListenner {
        void onItemClick(View v, int position, GameEnity game);
    }

    public class SumonerMatchesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mItemImage;
        private TextView mItemTitle, mItemKda, mItemGold, mItemCrep, mItemTime, mItemDate;
        private ImageView mItemViewSlot1, mItemViewSlot2,
                mItemViewSlot3, mItemViewSlot4, mItemViewSlot5, mItemViewSlot6, mItemViewSlot7;
        private ImageView mItemSpell1, mItemSpell2;
        private GameEnity mGame;
        private TextView mMatchResult;

        public SumonerMatchesViewHolder(View itemView) {
            super(itemView);
            mItemImage = (ImageView) itemView.findViewById(R.id.match_item_image);
            mItemTitle = (TextView) itemView.findViewById(R.id.match_item_title);
            mItemKda = (TextView) itemView.findViewById(R.id.match_item_kda);
            mItemGold = (TextView) itemView.findViewById(R.id.match_item_gold);
            mItemCrep = (TextView) itemView.findViewById(R.id.match_item_crep);
            mItemTime = (TextView) itemView.findViewById(R.id.match_item_time);
            mItemDate = (TextView) itemView.findViewById(R.id.match_item_date);
            mItemViewSlot1 = (ImageView) itemView.findViewById(R.id.item_1);
            mItemViewSlot2 = (ImageView) itemView.findViewById(R.id.item_2);
            mItemViewSlot3 = (ImageView) itemView.findViewById(R.id.item_3);
            mItemViewSlot4 = (ImageView) itemView.findViewById(R.id.item_4);
            mItemViewSlot5 = (ImageView) itemView.findViewById(R.id.item_5);
            mItemViewSlot6 = (ImageView) itemView.findViewById(R.id.item_6);
            mItemViewSlot7 = (ImageView) itemView.findViewById(R.id.item_7);
            mItemSpell2 = (ImageView) itemView.findViewById(R.id.spellid_2);
            mItemSpell1 = (ImageView) itemView.findViewById(R.id.spellid_1);
            mMatchResult = (TextView) itemView.findViewById(R.id.match_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListenner.onItemClick(v, getAdapterPosition(), mGames.get(getAdapterPosition()));
                }
            });
        }

        public void bindView(GameEnity game) {
            mGame = game;
            StringBuilder builder = new StringBuilder();
            ChampionEnity championEnity = getChampionByID(String.valueOf(game.getChampionId()));
            Glide.with(mContext)
                    .load(Utils.RiotStatic.getChampionIcon(championEnity.getKey()))
                    .into(mItemImage);

            mItemTitle.setText(championEnity.getName());
            builder.append(game.getStats().getChampionsKilled())
                    .append(Constant.Charactor.DIV)
                    .append(game.getStats().getNumDeaths())
                    .append(Constant.Charactor.DIV)
                    .append(game.getStats().getAssists());
            mItemKda.setText(builder);
            mItemGold.setText(Utils.formatDouble(game.getStats().getGoldEarned()));
            mItemCrep.setText(String.valueOf(game.getStats().getMinionsKilled()
                    + game.getStats().getNeutralMinionsKilledYourJungle()
                    + game.getStats().getNeutralMinionsKilledEnemyJungle()));
            mItemTime.setText(Utils.getHourFormatFromSecond(game.getCreateDate()));
            mItemDate.setText(Utils.getDateFormatFromSecond(game.getCreateDate()));
            loadItemView(game);
            loadSpellView(game);
        }

        public void loadSpellView(GameEnity game) {
            SpellEnity firstSpell = mSpellDatabase.getSpellByID(game.getSpell1());
            SpellEnity secondSpell = mSpellDatabase.getSpellByID(game.getSpell2());

            if (firstSpell != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getSpellImage(firstSpell.getImage().getFull()))
                        .into(mItemSpell1);
                mItemSpell1.setOnClickListener(this);
            }

            if (secondSpell != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getSpellImage(secondSpell.getImage().getFull()))
                        .into(mItemSpell2);
                mItemSpell2.setOnClickListener(this);
            }
        }

        public void loadItemView(GameEnity game) {

            ItemEnity itemEnity = mDatabase.getItemById((int) game.getStats().getItem0());
            if (itemEnity != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                        .into(mItemViewSlot1);
                mItemViewSlot1.setOnClickListener(this);
            }
            itemEnity = mDatabase.getItemById((int) game.getStats().getItem1());
            if (itemEnity != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                        .into(mItemViewSlot2);
                mItemViewSlot2.setOnClickListener(this);
            }

            itemEnity = mDatabase.getItemById((int) game.getStats().getItem2());
            if (itemEnity != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                        .into(mItemViewSlot3);
                mItemViewSlot3.setOnClickListener(this);
            }

            itemEnity = mDatabase.getItemById((int) game.getStats().getItem3());
            if (itemEnity != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                        .into(mItemViewSlot4);
                mItemViewSlot4.setOnClickListener(this);
            }

            itemEnity = mDatabase.getItemById((int) game.getStats().getItem4());
            if (itemEnity != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                        .into(mItemViewSlot5);
                mItemViewSlot5.setOnClickListener(this);
            }

            itemEnity = mDatabase.getItemById((int) game.getStats().getItem5());
            if (itemEnity != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                        .into(mItemViewSlot6);
                mItemViewSlot6.setOnClickListener(this);
            }

            itemEnity = mDatabase.getItemById((int) game.getStats().getItem6());
            if (itemEnity != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                        .into(mItemViewSlot7);
                mItemViewSlot7.setOnClickListener(this);
            }
            if (game.getStats().isWin()) {
                mMatchResult.setBackgroundColor(Color.GREEN);
            } else {
                mMatchResult.setBackgroundColor(Color.RED);
            }

        }

        @Override
        public void onClick(View v) {
            ItemEnity selectedItem = null;
            SpellEnity seletedSpell = null;
            switch (v.getId()) {
                case R.id.item_1:
                    selectedItem = mDatabase.getItemById((int) mGame.getStats().getItem0());
                    break;
                case R.id.item_2:
                    selectedItem = mDatabase.getItemById((int) mGame.getStats().getItem1());
                    break;
                case R.id.item_3:
                    selectedItem = mDatabase.getItemById((int) mGame.getStats().getItem2());
                    break;
                case R.id.item_4:
                    selectedItem = mDatabase.getItemById((int) mGame.getStats().getItem3());
                    break;
                case R.id.item_5:
                    selectedItem = mDatabase.getItemById((int) mGame.getStats().getItem4());
                    break;
                case R.id.item_6:
                    selectedItem = mDatabase.getItemById((int) mGame.getStats().getItem5());
                    break;
                case R.id.item_7:
                    selectedItem = mDatabase.getItemById((int) mGame.getStats().getItem6());
                    break;
                case R.id.spellid_1:
                    seletedSpell = mSpellDatabase.getSpellByID(mGame.getSpell1());
                    break;
                case R.id.spellid_2:
                    seletedSpell = mSpellDatabase.getSpellByID(mGame.getSpell2());
                    break;
            }
            if (selectedItem != null) {
                mItemDialog = new ItemDialog(mContext);
                mItemDialog.setUp(selectedItem, mItemClickListenner);
                mItemDialog.show();
            } else {
                if (seletedSpell != null) {
                    Utils.showSpellDialog((Activity) mContext, seletedSpell);
                }
            }
        }

        private ItemDialog.ItemDialogOnClickListenner mItemClickListenner = new ItemDialog.ItemDialogOnClickListenner() {
            @Override
            public void onItemClickListenner(ItemEnity itemEnity) {
                if (mItemDialog != null && mItemDialog.isShowing()) {
                    mItemDialog.setUp(itemEnity, mItemClickListenner);
                }
            }
        };
    }
}
