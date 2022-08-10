package com.example.juda

import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface PostService {
    @FormUrlEncoded
    @POST("post/")
    fun create_post(
        @Field("title") title:String,
        @Field("image") image: File?,
        @Field("content") content:String
    ) : Call<PostResponse>

    @GET("post/")
    fun get_post(

//        @Field("id") id:String,
//        @Field("title") title:String,
//        @Field("writer_nickname") writer_nickname:String,
//        @Field("image") image:String,
//        @Field("create_at") create_at:String
    ) : Call<List<PostResponse>>

    @GET("post/{id}/")
    fun read_post(
        @Path("id") id: Int
    ) : Call<PostResponse.Data>

    @PUT("post/{id}/")
    fun update_post(
        @Path("id") id: Int
    ) : Call<PostResponse>

    @DELETE("post/{id}/")
    fun delete_post(
        @Path("id") id: String
    ) : Call<Void>

}