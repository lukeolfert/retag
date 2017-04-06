package com.example.luke.retag.activities;

import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import com.example.luke.retag.R;
import com.example.luke.retag.mediaTypes.Albums;
import com.example.luke.retag.mediaTypes.Artists;
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
        new getMusic(cr, null).execute();
    }
}

class getMusic extends AsyncTask<Void, Void, Void> {

    ContentResolver myResolver;
    //ImageView loadingIcon;

    // Setting Instance Variables upon Constructor Call

    public getMusic(ContentResolver cr, ImageView loadingIcon) {
        super();

        //this.loadingIcon = loadingIcon;
        this.myResolver = cr;
    }

    @Override
    protected Void doInBackground(Void... ContentResolver) {

        Albums albumIndex = new Albums(myResolver);
        albumIndex.prepare();

        Songs songIndex = new Songs(myResolver);
        songIndex.prepare();

        Artists artistsIndex = new Artists(myResolver);
        artistsIndex.prepare();

        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(Void result) {



    }

    @Override
    protected void onProgressUpdate(Void... values) {

    }

}