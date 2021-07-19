package com.example.random_user.model.di.repository.base

import com.example.random_user.model.local.User
import com.example.random_user.model.repository.DataCache
import com.example.random_user.model.repository.LocalRepository
import dagger.Binds
import dagger.Module

@Module
interface LocalRepositoryBindModule {

    @Binds
    fun bindLocalRepository(localRepository: LocalRepository): DataCache<User, String>
}