package com.example.noahr.photoapp.Services;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.noahr.photoapp.Views.FollowListFrag;
import com.example.noahr.photoapp.Domain.FollowRequest;
import com.example.noahr.photoapp.Domain.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Class to handle the details of connecting to the server, in regards to adding a user to follow,
// or to get a list of the all the users someone follows, or the users who follow them.
public class FollowNetworkLayer {

    FollowRequest followRequest;
    Retrofit retrofit;
    FollowRequestEndpoints endpoints;
    Context context;
    AutoCompleteTextView tv;
    ArrayAdapter aA;
    ListView lv;

    public FollowNetworkLayer(Context con){
        this.context = con;
        final String BASE_URL = "http://10.0.2.2:8080/RestServlet/rest/followers/";
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        endpoints = retrofit.create(FollowRequestEndpoints.class);
    }

    public FollowNetworkLayer(FollowRequest fr, Context contxt, AutoCompleteTextView pTv, ArrayAdapter pAa, ListView pLv){
        this.followRequest= fr;
        this.context = contxt;
        final String BASE_URL = "http://10.0.2.2:8080/RestServlet/rest/followers/";
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        endpoints = retrofit.create(FollowRequestEndpoints.class);
        this.tv = pTv;
        this.aA = pAa;
        this.lv = pLv;
    }


    // Call the method from the Retrofit interface that lets the user follow another user.
    public void addFollow(){
        Call<FollowRequest> call = endpoints.addFollow(followRequest);
        call.enqueue(new Callback<FollowRequest>() {
            @Override
            public void onResponse(Call<FollowRequest> call, Response<FollowRequest> response) {
                if(response.body() != null){
                    // This operation doesn't send anything back useful except for a response
                    // message letting the user know the result of the follow request.
                    String responseStr = response.body().getResponseMessage();
                    Toast.makeText(context, responseStr, Toast.LENGTH_SHORT).show();
                    // Update our cached list of users we follow.
                    if(responseStr.equalsIgnoreCase("Follow successful")) {
                        FollowListFrag.usersIFollow.add(followRequest.getToBeFollowed().getUsername());
                        FollowListFrag.arrayAdapter.notifyDataSetChanged();
                    }
                }
                else{
                    Toast.makeText(context, "Error connecting...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FollowRequest> call, Throwable t) {

            }
        });
    }


    // Call the method from the Retrofit interface that gets a list of users who follow the current
    // user.
    public void getAllWhoFollowMe(String username){
        Call<FollowRequest> call = endpoints.getMyFollowers(username);
        call.enqueue(new Callback<FollowRequest>() {
            @Override
            public void onResponse(Call<FollowRequest> call, Response<FollowRequest> response) {
                if(response.body() != null){
                    ArrayList<User> followers = response.body().getUsersWhoFollowMe();
                    ArrayList<String> followersAsString = new ArrayList<String>();

                    // Make a list of the user's by their username.
                    for(User user : followers){
                        followersAsString.add(user.getUsername());
                    }

                    // Set the listview in the "FollowListFrag" to contain a list of the users that the
                    // current user follows, as retrieved from the server.
                    FollowListFrag.arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1,  followersAsString);
                    FollowListFrag.listView.setAdapter(FollowListFrag.arrayAdapter);
                }
                else{
                    Toast.makeText(context, "Error connecting...", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<FollowRequest> call, Throwable t) {
            }
        });
    }


    // Call the method from the Retrofit interface that gets a list of users who the current
    // user follows.
    public void getAllWhoIFollow(String username){
        Call<FollowRequest> call = endpoints.getWhoIFollow(username);
        call.enqueue(new Callback<FollowRequest>() {
            @Override
            public void onResponse(Call<FollowRequest> call, Response<FollowRequest> response) {
                if(response.body() != null){
                    ArrayList<User> iFollow = response.body().getUsersIFollow();
                    ArrayList<String> iFollowAsString = new ArrayList<String>();

                    // Make a list of the user's by their username.  Just transforms a list
                    // of Users into a list of strings.
                    for(User user : iFollow){
                        iFollowAsString.add(user.getUsername());
                    }

                    // Set the list of users within the fragment, so we can keep track of who should
                    // appear in the follows list without the need to keep querying.
                    FollowListFrag.usersIFollow = iFollowAsString;

                    // Set the listview in the "friendslistFrag" to contain a list of the user's friends
                    // that was retrieved from the server.
                    FollowListFrag.arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1,  iFollowAsString);
                    FollowListFrag.listView.setAdapter(FollowListFrag.arrayAdapter);
                }
                else{
                    Toast.makeText(context, "Error connecting...", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<FollowRequest> call, Throwable t) {
            }
        });
    }



    // Get a list of users you may know.
    public void getFriendsOfFriends(String username){
        Toast.makeText(context, "GETTING FRIENDS OF FRIENDS", Toast.LENGTH_LONG).show();
        Call<FollowRequest> call = endpoints.getFriendsOfFriends(username);
        call.enqueue(new Callback<FollowRequest>() {
            @Override
            public void onResponse(Call<FollowRequest> call, Response<FollowRequest> response) {
                if(response.body() != null){
                    aA = new ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, response.body().getUsersIMayKnow());
                    tv.setAdapter(aA);
                }
            }

            @Override
            public void onFailure(Call<FollowRequest> call, Throwable t) {

            }
        });
    }



    // Call the method from the Retrofit interface that deletes a single user that the current
    // user is following.
    public void deleteUser(String senderAndUserToDelete){
        Call<FollowRequest> call = endpoints.deleteFollowee(senderAndUserToDelete);
        call.enqueue(new Callback<FollowRequest>() {
            @Override
            public void onResponse(Call<FollowRequest> call, Response<FollowRequest> response) {
                if(response.body() != null){
                    Toast.makeText(context, response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Error connecting...", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<FollowRequest> call, Throwable t) {

            }
        });
    }
}