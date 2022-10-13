package com.tworoot2.loginscreen.RetrofitAPIWork;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {

    private static final String url = "https://pg-app-backend.herokuapp.com/api/";
    private static Controller clientObject;
    private static Retrofit retrofit;

    public Controller() {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized Controller getInstance() {
        if (clientObject == null) {
            clientObject = new Controller();
        }
        return clientObject;
    }

    public APISet getApi() {
        return retrofit.create(APISet.class);
    }

}
