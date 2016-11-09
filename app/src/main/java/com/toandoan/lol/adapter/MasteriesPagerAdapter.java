package com.toandoan.lol.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.toandoan.lol.R;
import com.toandoan.lol.database.dao.MasteryDAO;
import com.toandoan.lol.database.impl.MasteriesImpl;
import com.toandoan.lol.listenner.MasteriesListenner;
import com.toandoan.lol.model.MasteryEnity;
import com.toandoan.lol.utility.Utils;

import java.util.List;

/**
 * Created by ToanDoan on 11/4/2016.
 */

public class MasteriesPagerAdapter extends PagerAdapter {
    private final int MASTERY_COUNT = 3;
    private Context mContext;
    private MasteriesListenner listenner;
    private List<MasteryEnity> activeMasteries;
    private final int CUNING = 1;
    private final int RESOLVE = 2;
    private final int FEROCITY = 0;
    private String[] tabtitle;

    public MasteriesPagerAdapter(Context mContext, List<MasteryEnity> activeMasteries, MasteriesListenner listenner) {
        this.mContext = mContext;
        this.activeMasteries = activeMasteries;
        this.listenner = listenner;
        this.tabtitle = new String[]{mContext.getString(R.string.cuning),
                mContext.getString(R.string.resolve),
                mContext.getString(R.string.ferocity)};
    }

    @Override
    public int getCount() {
        return MASTERY_COUNT;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup viewGroup = null;
        switch (position) {
            case CUNING:
                viewGroup = (ViewGroup) LayoutInflater.from(container.getContext())
                        .inflate(R.layout.mastery_cuning_item_layout, null);
                break;
            case RESOLVE:
                viewGroup = (ViewGroup) LayoutInflater.from(container.getContext())
                        .inflate(R.layout.mastery_resolve_item_layout, null);
                break;
            case FEROCITY:
                viewGroup = (ViewGroup) LayoutInflater.from(container.getContext())
                        .inflate(R.layout.mastery_ferocity_item_layout, null);
                break;
        }

        updateUIAtPosition(viewGroup, position);

        container.addView(viewGroup);
        return viewGroup;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ViewGroup) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitle[position];
    }

    private void updateUIAtPosition(View view, int position) {
        MasteriesImpl masterieDatabase = new MasteriesImpl(mContext);
        switch (position) {
            case CUNING:
                List<MasteryEnity> masteryEnities = masterieDatabase.getMasteriesByType(MasteryDAO.TYPE_CUNNING);
                binData(view, masteryEnities);
                break;
            case RESOLVE:
                masteryEnities = masterieDatabase.getMasteriesByType(MasteryDAO.TYPE_RESOLVE);
                binData(view, masteryEnities);
                break;
            case FEROCITY:
                masteryEnities = masterieDatabase.getMasteriesByType(MasteryDAO.TYPE_FEROCITY);
                binData(view, masteryEnities);
                break;
        }
    }

    private void binData(View view, final List<MasteryEnity> masteries) {
        if (masteries != null && masteries.size() != 0) {
            for (int i = 0; i < masteries.size(); i++) {
                ImageView ivTitle = (ImageView) view.findViewWithTag(masteries.get(i).getId() + "");
                if (ivTitle != null) {
                    ivTitle.setImageBitmap(Utils.getMasteryImageFromAssets(mContext, false, masteries.get(i).getImage().getFull()));
                    final int finalI = i;
                    ivTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listenner.onItemClickListenner(masteries.get(finalI));
                        }
                    });
                }

            }
        }

        if (activeMasteries != null && activeMasteries.size() != 0) {
            for (int i = 0; i < activeMasteries.size(); i++) {
                ImageView ivTitle = (ImageView) view.findViewWithTag(activeMasteries.get(i).getId() + "");
                if (ivTitle != null) {
                    ivTitle.setImageBitmap(Utils.getMasteryImageFromAssets(mContext, true, activeMasteries.get(i).getImage().getFull()));
                }

            }
        }


    }
}
