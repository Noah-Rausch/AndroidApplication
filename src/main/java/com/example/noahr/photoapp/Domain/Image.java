package com.example.noahr.photoapp.Domain;

//Class to represent a single image.

import java.util.ArrayList;

public class Image implements Comparable<Image> {

    private int id;
    private byte[] imageBytes;
    private String sender;
    private String url;
    private ArrayList<String> recipients;
    private String responseMessage;
    private int votes;

    public Image(){

    }

    public Image(int i, String se, String u, ArrayList<String> re, int v){

        this.id = i;
        this.sender = se;
        this.url = u;
        this.recipients = re;
        this.votes = v;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(ArrayList<String> recipients) {
        this.recipients = recipients;
    }

    public byte[] getImageBytes() {

        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {

        this.imageBytes = imageBytes;
    }

    public String getSender() {

        return sender;
    }

    public void setSender(String sender) {

        this.sender = sender;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }


    public String getResponseMessage() {

        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {

        this.responseMessage = responseMessage;
    }

    public int getVotes() {

        return votes;
    }

    public void setVotes(int votes) {

        this.votes = votes;
    }

    // Compare two images based on votes.

    @Override
    public int compareTo(Image o) {

        return votes - o.votes;
    }
}

