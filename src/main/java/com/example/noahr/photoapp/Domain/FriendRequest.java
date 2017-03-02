package com.example.noahr.photoapp.Domain;

// A model class that represents a friend request.  It contains the users involved in setting
// up the friend relationship.  Has data fields that that allows a list of Users to be
// transported, in addition to response message to describe the result of operations.

import java.util.ArrayList;

public class FriendRequest {
    private User sender;
    private User receiver;
    private ArrayList<User> friendRequests = new ArrayList<>();
    private String responseMessage;

    public FriendRequest(){

    }


    public FriendRequest(User sender, User reciever){
        this.sender = sender;
        this.receiver = reciever;
    }


    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public ArrayList<User> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(ArrayList<User> friendRequests) {
        this.friendRequests = friendRequests;
    }


    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}