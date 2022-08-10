package com.example.juda;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("register/")
    Call<RegisterResponse> userResgister(@Body RegisterData data);

}
