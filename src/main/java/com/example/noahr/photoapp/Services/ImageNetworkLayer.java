package com.example.noahr.photoapp.Services;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.noahr.photoapp.Domain.Image;
import com.example.noahr.photoapp.Domain.ImageWrapper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


// Class that handles the details of connecting to the server in regards to sending and getting images.

public class ImageNetworkLayer {

    ImageWrapper imageWrapper;
    Retrofit retrofit;
    ImageEndpoints endpoints;
    ArrayAdapter vArrayAdapter;
    GridView gridView;
    ImageAdapter imageAdapter;

    Context context;

    public ImageNetworkLayer(ImageWrapper im, Context con){

        this.imageWrapper = im;
        this.context = con;
        final String BASE_URL = "http://10.0.2.2:8080/RestServlet/rest/images/";
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        endpoints = retrofit.create(ImageEndpoints.class);
    }

    public ImageNetworkLayer(Context contxt, ArrayAdapter arrayAdapterP, GridView gV, ImageAdapter iA){

        this.context = contxt;
        final String BASE_URL = "http://10.0.2.2:8080/RestServlet/rest/images/";
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        endpoints = retrofit.create(ImageEndpoints.class);
        this.vArrayAdapter = arrayAdapterP;
        this.gridView = gV;
        this.imageAdapter = iA;
    }



    // Calls the method in the endpoints, connects to the server and sends the "ImageModel".
    // The model object contains the image to be posted.

    public void postImage(){

        Call<ImageWrapper> call = endpoints.postImage(imageWrapper);
        call.enqueue(new Callback<ImageWrapper>() {
            @Override
            public void onResponse(Call<ImageWrapper> call, Response<ImageWrapper> response) {

                if(response.body() != null){

                    // The image was successfully sent.
                    Toast.makeText(context, response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                }
                else{

                    // body is empty...
                    Toast.makeText(context, "Reponse body is empty...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImageWrapper> call, Throwable t) {

                Toast.makeText(context, "Error getting response", Toast.LENGTH_SHORT).show();
            }
        });
    }



    // Add a vote to an image.

    public void addVote(){

        Call<ImageWrapper> call = endpoints.addVote(imageWrapper);
        call.enqueue(new Callback<ImageWrapper>() {
            @Override
            public void onResponse(Call<ImageWrapper> call, Response<ImageWrapper> response) {

                if(response.body() != null){

                    // The vote was successfully sent.
                    Toast.makeText(context, response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImageWrapper> call, Throwable t) {

            }
        });
    }



    // Call method in endpoints that returns all the images sent to a user.  Based on the instruction,
    // This could call on the server either an operation to get get images to vote on, or get images
    // to show in our Circle

    public void getImages(String username, String instruction){

        Call<ImageWrapper> call = endpoints.getImages(username, instruction);
        call.enqueue(new Callback <ImageWrapper>() {

            @Override
            public void onResponse(Call<ImageWrapper> call, Response<ImageWrapper> response) {

                if(response.body() != null){

                    ArrayList<String> senders = new ArrayList<>();
                    ArrayList<String> urls = new ArrayList<>();
                    ArrayList<Integer> ids = new ArrayList<>();

                    ArrayList<Image> pics = response.body().getImageList();

                    // Extract the lists of senders, urls, and ids from the list of pics.

                    for(Image i : pics){

                        senders.add(i.getSender());
                        urls.add(i.getUrl());
                        ids.add(i.getId());
                    }

                    imageAdapter = new ImageAdapter(context, ids, urls, senders);
                    gridView.setAdapter(imageAdapter);
                }
                else{
                    // Body is empty.
                }
            }

            @Override
            public void onFailure(Call<ImageWrapper> call, Throwable t) {

            }
        });
    }
}
