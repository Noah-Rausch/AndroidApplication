package com.example.noahr.photoapp.Domain;

import java.util.ArrayList;

// Model class that represents the data needed to follow another user.
public class FollowRequest {
    private User sender;
    private User toBeFollowed;
    private ArrayList<User> usersIFollow;
    private ArrayList<User> usersWhoFollowMe;
    private ArrayList<String> usersIMayKnow;
    private String responseMessage;

    public FollowRequest(){

    }

    public FollowRequest(User send, User toFollow){
        this.sender = send;
        this.toBeFollowed = toFollow;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getToBeFollowed() {
        return toBeFollowed;
    }

    public void setToBeFollowed(User toBeFollowed) {
        this.toBeFollowed = toBeFollowed;
    }

    public ArrayList<User> getUsersIFollow() {
        return usersIFollow;
    }

    public void setUsersIFollow(ArrayList<User> usersIFollow) {
        this.usersIFollow = usersIFollow;
    }

    public ArrayList<User> getUsersWhoFollowMe() {
        return usersWhoFollowMe;
    }

    public void setUsersWhoFollowMe(ArrayList<User> usersWhoFollowMe) {
        this.usersWhoFollowMe = usersWhoFollowMe;
    }


    public ArrayList<String> getUsersIMayKnow() {
        return usersIMayKnow;
    }

    public void setUsersIMayKnow(ArrayList<String> usersIMayKnow) {
        this.usersIMayKnow = usersIMayKnow;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
