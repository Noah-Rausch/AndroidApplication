package com.example.noahr.photoapp.Services;

// Interface to connect photo posting and retrieving information from the server.

import com.example.noahr.photoapp.Domain.ImageWrapper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ImageEndpoints {

    // Post an image to the server.
    @POST("add")
    Call<ImageWrapper> postImage(@Body ImageWrapper fr);

    // Add a vote on an image.
    @POST("vote")
    Call<ImageWrapper> addVote(@Body ImageWrapper ir);

    // Get the images sent to a specific user so they can vote on them.
    @GET("{sender}/{instruction}")
    Call<ImageWrapper> getImages(@Path("sender") String sender, @Path("instruction") String instruction);


    // Get all the images that have been sent by users that you follow.
    @GET("{sender}")
    Call<ImageWrapper> getMyCircle(@Path("sender") String sender);
}
