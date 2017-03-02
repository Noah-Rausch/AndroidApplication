package com.example.noahr.photoapp.Services;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.noahr.photoapp.Views.CentralAcc;
import com.example.noahr.photoapp.Domain.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Class that handles the details of connecting to the network and dealing with the HTTP calls.
// Uses a User object as a parameter, adding a layer on top of the UI that separates it from the
// server.
public class LoginNetworkLayer {
    User user;
    Retrofit retrofit;
    LoginEndpoints endpoints;
    Context context;

    public LoginNetworkLayer(User user, Context con) {
        this.user = user;
        this.context = con;
        final String BASE_URL = "http://10.0.2.2:8080/RestServlet/rest/users/";
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        endpoints = retrofit.create(LoginEndpoints.class);
    }


    // Method that initiates a connection with the server to create a new user.
    public void createUser() {
        Call<User> call = endpoints.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // If we get back the object we expect, let the user know.  If the body if empty,
                // deal with it accordingly.
                if(response.body() != null){
                    Toast.makeText(context, response.body().getResponseMessage(), Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(context, "Error connecting...", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "Error connecting", Toast.LENGTH_LONG).show();
            }
        });
    }


    // Method that connects to the server to log a user in.
    public void loginUser(){
        Call<User> call = endpoints.loginUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // If we get back the object we expect, let the user know.  If the body if empty,
                // deal with it accordingly.
                // If the login is successful, switch to the main app activity.
                if(response.body() != null){
                    String result = response.body().getResponseMessage();
                    if(result.equalsIgnoreCase("Login Successful")){
                        Intent intent = new Intent(context, CentralAcc.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("myUsername", response.body().getUsername());
                        context.startActivity(intent);
                    }
                    else{
                        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(context, "Error connecting", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "Error connecting", Toast.LENGTH_LONG).show();
            }
        });
    }
}



