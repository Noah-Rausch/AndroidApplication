package com.example.noahr.photoapp.Views;


import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.noahr.photoapp.Services.ImageAdapter;
import com.example.noahr.photoapp.R;
import com.example.noahr.photoapp.Services.ImageNetworkLayer;

// Fragment that displays all the images ever taken in the database.
public class WorldFrag extends Fragment {

    private View myView;
    static public ArrayAdapter arrayAdapterWorld;
    static public ImageAdapter imageAdapter;
    static public GridView gridView;

    public WorldFrag() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.fragment_feed, container, false);

        gridView = (GridView) myView.findViewById(R.id.worldGridViewXml);
        gridView.setAdapter(arrayAdapterWorld);

        // Instantiate the network layer, and start the operation of getting a
        // list of all the images that have been sent to this user.
        // Pass the method the specific UI elements you want updated with info.

        ImageNetworkLayer imageNetworkLayer = new ImageNetworkLayer(getActivity(), arrayAdapterWorld, gridView, imageAdapter);
        imageNetworkLayer.getImages(CentralAcc.myUsername, "forWorld");

        return myView;
    }
}

