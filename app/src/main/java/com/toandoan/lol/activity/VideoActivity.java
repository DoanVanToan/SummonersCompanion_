package com.toandoan.lol.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.toandoan.lol.R;
import com.toandoan.lol.base.BaseActivity;
import com.toandoan.lol.constant.Constant;
import com.toandoan.lol.utility.LogUtil;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoActivity extends BaseActivity {
    private String urlThumb;
    private String title;
    private String urlVideo;
    private JCVideoPlayerStandard jcVideoPlayerStandard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getDataIntent();
        initViews();
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            urlVideo = intent.getExtras().getString(Constant.IntentKey.URL_VIDEO);
            urlThumb = intent.getExtras().getString(Constant.IntentKey.URL_THUMB_VIDEO);
            title = intent.getExtras().getString(Constant.IntentKey.TITLE);

            LogUtil.e("VIDEO_ACTIVITY_URL_VIDEO", urlVideo);
            LogUtil.e("VIDEO_ACTIVITY_URL_THUMB", urlThumb);
            LogUtil.e("VIDEO_ACTIVITY_NAME", title);

        }
    }

    private void initViews() {
        jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.custom_videoplayer_standard);
        jcVideoPlayerStandard.setUp(urlVideo
                , title);
        Glide.with(activity)
                .load(urlThumb)
                .into(jcVideoPlayerStandard.thumbImageView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
