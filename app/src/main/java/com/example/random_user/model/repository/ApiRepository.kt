package com.example.random_user.model.repository

import com.example.random_user.model.remote.UserApi
import com.example.random_user.model.remote.UsersInfoRetrofit

class ApiRepository(private val api: UserApi) {

    suspend fun getUsers(count: Int) = api.getUsers(count)
}