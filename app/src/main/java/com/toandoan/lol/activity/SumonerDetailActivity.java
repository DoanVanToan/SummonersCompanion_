package com.toandoan.lol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.toandoan.lol.R;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.fragment.SumonerMatchesListFragment;
import com.toandoan.lol.model.UserEnity;
import com.toandoan.lol.mvp_abstract.SumonerDetailAsbtract;
import com.toandoan.lol.utility.Utils;

public class SumonerDetailActivity extends BaseActivity implements SumonerDetailAsbtract.View, View.OnClickListener {

    private FloatingActionButton mRunesButton, mMasteriesButton;
    private Toolbar mToolbar;
    private UserEnity mUserEnity;
    private FloatingActionMenu mMenu;
    private SumonerMatchesListFragment mMatchesListFragment;


    public static void startActivity(Context context, UserEnity userID) {
        Intent intent = new Intent(context, SumonerDetailActivity.class);
        intent.putExtra(Constant.IntentKey.SUMONER, userID);
        context.startActivity(intent);
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            mUserEnity = (UserEnity) intent.getExtras().getSerializable(Constant.IntentKey.SUMONER);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumoner_detail);
        getData();
        initViews();
    }


    @Override
    public void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mUserEnity.getName());
        mRunesButton = (FloatingActionButton) findViewById(R.id.menu_runes);
        mMasteriesButton = (FloatingActionButton) findViewById(R.id.menu_masteries);
        mMenu = (FloatingActionMenu) findViewById(R.id.menu);
        mRunesButton.setOnClickListener(this);
        mMasteriesButton.setOnClickListener(this);

        mMatchesListFragment = SumonerMatchesListFragment.newInstance(mUserEnity);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.sumoner_frame_layout, mMatchesListFragment)
                .commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_runes:
                SumonerRunesActivity.startActivity(this, mUserEnity);
                mMenu.close(true);
                break;
            case R.id.menu_masteries:
                SumonerMasteriesActivity.startActivity(this, mUserEnity);
                mMenu.close(true);
                break;
            case R.id.menu:

                break;
        }
    }
}
