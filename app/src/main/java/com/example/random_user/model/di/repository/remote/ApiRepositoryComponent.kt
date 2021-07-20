package com.example.random_user.model.di.repository.remote

import com.example.random_user.model.di.data.DataComponent
import com.example.random_user.model.di.repository.RepositoryScope
import com.example.random_user.model.remote.Result
import com.example.random_user.model.repository.DataFetcher
import dagger.Component

@RepositoryScope
@Component(
    dependencies = [DataComponent::class],
    modules = [
        ApiRepositoryModule::class,
        ApiRepoBindModule::class
    ]
)
interface ApiRepositoryComponent {

    fun getRepository(): DataFetcher<List<Result>>
}