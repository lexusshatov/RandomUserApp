package com.example.random_user.model.di.data.repository.local

import com.example.random_user.model.local.User
import com.example.random_user.model.repository.DataCache
import com.example.random_user.model.repository.LocalRepository
import dagger.Binds
import dagger.Module

@Module
interface LocalRepoBindModule {

    @Binds
    fun bindLocalRepo(localRepository: LocalRepository): DataCache<User, String>
}