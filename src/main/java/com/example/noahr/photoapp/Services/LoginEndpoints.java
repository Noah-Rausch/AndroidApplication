package com.example.noahr.photoapp.Services;

import com.example.noahr.photoapp.Domain.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

// Interface to set specific endpoints and what methods connect to those endpoints.
public interface LoginEndpoints {


    // Create a new user.  Posts a user object to the server to create a new account
    // from its info.

    @POST("create")
    Call<User> createUser(@Body User user);



    // Log a user in.  Posts a user object to the server to verify the info.

    @POST("login")
    Call<User> loginUser(@Body User user);



    // Get a user.  The callback returns a response, which contains the user or a message
    // that the user does not exist.

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);
}