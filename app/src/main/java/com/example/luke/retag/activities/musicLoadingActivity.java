package com.example.luke.retag.activities;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import com.example.luke.retag.R;
import com.example.luke.retag.customAdapters.dataTransfer;
import com.example.luke.retag.mediaTypes.ASyncResponse;
import com.example.luke.retag.mediaTypes.Albums;
import com.example.luke.retag.mediaTypes.Artists;
import com.example.luke.retag.mediaTypes.LibraryHolder;
import com.example.luke.retag.mediaTypes.SaveState;
import com.example.luke.retag.mediaTypes.Songs;

public class musicLoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_music_loading);

        //ImageView musicLoadingIcon = (ImageView) findViewById(R.id.imageView);

        // Fetching Activity Context for Content Resolver
        Context context = musicLoadingActivity.this;
        ContentResolver cr = context.getContentResolver();

        // Initializing Asyncronous Task

        getMusic task = new getMusic(cr,

                new ASyncResponse() {

                    @Override
                    public void processFinish(LibraryHolder library) {

                        SaveState.library.add(library);
                        SaveState.saveData(SaveState.getInstance());

                        //dataTransfer data = new dataTransfer();
                        //data.setLibrary(library);

                        Intent intent = new Intent(musicLoadingActivity.this, MusicLibraryActivity.class);
                        startActivity(intent);

                    }
                });

        task.execute(new Object[] { "Youe request to aynchronous task class is giving here.." });

    }
}

class getMusic extends AsyncTask<Object, Object, LibraryHolder> {

    public LibraryHolder library;
    ContentResolver myResolver;
    public ASyncResponse delegate = null; //Call back interface

    //ImageView loadingIcon;

    // Setting Instance Variables upon Constructor Call

    public getMusic(ContentResolver cr, ASyncResponse response) {
        super();

        //this.loadingIcon = loadingIcon;
        myResolver = cr;
        delegate = response;
    }

    @Override
    protected LibraryHolder doInBackground(Object... ContentResolver) {

        Albums albumIndex = new Albums(myResolver);
        albumIndex.prepare();

        Songs songIndex = new Songs(myResolver);
        songIndex.prepare();

        Artists artistsIndex = new Artists(myResolver);
        artistsIndex.prepare();

        this.library = new LibraryHolder(songIndex.getSongLibrary(),
                                         artistsIndex.getArtistLibrary(),
                                         albumIndex.getAlbumLibrary());

        return library;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(LibraryHolder result) {

        delegate.processFinish(result);

    }

    @Override
    protected void onProgressUpdate(Object... values) {

    }

    public LibraryHolder getLibrary() {
        return library;
    }
}