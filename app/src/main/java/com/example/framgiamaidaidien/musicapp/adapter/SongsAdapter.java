package com.example.framgiamaidaidien.musicapp.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framgiamaidaidien.musicapp.R;
import com.example.framgiamaidaidien.musicapp.model.Song;

import java.util.List;

/**
 * Created by FRAMGIA\mai.dai.dien on 30/12/2016.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<Song> mSongs;

    public SongsAdapter(Context context, List<Song> songs) {
        this.mContext = context;
        this.mSongs = songs;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = mLayoutInflater.inflate(R.layout.list_item_view_song, parent, false);
        return new SongViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return (mSongs == null) ? 0 : mSongs.size();
    }

    class SongViewHolder extends RecyclerView.ViewHolder {
        public ImageView mThumbImageView;
        public TextView mTitleSongTextView, mArtistSongTextView, mLengthOfSongTextView;

        public SongViewHolder(View view) {
            super(view);
            mThumbImageView = (ImageView) view.findViewById(R.id.thumb_image);
            mTitleSongTextView = (TextView) view.findViewById(R.id.title_song);
            mArtistSongTextView = (TextView) view.findViewById(R.id.artist_song);
            mLengthOfSongTextView = (TextView) view.findViewById(R.id.length_of_song);
        }
    }
}
