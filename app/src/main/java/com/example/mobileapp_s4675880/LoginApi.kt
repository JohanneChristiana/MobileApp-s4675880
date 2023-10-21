package com.example.mobileapp_s4675880

interface LoginApi {

    @GET("login")
    suspend fun login(): LoginResponse
}