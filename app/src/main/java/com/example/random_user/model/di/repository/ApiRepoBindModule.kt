package com.example.random_user.model.di.repository

import com.example.random_user.model.repository.ApiRepository
import com.example.random_user.model.repository.DataFetcher
import com.example.random_user.model.repository.remote.Result
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ApiRepoBindModule {

    @Singleton
    @Binds
    fun bindApiRepo(apiRepository: ApiRepository): DataFetcher<List<Result>>
}