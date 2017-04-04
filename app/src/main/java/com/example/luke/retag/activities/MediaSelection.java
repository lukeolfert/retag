package com.example.luke.retag.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luke.retag.R;

public class MediaSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_media_selection);

        // View Initialization

        //ImageView videoIcon = (ImageView) findViewById(R.id.videoIcon);
        ImageView musicIcon = (ImageView) findViewById(R.id.musicIcon);
        TextView videoText = (TextView) findViewById(R.id.videoText);
        TextView musicText = (TextView) findViewById(R.id.musicText);

        // Setting Typefaces

        Typeface openLight = Typeface.createFromAsset(getAssets(), "fonts/openLight.ttf");
        videoText.setTypeface(openLight);
        musicText.setTypeface(openLight);

        musicIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaSelection.this, musicLoadingActivity.class);
                startActivity(intent);
            }

        });
    }
}
