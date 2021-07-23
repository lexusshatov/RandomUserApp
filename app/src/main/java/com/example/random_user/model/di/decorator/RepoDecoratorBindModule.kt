package com.example.random_user.model.di.decorator

import com.example.random_user.model.repository.DataRepository
import com.example.random_user.model.repository.RepositoryDecorator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RepoDecoratorBindModule {

    @ActivityRetainedScoped
    @Binds
    fun bindRepoDecorator(repoDecorator: RepositoryDecorator): DataRepository
}