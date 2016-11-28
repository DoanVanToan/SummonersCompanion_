package com.toandoan.lol.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.toandoan.lol.R;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by framgia on 28/11/2016.
 */

public class RegionDialog extends Dialog implements AdapterView.OnItemClickListener {
    @BindView(R.id.region_listview)
    ListView mRegionListview;

    private List<String> mRegionNames;
    private List<String> mRegionCodes;
    private ArrayAdapter<String> mAdaper;
    private OnItemClickListenner mListenner;

    public RegionDialog(Context context, OnItemClickListenner listenner) {
        super(context);
        mListenner = listenner;
        init();
    }

    public RegionDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected RegionDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.region_dialog_layout);
        ButterKnife.bind(this);
        mRegionNames = Arrays.asList(getContext().getResources().getStringArray(R.array.region_name));
        mRegionCodes = Arrays.asList(getContext().getResources().getStringArray(R.array.region_code));
        mAdaper = new ArrayAdapter<String>(getContext(), R.layout.region_item_layout, mRegionNames);
        mRegionListview.setAdapter(mAdaper);
        mRegionListview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String code = mRegionCodes.get(position);
        mListenner.onItemClick(position, code);
        dismiss();
    }

    public interface OnItemClickListenner {
        void onItemClick(int position, String code);
    }
}
