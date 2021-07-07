package com.example.randomuserapp.model.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api")
    fun getUsers(@Query("results") count: Int): Call<UsersInfoRetrofit>
}