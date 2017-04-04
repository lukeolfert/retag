package com.example.luke.retag.mediaTypes;

/**
 * Created by Luke on 2017-04-02.
 */

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Retrieves and organizes media to play. Before being used, you must call {@link #prepare()},
 * which will retrieve all of the music on the user's device (by performing a query on a content
 * resolver). After that, it's ready to retrieve a random song, with its title and URI, upon
 * request.
 */
public class Albums {

    final String TAG = "MusicRetriever";
    ContentResolver mContentResolver;
    // the items (songs) we have queried
    List<Album> mItems = new ArrayList<Album>();
    Random mRandom = new Random();

    public Albums (ContentResolver cr) {
        mContentResolver = cr;
    }
    /**
     * Loads music data. This method may take long, so be sure to call it asynchronously without
     * blocking the main thread.
     */
    public void prepare() {
        Uri uri = android.provider.MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        Log.i(TAG, "Querying Albums...");
        Log.i(TAG, "URI: " + uri.toString());
        // Perform a query on the content resolver. The URI we're passing specifies that we
        // want to query for all audio media on external storage (e.g. SD card)
        Cursor cur = mContentResolver.query(uri, null,
                MediaStore.Audio.Media.IS_MUSIC + " = 1", null, null);
        Log.i(TAG, "Query finished. " + (cur == null ? "Returned NULL." : "Returned a cursor."));
        if (cur == null) {
            // Query failed...
            Log.e(TAG, "Failed to retrieve music: cursor is null :-(");
            return;
        }
        if (!cur.moveToFirst()) {
            // Nothing to query. There is no music on the device. How boring.
            Log.e(TAG, "Failed to move cursor to first row (no query results).");
            return;
        }
        Log.i(TAG, "Listing...");
        // retrieve the indices of the columns where the ID, title, etc. of the song are

        int idColumn = cur.getColumnIndex(MediaStore.Audio.Albums._ID);
        int artistColumn = cur.getColumnIndex(MediaStore.Audio.Albums.ARTIST);
        int titleColumn = cur.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
        int albumColumn = cur.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ID);
        int numSongsColumn = cur.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS);
        Bitmap albumcover = BitmapFactory.decodeFile(cur.getString(cur.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART)));

        Log.i(TAG, "Title column index: " + String.valueOf(titleColumn));
        Log.i(TAG, "ID column index: " + String.valueOf(titleColumn));
        // add each song to mItems
        do {
            Log.i(TAG, "ID: " + cur.getString(idColumn) + " Title: " + cur.getString(titleColumn));
            mItems.add(new Album(
                    cur.getLong(idColumn),
                    cur.getString(artistColumn),
                    cur.getString(titleColumn),
                    cur.getString(albumColumn),
                    cur.getInt(numSongsColumn),
                    albumcover));

        } while (cur.moveToNext());
        Log.i(TAG, "Done querying Albums. MusicRetriever is ready.");
    }

    public ContentResolver getContentResolver() {
        return mContentResolver;
    }
    /** Returns a random Item. If there are no items available, returns null. */
    public Album getRandomItem() {
        if (mItems.size() <= 0) return null;
        return mItems.get(mRandom.nextInt(mItems.size()));
    }

    public static class Album {

        long id;
        String artist;
        String title;
        String album;
        long duration;
        Bitmap albumCover;

        public Album(long id, String artist, String title, String album, long duration, Bitmap albumcover) {

            this.id = id;
            this.artist = artist;
            this.title = title;
            this.album = album;
            this.duration = duration;
            this.albumCover = albumcover;

        }
        public long getId() {
            return id;
        }
        public String getArtist() {
            return artist;
        }
        public String getTitle() {
            return title;
        }
        public String getAlbum() {
            return album;
        }
        public long getDuration() {
            return duration;
        }

        public Bitmap getCover() {
            return albumCover;
        }

        public Uri getURI() {
            return ContentUris.withAppendedId(
                    android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
        }
    }
}
