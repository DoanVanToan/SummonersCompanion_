package com.toandoan.lol.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.toandoan.lol.R;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.model.champion.ChampionPassive;
import com.toandoan.lol.model.champion.ChampionSpell;
import com.toandoan.lol.utility.Utils;

import java.util.List;

/**
 * Created by ToanDoan on 10/16/2016.
 */

public class AbilitesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int PASSIVE_TYPE = 0;
    private int ABILITES_TYPE = 1;
    private Context mContext;
    private ChampionEnity championEnity;
    private List<ChampionSpell> listSpell;
    private IAbilitesClickListenner listenner;

    public AbilitesAdapter(Context mContext, ChampionEnity championEnity, IAbilitesClickListenner listenner) {
        this.mContext = mContext;
        this.championEnity = championEnity;
        this.listSpell = championEnity.getSpells();
        this.listenner = listenner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == PASSIVE_TYPE) {
            v = LayoutInflater.from(mContext).inflate(R.layout.abilites_pasive_item_layout, parent, false);
            return new PassiveViewHolder(v);

        } else {
            v = LayoutInflater.from(mContext).inflate(R.layout.abilities_item_layout, parent, false);
            return new AbilitesViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == PASSIVE_TYPE) {
            ChampionPassive passive = championEnity.getPassive();
            ((PassiveViewHolder) holder).getTvPassiveName().setText(passive.getName());
            ((PassiveViewHolder) holder).getTvPassiveTitle().setText(Html.fromHtml(passive.getDescription()));

            Glide.with(mContext)
                    .load(Utils.RiotStatic.getChampionPassive(passive.getImage().getFull()))
                    .into(((PassiveViewHolder) holder).getIvPassiveIcon());

            ((PassiveViewHolder) holder).getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenner.onItemClickListenner(championEnity, position);
                }
            });
        } else {
            ChampionSpell spell = listSpell.get(position - 1);
            String cooldown = mContext.getString(R.string.cool_down) + ": " + spell.getCooldownBurn() + " s";
            String cost = mContext.getString(R.string.cost) + ": " + spell.getCostBurn() + " " + spell.getCostType();
            String range = mContext.getString(R.string.range) + ": " + spell.getRangeBurn();
            String description = spell.getDescription();
            String name = spell.getName();

            ((AbilitesViewHolder) holder).getTvSpellName().setText(name);
            ((AbilitesViewHolder) holder).getTvSpellTitle().setText(Html.fromHtml(description));
            ((AbilitesViewHolder) holder).getTvSpellCooldown().setText(cooldown);
            ((AbilitesViewHolder) holder).getTvSpellCost().setText(cost);
            ((AbilitesViewHolder) holder).getTvSpellRange().setText(range);
            ((AbilitesViewHolder) holder).getTvSpellCooldown().setText(cooldown);

            Glide.with(mContext)
                    .load(Utils.RiotStatic.getChampionAbiliteUrlImage(spell.getImage().getFull()))
                    .into(((AbilitesViewHolder) holder).getIvSpellIcon());

            ((AbilitesViewHolder) holder).getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenner.onItemClickListenner(championEnity, position);
                }
            });

        }

    }


    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? PASSIVE_TYPE : ABILITES_TYPE;
    }

    public class AbilitesViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSpellName, tvSpellCooldown, tvSpellCost, tvSpellRange, tvSpellTitle;
        private ImageView ivSpellIcon;
        private View itemView;

        public AbilitesViewHolder(View itemView) {
            super(itemView);
            tvSpellName = (TextView) itemView.findViewById(R.id.tvSpellName);
            tvSpellCooldown = (TextView) itemView.findViewById(R.id.tvSpellCooldown);
            tvSpellCost = (TextView) itemView.findViewById(R.id.tvSpellCost);
            tvSpellRange = (TextView) itemView.findViewById(R.id.tvSpellRange);
            tvSpellTitle = (TextView) itemView.findViewById(R.id.tvSpellTitle);
            ivSpellIcon = (ImageView) itemView.findViewById(R.id.ivSpellIcon);
            this.itemView  = itemView;
        }

        public TextView getTvSpellName() {
            return tvSpellName;
        }

        public void setTvSpellName(TextView tvSpellName) {
            this.tvSpellName = tvSpellName;
        }

        public TextView getTvSpellCooldown() {
            return tvSpellCooldown;
        }

        public void setTvSpellCooldown(TextView tvSpellCooldown) {
            this.tvSpellCooldown = tvSpellCooldown;
        }

        public TextView getTvSpellCost() {
            return tvSpellCost;
        }

        public void setTvSpellCost(TextView tvSpellCost) {
            this.tvSpellCost = tvSpellCost;
        }

        public TextView getTvSpellRange() {
            return tvSpellRange;
        }

        public void setTvSpellRange(TextView tvSpellRange) {
            this.tvSpellRange = tvSpellRange;
        }

        public TextView getTvSpellTitle() {
            return tvSpellTitle;
        }

        public void setTvSpellTitle(TextView tvSpellTitle) {
            this.tvSpellTitle = tvSpellTitle;
        }

        public ImageView getIvSpellIcon() {
            return ivSpellIcon;
        }

        public void setIvSpellIcon(ImageView ivSpellIcon) {
            this.ivSpellIcon = ivSpellIcon;
        }

        public View getItemView() {
            return itemView;
        }

        public void setItemView(View itemView) {
            this.itemView = itemView;
        }
    }

    public class PassiveViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPassiveName, tvPassiveTitle;
        private ImageView ivPassiveIcon;
        private View itemView;

        public PassiveViewHolder(View itemView) {
            super(itemView);
            tvPassiveName = (TextView) itemView.findViewById(R.id.tvPassiveName);
            tvPassiveTitle = (TextView) itemView.findViewById(R.id.tvPassiveTitle);
            ivPassiveIcon = (ImageView) itemView.findViewById(R.id.ivPassiveIcon);
            this.itemView  = itemView;
        }

        public TextView getTvPassiveName() {
            return tvPassiveName;
        }

        public void setTvPassiveName(TextView tvPassiveName) {
            this.tvPassiveName = tvPassiveName;
        }

        public TextView getTvPassiveTitle() {
            return tvPassiveTitle;
        }

        public void setTvPassiveTitle(TextView tvPassiveTitle) {
            this.tvPassiveTitle = tvPassiveTitle;
        }

        public ImageView getIvPassiveIcon() {
            return ivPassiveIcon;
        }

        public void setIvPassiveIcon(ImageView ivPassiveIcon) {
            this.ivPassiveIcon = ivPassiveIcon;
        }

        public View getItemView() {
            return itemView;
        }

        public void setItemView(View itemView) {
            this.itemView = itemView;
        }
    }

    public interface IAbilitesClickListenner {
        public void onItemClickListenner(ChampionEnity championEnity, int position);
    }
}
