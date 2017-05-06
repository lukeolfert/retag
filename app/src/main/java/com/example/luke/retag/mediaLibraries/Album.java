package com.example.luke.retag.mediaLibraries;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Album implements Serializable {

    String albumName, albumArtist, albumArtworkPath;
    int albumURI, songCount;
    transient Drawable albumArtwork;

    public Album(String albumName, String albumArtist,
                 int songCount,String albumArtPath) {

        this.albumName = albumName;
        this.albumArtist = albumArtist;
        this.songCount = songCount;
        this.albumArtworkPath = albumArtPath;

    }

    public int getAlbumuri() {
        return albumURI;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public int getSongCount() {
        return songCount;
    }

    public Drawable getAlbumArtwork() {
        return albumArtwork;
    }

    public void setAlbumArt() {

        this.albumArtwork = Drawable.createFromPath(albumArtworkPath);

    }
}
