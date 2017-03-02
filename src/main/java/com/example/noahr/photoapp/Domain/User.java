package com.example.noahr.photoapp.Domain;

// Class to represent a user. Acts as the model object, and will be transported to
// and from the server.
public class User {
    private String username;
    private String email;
    private String password;
    private String responseMessage;

    public User(){

    }

    public User(String un, String e, String p){
        this.username = un;
        this.email = e;
        this.password = p;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}



