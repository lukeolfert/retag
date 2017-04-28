package com.example.luke.retag.activities;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.example.luke.retag.R;
import com.example.luke.retag.mediaLibraries.Album;
import com.example.luke.retag.mediaLibraries.AlbumQuery;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class musicLoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_music_loading);

        // Fetching Activity Context for Content Resolver

        Context context = musicLoadingActivity.this;
        ContentResolver cr = context.getContentResolver();

        // Initializing Asyncronous Task

        getMusic task = new getMusic(context, cr);
        task.execute();

    }
}

class getMusic extends AsyncTask<String, Void, String> {

    Context context;
    ContentResolver myResolver;
    ArrayList<Album> albumLibrary;

    // Setting Instance Variables upon Constructor Call

    public getMusic(Context ctx, ContentResolver cr) {
        super();

        context = ctx;
        myResolver = cr;
    }

    @Override
    protected String doInBackground(String... params) {
        albumLibrary = AlbumQuery.fetchAlbumLibrary(myResolver);

        try {

            FileOutputStream fos = context.openFileOutput("AlbumLibrary", Context.MODE_PRIVATE);
            ObjectOutputStream os = null;
            os = new ObjectOutputStream(fos);
            os.writeObject(albumLibrary);
            os.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Finished";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        context.startActivity(new Intent(context, MusicLibraryActivity.class));
    }
}