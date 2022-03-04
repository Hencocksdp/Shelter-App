package com.example.phoneauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

public class Splash extends AppCompatActivity {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        videoView= (VideoView) findViewById(R.id.videoView);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash_screennew);

        videoView.setVideoURI(video); videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent(Splash.this, Registrer.class);
                startActivity(intent);
                Splash.this.finish();

            }
        });
        videoView.start();

    }





}

