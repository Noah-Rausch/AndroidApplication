package com.example.noahr.photoapp.Services;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noahr.photoapp.Domain.Image;
import com.example.noahr.photoapp.Domain.ImageWrapper;
import com.example.noahr.photoapp.R;
import com.example.noahr.photoapp.Services.ImageNetworkLayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private ArrayList<Integer> ids;
    private ArrayList<String> urls;
    private ArrayList<String> senders;
    private Context mContext;
    private static LayoutInflater inflater = null;


    // Constructor
    public ImageAdapter(Context c, ArrayList<Integer> ids, ArrayList<String> urlList, ArrayList<String> sendersL) {
        this.mContext = c;
        this.ids = ids;
        this.urls = urlList;
        this.senders = sendersL;
        inflater = ( LayoutInflater)mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return urls.size();
    }

    public Bitmap getItem(int position) {

        return null;
    }

    public long getItemId(int position) {

        return position;
    }


    public class Holder
    {
        TextView sender;
        ImageView imgV;
        Button button;
    }


    // create a new ImageView for each item referenced by the Adapter

    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder = new Holder();
        View rowV;

        rowV = inflater.inflate(R.layout.ui_layout, null);
        holder.sender = (TextView) rowV.findViewById(R.id.captionXML);
        holder.imgV = (ImageView) rowV.findViewById(R.id.imgXML);
        Picasso.with(mContext)
                .load(urls.get(position) + ".png")
                .into(holder.imgV);

        holder.sender.setText(senders.get(position));
        holder.button = (Button) rowV.findViewById(R.id.voteButton);
        holder.button.setOnClickListener(new View.OnClickListener() {

            // When the user clicks this button, the associated picture will
            // gain a vote.  This is done by sending the url of the image
            // to be voted on to the server.

            @Override
            public void onClick(View v) {

                Image im = new Image();
                im.setUrl(urls.get(position));
                ArrayList<Image> ims = new ArrayList<>();
                ims.add(im);
                ImageNetworkLayer imageNetworkLayer = new ImageNetworkLayer(new ImageWrapper(ims), mContext);
                imageNetworkLayer.addVote();
                Toast.makeText(mContext, urls.get(position), Toast.LENGTH_LONG).show();
            }
        });
        return rowV;
    }
}
