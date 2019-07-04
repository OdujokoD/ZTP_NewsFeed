package com.odujokod.ztp_newsfeed.network;

import com.odujokod.ztp_newsfeed.model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Endpoint {
    @GET("posts/newsfeed")
    Call<List<Posts>> getPosts();
}
