package com.example.framgiamaidaidien.musicapp;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.example.framgiamaidaidien.musicapp.adapter.SimpleDividerItemDecoration;
import com.example.framgiamaidaidien.musicapp.adapter.SongsAdapter;
import com.example.framgiamaidaidien.musicapp.model.Song;
import com.example.framgiamaidaidien.musicapp.ui.CircleProgressFAB;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final LinearInterpolator LINEAR_INTERPOLATOR = new LinearInterpolator();

    private CircleProgressFAB mCircleProgressFAB;
    private FloatingActionButton mPlayButton;
    private RecyclerView mSongsRecyclerView;

    ObjectAnimator progressCircleAnimator;

    private boolean mIsPlay, mIsFirstPlay = true;
    private List<Song> mSongs;
    private SongsAdapter mSongsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setClickListener();
    }

    private void init() {
        mCircleProgressFAB = (CircleProgressFAB) findViewById(R.id.song_playing_fab);
        mPlayButton = (FloatingActionButton) findViewById(R.id.play_btn);
        mSongsRecyclerView = (RecyclerView) findViewById(R.id.list_song);

        mSongs = new ArrayList<>();
        mSongs.add(new Song("s", "s", 12));
        mSongs.add(new Song("s", "s", 12));
        mSongs.add(new Song("s", "s", 12));
        mSongs.add(new Song("s", "s", 12));
        mSongs.add(new Song("s", "s", 12));
        mSongs.add(new Song("s", "s", 12));
        mSongs.add(new Song("s", "s", 12));

        mSongsAdapter = new SongsAdapter(this, mSongs);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mSongsRecyclerView.setLayoutManager(linearLayoutManager);
        // add divider to recyclerview
        mSongsRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        // set adapter to recyclerview
        mSongsRecyclerView.setAdapter(mSongsAdapter);
    }

    private void setClickListener() {
        mPlayButton.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play_btn:
                if (mIsFirstPlay) {
                    startAnimationProgress();
                } else if (!mIsPlay){
                    resumeAnimationProgress();
                } else {
                    pauseAnimationProgress();
                }
                break;
        }
    }

    private void startAnimationProgress() {
        mPlayButton.setImageResource(android.R.drawable.ic_media_pause);
        mIsFirstPlay = false;
        mIsPlay = true;
        if (null != progressCircleAnimator) {
            progressCircleAnimator.cancel();
            progressCircleAnimator = null;
        }
        progressCircleAnimator = ObjectAnimator.ofFloat(mCircleProgressFAB, CircleProgressFAB.DEGREE_PROGRESS, 0, 360);
        progressCircleAnimator.setDuration(25000);
        progressCircleAnimator.setInterpolator(LINEAR_INTERPOLATOR);
        progressCircleAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Toast.makeText(MainActivity.this, "End animation!", Toast.LENGTH_SHORT).show();
                // reset progress
                mPlayButton.setImageResource(android.R.drawable.ic_media_play);
                mCircleProgressFAB.setClearProgress(true);
                mIsFirstPlay = true;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        progressCircleAnimator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void resumeAnimationProgress() {
        mPlayButton.setImageResource(android.R.drawable.ic_media_pause);
        mIsPlay = true;
        progressCircleAnimator.resume();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void pauseAnimationProgress() {
        if (progressCircleAnimator == null) return;
        mPlayButton.setImageResource(android.R.drawable.ic_media_play);
        progressCircleAnimator.pause();
        mIsPlay = false;
    }
}
