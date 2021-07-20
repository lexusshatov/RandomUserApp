package com.example.random_user.model.di.repository.local

import com.example.random_user.model.local.UserDao
import com.example.random_user.model.repository.LocalRepository
import dagger.Module
import dagger.Provides

@Module
class LocalRepositoryModule {

    @Provides
    fun provideLocalRepository(userDao: UserDao) = LocalRepository(userDao)
}