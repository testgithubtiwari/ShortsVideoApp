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
import androidx.recyclerview.widget.RecyclerView;

public class MediaPlayerActivity extends AppCompatActivity {

    private VideoView videoView;
    private int clickedPosition = RecyclerView.NO_POSITION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        getSupportActionBar().setTitle("Video is playing");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        // Hide the status bar and make the activity full-screen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Retrieve the media URL and clicked position from the intent
        String mediaUrl = getIntent().getStringExtra("MEDIA_URL");
        clickedPosition = getIntent().getIntExtra("clicked_position", RecyclerView.NO_POSITION);

        videoView = findViewById(R.id.videoView);

        // Set the media controller for the video view
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        // Set the video URI and start playing
        videoView.setVideoURI(Uri.parse(mediaUrl));
        videoView.start();
    }

    @Override
    public void onBackPressed() {
        // Pass the clicked position back to the previous activity or fragment
        getIntent().putExtra("clicked_position", clickedPosition);
        setResult(RESULT_OK, getIntent());
        super.onBackPressed();
    }
}
