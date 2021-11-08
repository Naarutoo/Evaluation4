package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("/api/")
    fun getUserDetails():Call<DTO>

}