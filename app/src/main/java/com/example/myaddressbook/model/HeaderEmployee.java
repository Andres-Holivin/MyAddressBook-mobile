package com.example.myaddressbook.model;

import com.google.gson.annotations.SerializedName;

import java.util.Vector;

public class HeaderEmployee{
    @SerializedName("employees")
    public Vector<Employee> employees;
}
