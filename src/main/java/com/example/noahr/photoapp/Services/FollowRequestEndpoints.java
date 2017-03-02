package com.example.noahr.photoapp.Services;

import com.example.noahr.photoapp.Domain.FollowRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

// Interface to set specific endpoints and what methods connect to those endpoints.
public interface FollowRequestEndpoints {


    // Add a user to follow.  Sends a "FollowRequest" object, and returns another
    // With the "responseMessage" field containing the result of the operation.

    @POST("add")
    Call<FollowRequest> addFollow(@Body FollowRequest fr);



    // Get all the users that a user follows.  Returns a list within the "FollowRequest"
    // object that is returned.

    @GET("{sender}/followees")
    Call<FollowRequest> getWhoIFollow(@Path("sender") String username);



    // Get all the users that follow a user.  Returns a list within the "FollowRequest"
    // object that is returned.

    @GET("{sender}/followers")
    Call<FollowRequest> getMyFollowers(@Path("sender") String username);



    // Get people you may know.  This is done by getting popular friends of friends.

    @GET("{sender}/potential")
    Call<FollowRequest> getFriendsOfFriends(@Path("sender") String username);



    // Remove a user who the current user follows.

    @DELETE("{senderAndUserToDelete}")
    Call<FollowRequest> deleteFollowee(@Path("senderAndUserToDelete") String senderAndUserToDelete);
}
