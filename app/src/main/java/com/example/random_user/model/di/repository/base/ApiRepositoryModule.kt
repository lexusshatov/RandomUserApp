package com.example.random_user.model.di.repository.base

import com.example.random_user.model.remote.Result
import com.example.random_user.model.remote.UserApi
import com.example.random_user.model.repository.ApiRepository
import com.example.random_user.model.repository.DataFetcher
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class ApiRepositoryModule {
    @Inject
    lateinit var userApi: UserApi

    @Provides
    fun provideApiRepository(): DataFetcher<List<Result>> {
        return ApiRepository(userApi)
    }
}