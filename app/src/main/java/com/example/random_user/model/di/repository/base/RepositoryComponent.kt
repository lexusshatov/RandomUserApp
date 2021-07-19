package com.example.random_user.model.di.repository.base

import com.example.random_user.model.local.User
import com.example.random_user.model.remote.Result
import com.example.random_user.model.repository.DataCache
import com.example.random_user.model.repository.DataFetcher
import dagger.Subcomponent

@Subcomponent(
    modules = [
        ApiRepositoryModule::class,
        LocalRepositoryModule::class,
        LocalRepositoryBindModule::class
    ]
)
interface RepositoryComponent {

    fun apiRepository(): DataFetcher<List<Result>>
    fun localRepository(): DataCache<User, String>
}