package com.example.juda;

import com.google.gson.annotations.SerializedName;

//public class RegisterData {
//    @SerializedName("username")
//    private String username;
//
//    @SerializedName("email")
//    private String email;
//
//    @SerializedName("password")
//    private String password;
//
//    public RegisterData(String username, String email, String password) {
//        this.username = username;
//        this.email = email;
//        this.password = password;
//    }
//}
data class RegisterData(
    var username: String,
    var email: String,
    var password: String
)