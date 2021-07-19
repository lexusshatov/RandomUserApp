package com.example.random_user.model.di.repository

import com.example.random_user.model.repository.DataRepository
import dagger.Component
import dagger.Subcomponent

@Component(
    modules = [
        RepositoryDecoratorModule::class,
        RepositoryDecoratorBindModule::class
    ]
)
interface RepositoryDecoratorComponent {

    fun repository(): DataRepository
}