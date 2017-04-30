package com.example.luke.retag.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.luke.retag.FasterAnimationsContainer;
import com.example.luke.retag.R;

public class StartActivity extends AppCompatActivity {

    private static int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;
    FasterAnimationsContainer mFasterAnimationsContainer;
    private static final int[] IMAGE_RESOURCES = {

            R.drawable.frame_0037_1, R.drawable.frame_0036_1, R.drawable.frame_0035_1,
            R.drawable.frame_0034_1, R.drawable.frame_0033_1, R.drawable.frame_0032_1,
            R.drawable.frame_0031_1, R.drawable.frame_0030_1, R.drawable.frame_0029_1,
            R.drawable.frame_0028_1, R.drawable.frame_0027_1, R.drawable.frame_0026_1,
            R.drawable.frame_0025_1, R.drawable.frame_0024_1, R.drawable.frame_0023_1,
            R.drawable.frame_0022_1, R.drawable.frame_0021_1, R.drawable.frame_0020_1,
            R.drawable.frame_0019_1, R.drawable.frame_0018_1, R.drawable.frame_0017_1,
            R.drawable.frame_0016_1, R.drawable.frame_0015_1, R.drawable.frame_0014_1,
            R.drawable.frame_0013_1, R.drawable.frame_0012_1, R.drawable.frame_0011_1,
            R.drawable.frame_0010_1, R.drawable.frame_0009_1, R.drawable.frame_0008_1,
            R.drawable.frame_0007_1, R.drawable.frame_0006_1, R.drawable.frame_0005_1,
            R.drawable.frame_0004_1, R.drawable.frame_0003_1, R.drawable.frame_0002_1};

    private static final int ANIMATION_INTERVAL = 30; // 500ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.activity_start);

        // View Initialization

        Button getStarted = (Button) findViewById(R.id.button2);
        ImageView reTagLogo = (ImageView) findViewById(R.id.imageView);
        //ImageView title = (ImageView) findViewById(R.id.imageView2);

        Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideup_fadein);
        getStarted.setAnimation(animFadein);

        // Typeface Changes

        Typeface openLight = Typeface.createFromAsset(this.getAssets(), "fonts/openLight.ttf");
        getStarted.setTypeface(openLight);

        mFasterAnimationsContainer = FasterAnimationsContainer
                .getInstance(reTagLogo);

        mFasterAnimationsContainer.addAllFrames(IMAGE_RESOURCES,
                ANIMATION_INTERVAL);

        mFasterAnimationsContainer.start();

        // Triggering before status is set - fix

        if(!mFasterAnimationsContainer.mShouldRun) {

            mFasterAnimationsContainer.removeAllFrames();
            //reTagLogo.setImageResource(R.drawable.frame_0000_1);
            getStarted.startAnimation(animFadein);
        }

        getStarted.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, MediaSelection.class);
                startActivity(intent);
            }

        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }

            }
        }
    }
}