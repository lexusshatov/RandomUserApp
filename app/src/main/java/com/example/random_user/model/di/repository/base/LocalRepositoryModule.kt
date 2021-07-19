package com.example.random_user.model.di.repository.base

import com.example.random_user.model.local.User
import com.example.random_user.model.local.UserDatabase
import com.example.random_user.model.repository.DataCache
import com.example.random_user.model.repository.LocalRepository
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class LocalRepositoryModule {
    @Inject
    lateinit var localDatabase: UserDatabase

    @Provides
    fun provideLocalRepository(): DataCache<User, String> {
        return LocalRepository(localDatabase)
    }
}