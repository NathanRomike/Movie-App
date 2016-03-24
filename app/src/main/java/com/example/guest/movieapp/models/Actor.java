package com.example.guest.movieapp.models;


public class Actor {
    private String mProfilePath;
    private String mName;
    private String mId;


    public Actor(String profilePath, String name, String id) {
        this.mProfilePath = profilePath;
        this.mName = name;
        this.mId = id;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public String getName() {
        return mName;
    }

    public String getId() {
        return mId;
    }
}


