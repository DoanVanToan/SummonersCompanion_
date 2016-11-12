package com.toandoan.lol.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toandoan.lol.R;
import com.toandoan.lol.adapter.ItemAdapter;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.base.BaseFragment;
import com.toandoan.lol.presenter.ItemsPresenter;
import com.toandoan.lol.listenner.ItemListenner;
import com.toandoan.lol.model.item.ItemEnity;
import com.toandoan.lol.utility.LogUtil;
import com.toandoan.lol.utility.Utils;
import com.toandoan.lol.widget.dialog.ItemDialog;

import java.util.ArrayList;
import java.util.List;


public class ItemFragment extends BaseFragment {
    private final String TAG = "ItemFragment";
    private ItemsPresenter mHelper;
    private RecyclerView mReyclerView;
    private List<ItemEnity> mListItem;
    private ItemAdapter mItemAdapter;
    private LinearLayoutManager mLayoutManager;
    private ItemDialog mItemDialog;


    public static ItemFragment newInstance() {
        ItemFragment itemFragment = new ItemFragment();

        return itemFragment;
    }

    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_item, container, false);
        initViews(v);
        return v;
    }

    private void initViews(View v) {
        mReyclerView = (RecyclerView) v.findViewById(R.id.mReyclerView);

        mHelper = new ItemsPresenter((BaseActivity) getActivity(), mListenner);
        mHelper.getALlItemFromDatabase();

    }

    private ItemListenner mListenner = new ItemListenner() {
        @Override
        public void getAllItemSuccessful(List<ItemEnity> listData) {
            LogUtil.e(TAG, "getAllItemSuccessful " + listData.size());
            mListItem = new ArrayList<>(listData);
            mItemAdapter = new ItemAdapter(getContext(), mListenner, mListItem);
            mLayoutManager = new LinearLayoutManager(getContext());
            mReyclerView.setLayoutManager(mLayoutManager);
            mReyclerView.setAdapter(mItemAdapter);
        }

        @Override
        public void getAllItemFail(String message) {
            Utils.show(getContext(), message);
        }

        @Override
        public void onItemClickListenner(View view, int position) {
            ItemEnity selectedItem = mListItem.get(position);
            mItemDialog = new ItemDialog(getContext());
            mItemDialog.setUp(selectedItem, mItemClickListenner);
            mItemDialog.show();
        }
    };

    private ItemDialog.ItemDialogOnClickListenner mItemClickListenner = new ItemDialog.ItemDialogOnClickListenner() {
        @Override
        public void onItemClickListenner(ItemEnity itemEnity) {
            if (mItemDialog != null && mItemDialog.isShowing()) {
                mItemDialog.setUp(itemEnity, mItemClickListenner);
            }
        }
    };

    @Override
    public void onSearch(String key) {
        Utils.show(getContext(), TAG + key);
    }


}
