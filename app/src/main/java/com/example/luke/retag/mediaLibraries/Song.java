package com.example.luke.retag.mediaLibraries;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Song implements Serializable {

    String songName, songArtist, songArtwork, songDuration;
    int songURI;

    public Song (String songName, String songArtist, String songArtwork, String songDuration, int songURI) {

        this.songName = songArtist;
        this.songArtist = songArtist;
        this.songArtwork = songArtwork;
        this.songDuration = songDuration;
        this.songURI = songURI;

    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongArtwork() {
        return songArtwork;
    }

    public void setSongArtwork(String songArtwork) {
        this.songArtwork = songArtwork;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public int getSongURI() {
        return songURI;
    }

    public void setSongURI(int songURI) {
        this.songURI = songURI;
    }
}
