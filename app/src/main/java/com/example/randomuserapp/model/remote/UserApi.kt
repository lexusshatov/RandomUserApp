package com.example.randomuserapp.model.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api")
    suspend fun getUsers(@Query("results") count: Int): Call<UsersInfoRetrofit>
}