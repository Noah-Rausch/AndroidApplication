package com.example.noahr.photoapp.Views;


import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.GridView;


// Fragment that displays all of the images that have been sent by the users they follow.
import com.example.noahr.photoapp.Services.ImageAdapter;
import com.example.noahr.photoapp.R;
import com.example.noahr.photoapp.Services.ImageNetworkLayer;

public class MyCircleFrag extends Fragment {

    private View myView;
    static public ArrayAdapter arrayAdapterMyCircle;
    static public ImageAdapter imageAdapter;
    static public GridView gridView;

    public MyCircleFrag() {
        // Empty constructor.
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //imageAdapter = new ImageAdapter(getActivity());

        myView = inflater.inflate(R.layout.fragment_profile, container, false);
        gridView = (GridView) myView.findViewById(R.id.myCircleGridview);
        gridView.setAdapter(imageAdapter);


        // Instantiate the network layer, and start the operation of getting a
        // list of all the images that have been sent to this user.
        // Pass the method the specific UI elements you want updated with info.


        ImageNetworkLayer imageNetworkLayer = new ImageNetworkLayer(getActivity(), arrayAdapterMyCircle, gridView, imageAdapter);
        imageNetworkLayer.getImages(CentralAcc.myUsername, "forCircle");

        return myView;
    }
}
