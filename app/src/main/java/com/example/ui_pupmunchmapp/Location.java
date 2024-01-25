package com.example.ui_pupmunchmapp;

public class Location {
    private int Id;
    private String loc;

    // Default (no-argument) constructor
    public Location() {
        // Default constructor is required for Firebase to deserialize objects
    }

    public Location(String loc) {
        this.loc = loc;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    // Override toString to return a meaningful representation of the location
    @Override
    public String toString() {
        return loc;
    }
}
