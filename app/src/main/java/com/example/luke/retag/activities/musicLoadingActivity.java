package com.example.luke.retag.activities;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.luke.retag.R;
import com.example.luke.retag.customLayouts.CustomProgressBar;
import com.example.luke.retag.mediaLibraries.Album;
import com.example.luke.retag.mediaLibraries.AlbumQuery;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class musicLoadingActivity extends AppCompatActivity {

    private CustomProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_music_loading);

        TextView statusText = (TextView) findViewById(R.id.textView3);

        Animation fallAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fallin);
        Animation fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        Animation fadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);

        statusText.startAnimation(fadein);

        progressBar = (CustomProgressBar) findViewById(R.id.progressBar);
        progressBar.setAnimation(fallAnim);
        progressBar.startAnimation(fallAnim);

        // Fetching Activity Context for Content Resolver

        Context context = musicLoadingActivity.this;
        ContentResolver cr = context.getContentResolver();

        // Initializing Asyncronous Task

        getMusic task = new getMusic(context, cr, progressBar, statusText);
        task.execute();
    }
}

class getMusic extends AsyncTask<String, Integer, String> {

    Context context;
    ContentResolver myResolver;
    CustomProgressBar progressBar;
    TextView status;
    ArrayList<Album> albumLibrary;

    // Setting Instance Variables upon Constructor Call

    public getMusic(Context ctx, ContentResolver cr, CustomProgressBar pb, TextView textview) {
        super();

        progressBar = pb;
        context = ctx;
        myResolver = cr;
        status = textview;

    }

    @Override
    protected String doInBackground(String... params) {

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        albumLibrary = AlbumQuery.fetchAlbumLibrary(myResolver);

        for (int i = 0; i <= 40; i++) {

            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            publishProgress(i);
        }

        try {

            FileOutputStream fos = context.openFileOutput("AlbumLibrary", Context.MODE_PRIVATE);

            ObjectOutputStream os = null;
            os = new ObjectOutputStream(fos);

            for (int i = 40; i <= 60; i++) {

                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(i);
            }

            os.writeObject(albumLibrary);

            for (int i = 60; i <= 75; i++) {

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(i);
            }

            os.close();

            for (int i = 75; i <= 85; i++) {

                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(i);
            }

            fos.close();

            for (int i = 85; i <= 100; i++) {

                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Finished";
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        status.setText("Music Library Indexed");
        status.setTextColor(Color.parseColor("#47d77c"));

        context.startActivity(new Intent(context, MusicLibraryActivity.class));
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        progressBar.setProgress(values[0]);
    }
}