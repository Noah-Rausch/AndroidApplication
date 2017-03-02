package com.example.noahr.photoapp.Domain;

import com.example.noahr.photoapp.Domain.Image;

import java.util.ArrayList;
import java.util.Collections;

// Class to act as a container to individual image object.  Contains functionality
// to sort the images stored within.

public class ImageWrapper{

    ArrayList<Image> imageList;
    private String responseMessage;

    public ImageWrapper(){

    }

    public ImageWrapper(ArrayList<Image> l){

        this.imageList = l;
    }

    public void sortImages(){

        Collections.sort(imageList);
    }

    public ArrayList<Image> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<Image> imageList) {
        this.imageList = imageList;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
