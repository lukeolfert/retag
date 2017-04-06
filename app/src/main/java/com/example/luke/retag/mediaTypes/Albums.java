package com.example.luke.retag.mediaTypes;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
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

    public Albums(ContentResolver cr) {
        mContentResolver = cr;
    }
    /**
     * Loads music data. This method may take long, so be sure to call it asynchronously without
     * blocking the main thread.
     */
    public void prepare() {
        Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        Log.i(TAG, "Querying media...");
        Log.i(TAG, "URI: " + uri.toString());
        // Perform a query on the content resolver. The URI we're passing specifies that we
        // want to query for all audio media on external storage (e.g. SD card)
        Cursor cur = mContentResolver.query(uri, null, null, null, MediaStore.Audio.Albums.ALBUM + " ASC");
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
        int artistName = cur.getColumnIndex(MediaStore.Audio.Albums.ARTIST);
        int albumName = cur.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
        int songCount = cur.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS);
        int artistId = cur.getColumnIndex(MediaStore.Audio.Albums._ID);
        Log.i(TAG, "Title column index: " + String.valueOf(albumName));
        Log.i(TAG, "ID column index: " + String.valueOf(albumName));
        // add each song to mItems
        do {
            Log.i(TAG, "ID: " + cur.getString(artistId) + " Title: " + cur.getString(albumName));
            mItems.add(new Album(
                    cur.getLong(artistId),
                    cur.getString(artistName),
                    cur.getString(albumName),
                    cur.getLong(songCount)));
        } while (cur.moveToNext());
        Log.i(TAG, "Done querying media. MusicRetriever is ready.");
    }

    public List<Album> getAlbumLibrary() {
        return mItems;
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

        public Album(long id, String artist, String albumName, long songCount) {

            this.id = id;
            this.artist = artist;
            this.title = albumName;
            this.album = album;
            this.duration = songCount;

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
        public Uri getURI() {
            return ContentUris.withAppendedId(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
        }
    }
}
