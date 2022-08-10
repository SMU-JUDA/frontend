package com.example.juda

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterService {
    @FormUrlEncoded
    @POST("register/")
    fun register(@Field("username") username:String?,
                 @Field("email") email:String?,
                 @Field("password") password:String?) : Call<LoginResponse>
}