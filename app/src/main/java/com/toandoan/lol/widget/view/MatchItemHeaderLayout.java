package com.toandoan.lol.widget.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toandoan.lol.R;
import com.toandoan.lol.activity.ChampionDetailActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.database.impl.MyItemImpl;
import com.toandoan.lol.database.impl.SpellsImpl;
import com.toandoan.lol.model.SpellEnity;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.model.item.ItemEnity;
import com.toandoan.lol.model.match_detail.Participant;
import com.toandoan.lol.model.match_detail.ParticipantStats;
import com.toandoan.lol.utility.FileOperations;
import com.toandoan.lol.utility.Utils;
import com.toandoan.lol.widget.dialog.ItemDialog;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ToanDoan on 11/20/2016.
 */

public class MatchItemHeaderLayout extends LinearLayout implements View.OnClickListener {
    @BindView(R.id.match_item_image)
    SquareImageView matchItemImage;
    @BindView(R.id.match_item_title)
    RobotoTextView matchItemTitle;
    @BindView(R.id.match_item_kda)
    RobotoTextView matchItemKda;
    @BindView(R.id.match_item_gold)
    RobotoTextView matchItemGold;
    @BindView(R.id.match_item_crep)
    RobotoTextView matchItemCrep;
    @BindView(R.id.match_item_time)
    RobotoTextView matchItemTime;
    @BindView(R.id.match_item_date)
    RobotoTextView matchItemDate;
    @BindView(R.id.spellid_1)
    SquareImageView spellid1;
    @BindView(R.id.spellid_2)
    SquareImageView spellid2;
    @BindView(R.id.item_1)
    SquareImageView item1;
    @BindView(R.id.item_2)
    SquareImageView item2;
    @BindView(R.id.item_3)
    SquareImageView item3;
    @BindView(R.id.item_4)
    SquareImageView item4;
    @BindView(R.id.item_5)
    SquareImageView item5;
    @BindView(R.id.item_6)
    SquareImageView item6;
    @BindView(R.id.item_7)
    SquareImageView item7;

    private Participant mParticipant;
    private List<ChampionEnity> mChampions;
    private SpellsImpl mSpellDatabase;
    private MyItemImpl mDatabase;
    private Context mContext;
    private ItemDialog mItemDialog;
    private ChampionEnity mChampionEnity;

    public MatchItemHeaderLayout(Context context) {
        super(context);
        init();
    }

    public MatchItemHeaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MatchItemHeaderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MatchItemHeaderLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.match_item_header_layout, this);
    }

    public void setParticipant(Participant participant) {
        this.mParticipant = participant;
        this.build();
    }

    public void build() {
        mContext = getContext();
        loadChampions();
        mSpellDatabase = new SpellsImpl(mContext);
        mDatabase = new MyItemImpl(mContext);
        bindView();
    }

    public void loadChampions() {
        String fullChampStr = new FileOperations(getContext()).readData(Constant.Data.FULL_CHAMP_LIST);
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

    public void bindView() {
        StringBuilder builder = new StringBuilder();
        mChampionEnity = getChampionByID(String.valueOf(mParticipant.getChampionId()));
        Glide.with(mContext)
                .load(Utils.RiotStatic.getChampionIcon(mChampionEnity.getKey()))
                .into(matchItemImage);

        matchItemImage.setOnClickListener(this);

        matchItemTitle.setText(mChampionEnity.getName());
        builder.append(mParticipant.getStats().getKills())
                .append(Constant.Charactor.DIV)
                .append(mParticipant.getStats().getDeaths())
                .append(Constant.Charactor.DIV)
                .append(mParticipant.getStats().getAssists());
        matchItemKda.setText(builder);
        matchItemGold.setText(String.valueOf(mParticipant.getStats().getGoldEarned()));
        matchItemCrep.setText(String.valueOf(mParticipant.getStats().getMinionsKilled()));
        matchItemTime.setText(Utils.getTimeFromSecond(mParticipant.getMatchDuration()));
        matchItemDate.setText(Utils.getDateFormatFromSecond(mParticipant.getmMatchCreation()));
        loadItemView(mParticipant.getStats());
        loadSpellView(mParticipant);
    }

    public void loadSpellView(Participant participant) {
        SpellEnity firstSpell = mSpellDatabase.getSpellByID(participant.getSpell1Id());
        SpellEnity secondSpell = mSpellDatabase.getSpellByID(participant.getSpell2Id());

        if (firstSpell != null) {
            Glide.with(getContext())
                    .load(Utils.RiotStatic.getSpellImage(firstSpell.getImage().getFull()))
                    .into(spellid1);
            spellid1.setOnClickListener(this);
        }

        if (secondSpell != null) {
            Glide.with(getContext())
                    .load(Utils.RiotStatic.getSpellImage(secondSpell.getImage().getFull()))
                    .into(spellid2);
            spellid2.setOnClickListener(this);
        }
    }

    public void loadItemView(ParticipantStats stats) {

        ItemEnity itemEnity = mDatabase.getItemById((int) stats.getItem0());
        if (itemEnity != null) {
            Glide.with(mContext)
                    .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                    .into(item1);
            item1.setOnClickListener(this);
        }
        itemEnity = mDatabase.getItemById((int) stats.getItem1());
        if (itemEnity != null) {
            Glide.with(mContext)
                    .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                    .into(item2);
            item2.setOnClickListener(this);
        }

        itemEnity = mDatabase.getItemById((int) stats.getItem2());
        if (itemEnity != null) {
            Glide.with(mContext)
                    .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                    .into(item3);
            item3.setOnClickListener(this);
        }

        itemEnity = mDatabase.getItemById((int) stats.getItem3());
        if (itemEnity != null) {
            Glide.with(mContext)
                    .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                    .into(item4);
            item4.setOnClickListener(this);
        }

        itemEnity = mDatabase.getItemById((int) stats.getItem4());
        if (itemEnity != null) {
            Glide.with(mContext)
                    .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                    .into(item5);
            item5.setOnClickListener(this);
        }

        itemEnity = mDatabase.getItemById((int) stats.getItem5());
        if (itemEnity != null) {
            Glide.with(mContext)
                    .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                    .into(item6);
            item6.setOnClickListener(this);
        }

        itemEnity = mDatabase.getItemById((int) stats.getItem6());
        if (itemEnity != null) {
            Glide.with(mContext)
                    .load(Utils.RiotStatic.getItemImage(itemEnity.getImage().getFull()))
                    .into(item7);
            item7.setOnClickListener(this);
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.match_item_image){
            ChampionDetailActivity.startActivity(mContext, mChampionEnity);
        }else {
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
                if (seletedSpell!=null){
                    Utils.showSpellDialog((Activity) mContext, seletedSpell);
                }
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
