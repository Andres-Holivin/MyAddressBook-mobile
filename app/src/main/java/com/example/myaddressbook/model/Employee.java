package com.example.myaddressbook.model;

import com.google.gson.annotations.SerializedName;
public class Employee {
    @SerializedName("employeeId")
    public Integer employeeId;
    @SerializedName("email")
    public String email;
    @SerializedName("phone")
    public String phone;
    @SerializedName("location")
    public Location location;
    @SerializedName("registered")
    public Registered registered;
    @SerializedName("picture")
    public Picture picture;
    @SerializedName("name")
    public Name name;
}
