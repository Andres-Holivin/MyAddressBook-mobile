package com.example.myaddressbook.util;

import com.example.myaddressbook.model.HeaderEmployee;
import com.google.gson.JsonObject;

import java.util.Vector;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IAPIService {
    @GET("stage2/people/?nim=2301859505&nama=andres holivin")
    Call<HeaderEmployee> getEmployee();
    @GET("stage2/people/{id}/?nim=2301859505&nama=andres holivin")
    Call<HeaderEmployee> getEmployeeById(@Path("id") Integer id);
}
