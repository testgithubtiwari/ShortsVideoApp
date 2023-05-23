package com.example.youtubeshorts;

import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MediaPlayerActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        getSupportActionBar().setTitle("Video is playing");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        // Hide the status bar and make the activity full-screen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Retrieve the media URL from the intent
        String mediaUrl = getIntent().getStringExtra("MEDIA_URL");

        videoView = findViewById(R.id.videoView);


        // Set the media controller for the video view
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        // Set the video URI and start playing
        videoView.setVideoURI(Uri.parse(mediaUrl));
        videoView.start();

        // Set click listener for the close button

    }
}