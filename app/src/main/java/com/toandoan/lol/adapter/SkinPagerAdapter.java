package com.toandoan.lol.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.toandoan.lol.R;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.model.champion.ChampionSkinEnity;
import com.toandoan.lol.utility.LogUtil;
import com.toandoan.lol.utility.Utils;

import java.util.List;

/**
 * Created by Who are you on 13-Jun-16.
 */
public class SkinPagerAdapter extends PagerAdapter {
    private final Context mContext;
    private ChampionEnity championEnity;

    public SkinPagerAdapter(Context ctx, ChampionEnity championEnity) {
        this.championEnity = championEnity;
        mContext = ctx;
    }

    public void SetnotifyDataSetChanged(ChampionEnity championEnity) {
        this.championEnity = championEnity;
        notifyDataSetChanged();
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public ChampionSkinEnity getItemAtPosition(int position) {
        return championEnity.getSkins().get(position);
    }

    @Override
    public int getCount() {
        return championEnity.getSkins().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LogUtil.e("instantiateItem_SkinAdapter", Utils.RiotStatic.getChampionCover(championEnity, getItemAtPosition(position).getNum()));
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.sale_item, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.ivSaleItem);
        RelativeLayout rlLoading = (RelativeLayout) itemView.findViewById(R.id.rlLoading);

        Utils.loadAvatarNomal(mContext, Utils.RiotStatic.getChampionCover(championEnity, getItemAtPosition(position).getNum()), imageView, rlLoading);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
