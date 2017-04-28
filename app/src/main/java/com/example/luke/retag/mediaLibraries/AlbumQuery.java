package com.example.luke.retag.mediaLibraries;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

public class AlbumQuery {

    static ArrayList<Album> albumLibrary = new ArrayList<>();

    static public ArrayList<Album> fetchAlbumLibrary(ContentResolver cr) {

            Uri musicUri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
            ContentResolver musicResolver = cr;
            Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

            while (musicCursor.moveToNext()) {

                int myAlbumName = musicCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
                int myIDColumn = musicCursor.getColumnIndex(MediaStore.Audio.Albums._ID);
                int myArtistName = musicCursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST);
                int myAlbumCover = musicCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);
                int myAlbumSongCount = musicCursor.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS);


                long thisId = musicCursor.getLong(myIDColumn);

                String albumNameCur = musicCursor.getString(myAlbumName);
                String artistNameCur = musicCursor.getString(myArtistName);
                String albumCoverCur = musicCursor.getString(myAlbumCover);

                Log.v(null, "Indexing - " + albumNameCur);

                albumLibrary.add(new Album(albumNameCur, artistNameCur, myAlbumSongCount, albumCoverCur));

            }

        return albumLibrary;
    }
}

