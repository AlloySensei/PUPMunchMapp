package com.example.ui_pupmunchmapp;

public class Time {
    private int Id;
    private String Value;

    // Required no-argument constructor for Firebase
    public Time() {
    }

    public Time(String value) {
        this.Value = value;
    }

    @Override
    public String toString() {
        return Value;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        this.Value = value;
    }
}
