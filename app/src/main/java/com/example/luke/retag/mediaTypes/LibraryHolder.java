package com.example.luke.retag.mediaTypes;

import java.util.List;

/**
 * Created by Luke on 2017-04-06.
 */

public class LibraryHolder {

    List<Songs.Song> songLibrary;
    List<Artists.Artist> artistLibrary;
    List<Albums.Album> albumLibrary;

    public LibraryHolder(List<Songs.Song> songs, List<Artists.Artist> artists, List<Albums.Album> albums) {

        this.albumLibrary = albums;
        this.artistLibrary = artists;
        this.songLibrary = songs;

    }

    public List<Songs.Song> getSongLibrary() {
        return this.songLibrary;
    }

    public List<Albums.Album> getAlbumLibrary() {
        return this.albumLibrary;
    }

    public List<Artists.Artist> getArtistLibrary() {
        return this.artistLibrary;
    }
}
