package com.toandoan.lol.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.toandoan.lol.R;
import com.toandoan.lol.activity.ChampionDetailActivity;
import com.toandoan.lol.activity.SumonerDetailActivity;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.base.BaseFragment;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.presenter.UserOverviewPresenter;
import com.toandoan.lol.listenner.IUserOverviewListenner;
import com.toandoan.lol.model.UserEnity;
import com.toandoan.lol.utility.LogUtil;
import com.toandoan.lol.utility.Utils;

import org.json.JSONObject;

import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserOverviewFragment extends BaseFragment {
    private ImageView ivAvatar;
    private TextView tvName, tvLevel;
    private Context mContext;
    private UserOverviewPresenter helper;
    private BaseActivity mActivity;

    public static UserOverviewFragment newInstance() {
        UserOverviewFragment userOverviewFragment = new UserOverviewFragment();
        return userOverviewFragment;
    }

    public UserOverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_overview, container, false);
        initViews(view);
        return view;
    }


    private void initViews(View v) {
        mActivity = (BaseActivity) getActivity();
        ivAvatar = (ImageView) v.findViewById(R.id.ivAvt);
        tvLevel = (TextView) v.findViewById(R.id.tvLevel);
        tvName = (TextView) v.findViewById(R.id.tvName);
        mContext = getContext();

        initHelper();
    }

    private void initHelper() {
        helper = new UserOverviewPresenter(mActivity, iUserOverviewListenner);

    }

    /**
     * Listenner
     */
    public IUserOverviewListenner iUserOverviewListenner = new IUserOverviewListenner() {
        @Override
        public void searchUserByNameSuccess(JSONObject response) {
            LogUtil.e("searchUserByNameSuccess>>>", response.toString());
            Iterator<String> iter = response.keys();
            String key = iter.next();
            JSONObject user = (JSONObject) response.opt(key);

            if (user != null) {
                UserEnity userEnity = new UserEnity(user);
                Glide.with(mContext)
                        .load(Utils.RiotStatic.getProfileIcon(userEnity.getProfileIconId()))
                        .into(ivAvatar);
                tvName.setText(userEnity.getName());
                tvLevel.setText("- " + userEnity.getSummonerLevel());

                SumonerDetailActivity.startActivity(getContext(), userEnity);
            }else {

            }


        }


        @Override
        public void searchUserByNameFail(String message) {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onSearch(String key) {

    }

    @Override
    public void onSearchSubmit(String key) {
        helper.callApiSearch(Constant.Region.NORTH_AMERICA, key);
    }
}
