package com.example.random_user.model.di.repository.remote

import com.example.random_user.model.remote.Result
import com.example.random_user.model.repository.ApiRepository
import com.example.random_user.model.repository.DataFetcher
import dagger.Binds
import dagger.Module

@Module
interface ApiRepoBindModule {

    @Binds
    fun bindApiRepo(apiRepository: ApiRepository): DataFetcher<List<Result>>
}