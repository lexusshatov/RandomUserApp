package com.example.random_user.model.repository

import com.example.random_user.model.repository.remote.Result
import com.example.random_user.model.repository.remote.UserApi

class ApiRepository(private val api: UserApi) : DataFetcher<List<Result>> {

    override suspend fun fetchData(count: Int) = api.getUsers(count).results
}