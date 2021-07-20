package com.example.random_user.model.di.repository.remote

import com.example.random_user.model.remote.UserApi
import com.example.random_user.model.repository.ApiRepository
import dagger.Module
import dagger.Provides

@Module
class ApiRepositoryModule {

    @Provides
    fun provideApiRepository(apiRepository: UserApi) = ApiRepository(apiRepository)
}