package com.example.noahr.photoapp.Views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.noahr.photoapp.Services.ImageAdapter;
import com.example.noahr.photoapp.R;
import com.example.noahr.photoapp.Services.ImageNetworkLayer;

// Fragment where the a listview is created with the pictures send to them, waiting to be
// voted on.
public class VoteFrag extends Fragment {
    private View myView;
    static public ArrayAdapter arrayAdapterVote;
    static public ImageAdapter imageAdapter;
    static public GridView gridView;


    public VoteFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myView = inflater.inflate(R.layout.fragment_vote, container, false);
        gridView = (GridView) myView.findViewById(R.id.voteGridViewXml);
        gridView.setAdapter(arrayAdapterVote);

        // Instantiate the network layer, and start the operation of getting a
        // list of all the images that have been sent to this user.

        ImageNetworkLayer imageNetworkLayer = new ImageNetworkLayer(getActivity(), arrayAdapterVote, gridView, imageAdapter);
        imageNetworkLayer.getImages(CentralAcc.myUsername, "forVote");

        return myView;
    }
}
