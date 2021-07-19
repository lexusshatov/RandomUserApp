package com.example.random_user.model.di.repository

import com.example.random_user.model.repository.DataRepository
import com.example.random_user.model.repository.RepositoryDecorator
import dagger.Binds
import dagger.Module

@Module
interface RepositoryDecoratorBindModule {

    @Binds
    fun bindRepositoryDecorator(repositoryDecorator: RepositoryDecorator): DataRepository
}