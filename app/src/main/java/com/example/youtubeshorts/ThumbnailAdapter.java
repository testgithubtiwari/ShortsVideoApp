package com.example.youtubeshorts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.ThumbnailViewHolder> {
    private Context context;
    public static final int REQUEST_CODE_MEDIA_PLAYER = 1;
    private ArrayList<thumbnailData> thumbnailList;
    private int clickedPosition = RecyclerView.NO_POSITION;

    public ThumbnailAdapter(Context context, ArrayList<thumbnailData> thumbnailList) {
        this.context = context;
        this.thumbnailList = thumbnailList;
    }

    @NonNull
    @Override
    public ThumbnailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thumbnail_item, parent, false);
        return new ThumbnailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailViewHolder holder, int position) {
        thumbnailData thumbnailData = thumbnailList.get(position);

        // Load thumbnail image using Picasso
        Picasso.get()
                .load(thumbnailData.getImage())
                .into(holder.thumbnailImageView);

        // Highlight the clicked position
        if (position == clickedPosition) {
            // Apply your desired highlighting mechanism here (e.g., change background color)
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.highlight_color));
        } else {
            // Reset the highlighting for other positions
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.transparent1));
        }

        // Set click listener for thumbnail image
        holder.thumbnailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetch the corresponding media URL for the clicked thumbnail
                String mediaUrl = thumbnailData.getMediaUrl();

                // Save the clicked position/index
                clickedPosition = position;

                // Start the activity to play the media content
                Intent intent = new Intent(context, MediaPlayerActivity.class);
                intent.putExtra("MEDIA_URL", mediaUrl);
                ((Activity) context).startActivityForResult(intent, ThumbnailAdapter.REQUEST_CODE_MEDIA_PLAYER);
            }
        });
    }

    @Override
    public int getItemCount() {
        return thumbnailList.size();
    }

    public void setClickedPosition(int position) {
        clickedPosition = position;
    }

    public static class ThumbnailViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImageView;

        public ThumbnailViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImage);
        }
    }
}
