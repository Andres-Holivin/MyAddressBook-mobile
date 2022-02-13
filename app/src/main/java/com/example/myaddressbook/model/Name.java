package com.example.myaddressbook.model;

import com.google.gson.annotations.SerializedName;

public class Name {
    @SerializedName("title")
    public String title;
    @SerializedName("first")
    public String first;
    @SerializedName("last")
    public String last;
}
