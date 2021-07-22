package com.example.random_user.model.di.decorator

import com.example.random_user.model.repository.DataRepository
import com.example.random_user.model.repository.RepositoryDecorator
import dagger.Binds
import dagger.Module

@Module
interface RepoDecoratorBindModule {

    @DecoratorScope
    @Binds
    fun bindRepoDecorator(repoDecorator: RepositoryDecorator): DataRepository
}