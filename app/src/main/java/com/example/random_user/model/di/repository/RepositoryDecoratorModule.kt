package com.example.random_user.model.di.repository

import com.example.random_user.model.local.User
import com.example.random_user.model.remote.Result
import com.example.random_user.model.repository.DataCache
import com.example.random_user.model.repository.DataFetcher
import com.example.random_user.model.repository.DataRepository
import com.example.random_user.model.repository.RepositoryDecorator
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class RepositoryDecoratorModule {
    @Inject
    lateinit var api: DataFetcher<List<Result>>
    @Inject
    lateinit var database: DataCache<User, String>

    @Provides
    fun provideRepositoryDecorator(): DataRepository {
        return RepositoryDecorator(api, database)
    }
}