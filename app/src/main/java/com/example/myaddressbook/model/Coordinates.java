package com.example.myaddressbook.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Coordinates{
    @SerializedName("latitude")
    public String latitude;
    @SerializedName("longitude")
    public String longitude;
}