package com.example.random_user.model.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api")
    suspend fun getUsers(@Query("results") count: Int): UsersInfoRetrofit
}