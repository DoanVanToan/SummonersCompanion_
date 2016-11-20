package com.toandoan.lol.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
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
import com.toandoan.lol.model.match_detail.Participant;
import com.toandoan.lol.model.match_detail.ParticipantStats;
import com.toandoan.lol.model.match_detail.Team;
import com.toandoan.lol.model.rune.RuneEnity;
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
    private List<Participant> mParticipants;
    private Context mContext;
    private List<ChampionEnity> mChampions;
    private MyItemImpl mDatabase;
    private SpellsImpl mSpellDatabase;
    private ItemDialog mItemDialog;
    private OnItemClickListenner mListenner;


    public SumonerMatchesAdapter(Context context, List<Participant> participants, OnItemClickListenner listenner) {
        mContext = context;
        mParticipants = participants;
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
        Participant participant = mParticipants.get(position);
        holder.bindView(participant);
    }

    @Override
    public int getItemCount() {
        return mParticipants != null ? mParticipants.size() : 0;
    }


    public interface OnItemClickListenner{
        void onItemClick(View v, int position,Participant participant);
    }

    public class SumonerMatchesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mItemImage;
        private TextView mItemTitle, mItemKda, mItemGold, mItemCrep, mItemTime, mItemDate;
        private ImageView mItemViewSlot1, mItemViewSlot2,
                mItemViewSlot3, mItemViewSlot4, mItemViewSlot5, mItemViewSlot6, mItemViewSlot7;
        private ImageView mItemSpell1, mItemSpell2;
        private Participant mParticipant;
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
            mMatchResult= (TextView) itemView.findViewById(R.id.match_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListenner.onItemClick(v, getAdapterPosition(), mParticipants.get(getAdapterPosition()));
                }
            });
        }

        public void bindView(Participant participant) {
            mParticipant = participant;
            StringBuilder builder = new StringBuilder();
            ChampionEnity championEnity = getChampionByID(String.valueOf(participant.getChampionId()));
            Glide.with(mContext)
                    .load(Utils.RiotStatic.getChampionIcon(championEnity.getKey()))
                    .into(mItemImage);

            mItemTitle.setText(championEnity.getName());
            builder.append(participant.getStats().getKills())
                    .append(Constant.Charactor.DIV)
                    .append(participant.getStats().getDeaths())
                    .append(Constant.Charactor.DIV)
                    .append(participant.getStats().getAssists());
            mItemKda.setText(builder);
            mItemGold.setText(String.valueOf(participant.getStats().getGoldEarned()));
            mItemCrep.setText(String.valueOf(participant.getStats().getMinionsKilled()));
            mItemTime.setText(Utils.getTimeFromSecond(participant.getMatchDuration()));
            mItemDate.setText(Utils.getDateFormatFromSecond(participant.getmMatchCreation()));
            loadItemView(participant.getStats());
            loadSpellView(participant);
        }

        public void loadSpellView(Participant participant) {
            SpellEnity firstSpell = mSpellDatabase.getSpellByID(participant.getSpell1Id());
            SpellEnity secondSpell = mSpellDatabase.getSpellByID(participant.getSpell2Id());

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

        public void loadItemView(ParticipantStats stats) {

            ItemEnity itemEnity = mDatabase.getItemById((int) stats.getItem0());
            if (itemEnity != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                        .into(mItemViewSlot1);
                mItemViewSlot1.setOnClickListener(this);
            }
            itemEnity = mDatabase.getItemById((int) stats.getItem1());
            if (itemEnity != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                        .into(mItemViewSlot2);
                mItemViewSlot2.setOnClickListener(this);
            }

            itemEnity = mDatabase.getItemById((int) stats.getItem2());
            if (itemEnity != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                        .into(mItemViewSlot3);
                mItemViewSlot3.setOnClickListener(this);
            }

            itemEnity = mDatabase.getItemById((int) stats.getItem3());
            if (itemEnity != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                        .into(mItemViewSlot4);
                mItemViewSlot4.setOnClickListener(this);
            }

            itemEnity = mDatabase.getItemById((int) stats.getItem4());
            if (itemEnity != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                        .into(mItemViewSlot5);
                mItemViewSlot5.setOnClickListener(this);
            }

            itemEnity = mDatabase.getItemById((int) stats.getItem5());
            if (itemEnity != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                        .into(mItemViewSlot6);
                mItemViewSlot6.setOnClickListener(this);
            }

            itemEnity = mDatabase.getItemById((int) stats.getItem6());
            if (itemEnity != null) {
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                        .into(mItemViewSlot7);
                mItemViewSlot7.setOnClickListener(this);
            }
            if (stats.isWinner()){
                mMatchResult.setBackgroundColor(Color.GREEN);
            }else {
                mMatchResult.setBackgroundColor(Color.RED);
            }

        }

        @Override
        public void onClick(View v) {
            ItemEnity selectedItem = null;
            SpellEnity seletedSpell = null;
            switch (v.getId()) {
                case R.id.item_1:
                    selectedItem = mDatabase.getItemById((int) mParticipant.getStats().getItem0());
                    break;
                case R.id.item_2:
                    selectedItem = mDatabase.getItemById((int) mParticipant.getStats().getItem1());
                    break;
                case R.id.item_3:
                    selectedItem = mDatabase.getItemById((int) mParticipant.getStats().getItem2());
                    break;
                case R.id.item_4:
                    selectedItem = mDatabase.getItemById((int) mParticipant.getStats().getItem3());
                    break;
                case R.id.item_5:
                    selectedItem = mDatabase.getItemById((int) mParticipant.getStats().getItem4());
                    break;
                case R.id.item_6:
                    selectedItem = mDatabase.getItemById((int) mParticipant.getStats().getItem5());
                    break;
                case R.id.item_7:
                    selectedItem = mDatabase.getItemById((int) mParticipant.getStats().getItem6());
                    break;
                case R.id.spellid_1:
                    seletedSpell = mSpellDatabase.getSpellByID(mParticipant.getSpell1Id());
                    break;
                case R.id.spellid_2:
                    seletedSpell = mSpellDatabase.getSpellByID(mParticipant.getSpell2Id());
                    break;
            }
            if (selectedItem != null) {
                mItemDialog = new ItemDialog(mContext);
                mItemDialog.setUp(selectedItem, mItemClickListenner);
                mItemDialog.show();
            } else {
                Utils.showSpellDialog((Activity) mContext, seletedSpell);
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
