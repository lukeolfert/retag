package com.example.luke.retag.mediaLibraries;

import java.io.Serializable;

public class Album implements Serializable{

    String albumName, albumArtist, albumArtwork;
    int albumURI, songCount;

    public Album(String albumName, String albumArtist,
                 int songCount, String albumArtwork) {

        this.albumName = albumName;
        this.albumArtist = albumArtist;
        this.songCount = songCount;
        this.albumURI = albumURI;
        this.albumArtwork = albumArtwork;

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

    public String getAlbumArtwork() {
        return albumArtwork;
    }
}
