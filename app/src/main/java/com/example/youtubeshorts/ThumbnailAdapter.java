package com.example.youtubeshorts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.ThumbnailViewHolder> {
    private Context context;
    private ArrayList<thumbnailData> thumbnailList;

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

        // Set click listener for thumbnail image
        holder.thumbnailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetch the corresponding media URL for the clicked thumbnail
                String mediaUrl = thumbnailData.getMediaUrl();

                // Start the activity to play the media content
                Intent intent = new Intent(context, MediaPlayerActivity.class);
                intent.putExtra("MEDIA_URL", mediaUrl);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return thumbnailList.size();
    }

    public static class ThumbnailViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImageView;

        public ThumbnailViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImage);
        }
    }
}
