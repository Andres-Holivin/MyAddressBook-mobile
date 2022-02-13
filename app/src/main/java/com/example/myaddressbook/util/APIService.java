package com.example.myaddressbook.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    public static Retrofit employee=null;
    public static final String url="https://u73olh7vwg.execute-api.ap-northeast-2.amazonaws.com/";
    public static Retrofit getEmployee(){
        if(employee==null){
            employee = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return employee;
    }
}
