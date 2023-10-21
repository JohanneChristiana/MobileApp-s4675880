package com.example.mobileapp_s4675880

import retrofit2.http.GET
import retrofit2.http.Query
interface LoginApi {

    // When login function is called, we pass the queries values to the endpoint GET"login"
    @GET("login")
    suspend fun login(@Query("username") username:String, @Query("password") password:String): LoginResponse
    // Remember Values 'username' and 'password' should have the same value as IntelliJ IDEA
}