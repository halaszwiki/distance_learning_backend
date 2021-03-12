package com.example.dl.model;

public class ChatMessage {
    
    private String username;
    private String message;
    
    public ChatMessage() {
    }

    public ChatMessage(String username, String message) {
        super();
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
