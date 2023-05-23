package com.example.youtubeshorts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView thumbnailRecycler;
    private ProgressDialog pd;
    private ArrayList<thumbnailData> list;
    private ThumbnailAdapter adapter;
    private DatabaseReference reference;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Videos");
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));
        thumbnailRecycler=findViewById(R.id.Recycler);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                getThumbnail();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        pd=new ProgressDialog(this);
        reference= FirebaseDatabase.getInstance().getReference();
        thumbnailRecycler.setLayoutManager(new LinearLayoutManager(this));
        thumbnailRecycler.setHasFixedSize(true);
        getThumbnail();
    }

    private void getThumbnail() {
        DatabaseReference submissionsRef = reference.child("Submission");
        pd.setMessage("Loading...");
        pd.show();

        submissionsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();

                for (DataSnapshot submissionSnapshot : dataSnapshot.getChildren()) {
                    DataSnapshot imageSnapshot = submissionSnapshot.child("image");
                    String image = imageSnapshot.getValue(String.class);

                    DataSnapshot mediaSnapshot = submissionSnapshot.child("mediaUrl");
                    String mediaUrl = mediaSnapshot.getValue(String.class);

                    thumbnailData data = new thumbnailData(image, mediaUrl);
                    list.add(data);
                }

                adapter = new ThumbnailAdapter(MainActivity.this, list);
                thumbnailRecycler.setAdapter(adapter);
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                pd.dismiss();
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}