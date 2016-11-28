package com.toandoan.lol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.toandoan.lol.R;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.base.BaseFragment;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.fragment.ItemFragment;
import com.toandoan.lol.fragment.MasteriesFragment;
import com.toandoan.lol.fragment.RunesFragment;
import com.toandoan.lol.fragment.SearchSumonerFragment;
import com.toandoan.lol.fragment.SelectChampionFragment;
import com.toandoan.lol.fragment.SumonerOverviewFragment;
import com.toandoan.lol.utility.Utils;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SelectChampionFragment mSelectChampionFragment;
    private ItemFragment mItemFragment;
    private RunesFragment mRunesFragment;
    private SearchView mSearchView;
    private MasteriesFragment mMasteriesFragment;
    private SearchSumonerFragment mSumonerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initUserViews();
        Utils.getDataBaseFromEmuler(getApplicationContext(), "data.db");
    }


    private void initUserViews() {
        mSumonerFragment = SearchSumonerFragment.newInstance();
        mSelectChampionFragment = SelectChampionFragment.newInstance();
        mItemFragment = ItemFragment.newInstance();
        mRunesFragment = RunesFragment.newInstance();
        mMasteriesFragment = MasteriesFragment.newInstance();

        changeFramget(mSumonerFragment);
        updateToolBar(R.string.sumoner);
    }

    private void updateToolBar(int resourceID){
        getSupportActionBar().setTitle(resourceID);
    }

    public void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SumonerRunesActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void changeFramget(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flContent, fragment);
        fragmentTransaction.commit();
        if (mSearchView != null) mSearchView.onActionViewCollapsed();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuSearch = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) menuSearch.getActionView();
        mSearchView.setQueryHint(getResources().getString(R.string.search_hint));
        mSearchView.setOnQueryTextListener(onQueryTextListener);
        mSearchView.setVisibility(View.GONE);
        return true;
    }



    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.flContent);
            if (currentFragment instanceof BaseFragment) {
                ((BaseFragment) currentFragment).onSearchSubmit(query);
            }
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.flContent);
            if (currentFragment instanceof BaseFragment) {
                ((BaseFragment) currentFragment).onSearch(newText);
            }
            return false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_sumonners:
                changeFramget(mSumonerFragment);
                mSearchView.setVisibility(View.GONE);
                updateToolBar(R.string.sumoner);
                break;
            case R.id.nav_champions:
                changeFramget(mSelectChampionFragment);
                mSearchView.setVisibility(View.VISIBLE);
                updateToolBar(R.string.champions_);
                break;
            case R.id.nav_items:
                changeFramget(mItemFragment);
                mSearchView.setVisibility(View.VISIBLE);
                updateToolBar(R.string.items);
                break;
            case R.id.nav_runes:
                changeFramget(mRunesFragment);
                mSearchView.setVisibility(View.VISIBLE);
                updateToolBar(R.string.runes);
                break;
            case R.id.nav_masteries:
                changeFramget(mMasteriesFragment);
                mSearchView.setVisibility(View.GONE);
                updateToolBar(R.string.mastery);
                break;
            case R.id.nav_challange:
                ChallengerActivity.startActivity(this, null, Constant.SumonerStaticData.CHALLENGE_RANK);
                mSearchView.setVisibility(View.VISIBLE);
                break;
            case R.id.nav_share:

                break;
            case R.id.nav_send:

                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
