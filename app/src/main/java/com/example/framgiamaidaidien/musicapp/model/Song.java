package com.example.framgiamaidaidien.musicapp.model;

/**
 * Created by FRAMGIA\mai.dai.dien on 30/12/2016.
 */

public class Song {
    private String mNameOfSong;
    private String mNameOfArtist;
    private long mLengthOfSong;

    public Song(){}

    public Song(String nameOfSong, String nameOfArtist, long lengthOfSong) {
        this.mNameOfSong = nameOfSong;
        this.mNameOfArtist = nameOfArtist;
        this.mLengthOfSong = lengthOfSong;
    }

    public String getNameOfSong() {
        return mNameOfSong;
    }

    public void setNameOfSong(String nameOfSong) {
        mNameOfSong = nameOfSong;
    }

    public String getNameOfArtist() {
        return mNameOfArtist;
    }

    public void setNameOfArtist(String nameOfArtist) {
        mNameOfArtist = nameOfArtist;
    }

    public long getLengthOfSong() {
        return mLengthOfSong;
    }

    public void setLengthOfSong(long lengthOfSong) {
        mLengthOfSong = lengthOfSong;
    }
}
