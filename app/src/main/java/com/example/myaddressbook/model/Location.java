package com.example.myaddressbook.model;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("city")
    public String city;
    @SerializedName("state")
    public String state;
    @SerializedName("coordinates")
    public Coordinates coordinates;
}