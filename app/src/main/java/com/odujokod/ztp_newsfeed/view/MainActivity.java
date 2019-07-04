package com.odujokod.ztp_newsfeed.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.odujokod.ztp_newsfeed.R;
import com.odujokod.ztp_newsfeed.adapter.PostAdapter;
import com.odujokod.ztp_newsfeed.model.Posts;
import com.odujokod.ztp_newsfeed.network.Endpoint;
import com.odujokod.ztp_newsfeed.network.NetworkSetup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_posts);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        postAdapter = new PostAdapter();
        recyclerView.setAdapter(postAdapter);

        fetchPosts();
    }

    private void fetchPosts() {
        Endpoint endpoints = NetworkSetup.setupNetwork.create(Endpoint.class);
        endpoints.getPosts().enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(@NonNull Call<List<Posts>> call, @NonNull Response<List<Posts>> response) {
                if(response.body() != null){
                    Log.d("RESPONSE TAG", "onResponse: " + response.body());
                    parseResponse(response.body());
                } else {
                    Log.d("TAG", "onResponse: Fetch Failed");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Posts>> call, @NonNull Throwable t) {
                Log.d("TAG", "onResponse: " + t.getMessage());
            }
        });
    }

    private void parseResponse(List<Posts> posts) {
        postAdapter.setPosts(posts);
    }

}
