package com.example.random_user.model.di.repository.local

import com.example.random_user.model.di.repository.RepositoryScope
import com.example.random_user.model.repository.local.User
import com.example.random_user.model.repository.DataCache
import com.example.random_user.model.repository.LocalRepository
import dagger.Binds
import dagger.Module

@Module
interface LocalRepoBindModule {

    @RepositoryScope
    @Binds
    fun bindLocalRepo(localRepository: LocalRepository): DataCache<User, String>
}