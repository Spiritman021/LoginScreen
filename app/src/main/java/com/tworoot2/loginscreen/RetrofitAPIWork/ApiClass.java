package com.tworoot2.loginscreen.RetrofitAPIWork;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClass {
    private static final String url = "https://pg-app-backend.herokuapp.com/api/";

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static APISet client = retrofit.create(APISet.class);
}
