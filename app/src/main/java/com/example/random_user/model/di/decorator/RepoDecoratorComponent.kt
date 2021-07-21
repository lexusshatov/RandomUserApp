package com.example.random_user.model.di.decorator

import com.example.random_user.model.di.data.repository.local.LocalRepositoryComponent
import com.example.random_user.model.di.data.repository.remote.ApiRepositoryComponent
import com.example.random_user.model.repository.DataRepository
import dagger.Component

@DecoratorScope
@Component(
    dependencies = [
        LocalRepositoryComponent::class,
        ApiRepositoryComponent::class
    ],
    modules = [
        RepoDecoratorBindModule::class
    ]
)
interface RepoDecoratorComponent {

    fun repositoryDecorator(): DataRepository
}