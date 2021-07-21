package com.example.random_user.model.di.data.repository.remote

import com.example.random_user.model.di.data.DataComponent
import com.example.random_user.model.di.data.repository.RepositoryScope
import com.example.random_user.model.remote.Result
import com.example.random_user.model.repository.DataFetcher
import dagger.Component

@RepositoryScope
@Component(
    dependencies = [DataComponent::class],
    modules = [
        ApiRepoBindModule::class
    ]
)
interface ApiRepositoryComponent {

    @Cont

    fun getRepository(): DataFetcher<List<Result>>
}