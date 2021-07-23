package com.example.random_user.model.di.repository

import com.example.random_user.model.repository.DataCache
import com.example.random_user.model.repository.LocalRepository
import com.example.random_user.model.repository.local.User
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalRepoBindModule {

    @Singleton
    @Binds
    fun bindLocalRepo(localRepository: LocalRepository): DataCache<User, String>
}