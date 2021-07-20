package com.example.random_user.model.repository

import com.example.random_user.model.remote.Result
import com.example.random_user.model.remote.UserApi
import javax.inject.Inject

class ApiRepository @Inject constructor(private val api: UserApi)
    : DataFetcher<@kotlin.jvm.JvmSuppressWildcards List<@kotlin.jvm.JvmSuppressWildcards Result>> {

    override suspend fun fetchData(count: Int) = api.getUsers(count).results
}