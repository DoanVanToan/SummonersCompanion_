package com.toandoan.lol.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toandoan.lol.R;
import com.toandoan.lol.activity.VideoActivity;
import com.toandoan.lol.adapter.AbilitesAdapter;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.utility.DownloadTask;
import com.toandoan.lol.utility.FileOperations;
import com.toandoan.lol.utility.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbilitiesFragment extends Fragment {
    private RecyclerView rvAbilities;
    private ChampionEnity championEnity;
    private AbilitesAdapter adapter;
    private FileOperations fileOperations;

    public static AbilitiesFragment newInstance(ChampionEnity championEnity) {
        AbilitiesFragment abilitiesFragment = new AbilitiesFragment();
        abilitiesFragment.setChampionEnity(championEnity);
        return abilitiesFragment;
    }

    public AbilitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_abilities, container, false);
        initViews(v);
        return v;
    }

    private void initViews(View v) {
        fileOperations = new FileOperations(getContext());
        rvAbilities = (RecyclerView) v.findViewById(R.id.rvAbilities);
        adapter = new AbilitesAdapter(getContext(), championEnity, listenner);
        rvAbilities.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAbilities.setAdapter(adapter);
    }

    public ChampionEnity getChampionEnity() {
        return championEnity;
    }

    public void setChampionEnity(ChampionEnity championEnity) {
        this.championEnity = championEnity;
    }

    /**
     * Listenner
     */
    private AbilitesAdapter.IAbilitesClickListenner listenner = new AbilitesAdapter.IAbilitesClickListenner() {
        @Override
        public void onItemClickListenner(ChampionEnity championEnity, int position) {
            String urlVideo;
            String title;

            if (fileOperations.getVideoFile(Utils.RiotStatic.getChampionAbilitesFileName(championEnity, position+1)) != null) {
                urlVideo = fileOperations.getVideoFile(Utils.RiotStatic.getChampionAbilitesFileName(championEnity, position + 1));
            } else {
                urlVideo = Utils.RiotStatic.getChampionAbiliteUrlVideo(championEnity, position + 1);
                DownloadTask downloadTask = new DownloadTask(getContext(), Utils.RiotStatic.getChampionAbilitesFileName(championEnity, position + 1));
                downloadTask.execute(urlVideo);
            }

            if (position == 0) {
                title = championEnity.getPassive().getName();
            } else {
                title = championEnity.getSpells().get(position - 1).getName();
            }

            Intent i = new Intent(getActivity(), VideoActivity.class);
            i.putExtra(Constant.IntentKey.URL_VIDEO, urlVideo);
            i.putExtra(Constant.IntentKey.URL_THUMB_VIDEO, Utils.RiotStatic.getChampionAbiliteThumbUrlVideo(championEnity, position + 1));
            i.putExtra(Constant.IntentKey.TITLE, title);
            startActivity(i);
        }
    };
}
