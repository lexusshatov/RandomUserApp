package com.example.random_user.model.di.decorator

import com.example.random_user.model.local.User
import com.example.random_user.model.remote.Result
import com.example.random_user.model.repository.*
import dagger.Module
import dagger.Provides

@Module
class RepoDecoratorModule {

    @Provides
    fun provideRepoDecorator(
        apiRepo: DataFetcher<List<Result>>,
        localRepo: DataCache<User, String>
    ) = RepositoryDecorator(apiRepo, localRepo)
}