package com.example.random_user.model.di.repository.remote

import com.bumptech.glide.load.data.DataFetcher
import com.example.random_user.model.remote.Result
import com.example.random_user.model.repository.ApiRepository
import dagger.Binds
import dagger.Module

@Module
interface ApiRepoBindModule {

    @Binds
    fun bindApiRepo(apiRepository: ApiRepository): DataFetcher<List<Result>>
}