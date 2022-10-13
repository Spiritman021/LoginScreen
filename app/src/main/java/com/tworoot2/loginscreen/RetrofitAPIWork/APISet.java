package com.tworoot2.loginscreen.RetrofitAPIWork;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APISet {

    @FormUrlEncoded
    @POST("login/pg")
    Call<ResponseModel> verifyUser(
            @Field("email") String email,
            @Field("password") String password
    );

}
