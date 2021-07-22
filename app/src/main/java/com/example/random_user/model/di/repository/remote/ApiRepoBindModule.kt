package com.example.random_user.model.di.repository.remote

import com.example.random_user.model.di.repository.RepositoryScope
import com.example.random_user.model.repository.remote.Result
import com.example.random_user.model.repository.ApiRepository
import com.example.random_user.model.repository.DataFetcher
import dagger.Binds
import dagger.Module

@Module
interface ApiRepoBindModule {

    @RepositoryScope
    @Binds
    fun bindApiRepo(apiRepository: ApiRepository): DataFetcher<List<Result>>
}