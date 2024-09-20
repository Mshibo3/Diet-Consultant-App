package com.example.dietconsultantapp;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Videos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        VideoView video1 = findViewById(R.id.video1);

        video1.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.pushups);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(video1);
        video1.setMediaController(mediaController);


    }
}