package com.example.luke.retag.activities;

import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.luke.retag.R;
import com.example.luke.retag.mediaTypes.Songs;

public class musicLoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_music_loading);

        new getMusic().execute();
    }
}

class getMusic extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... Context) {

        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {

    }

    @Override
    protected void onProgressUpdate(String... values) {

    }

}