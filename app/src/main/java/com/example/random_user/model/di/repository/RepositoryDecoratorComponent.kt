package com.example.random_user.model.di.repository

import com.example.random_user.model.repository.DataRepository
import dagger.Subcomponent

@Subcomponent(
    modules = [
        RepositoryDecoratorModule::class,
        RepositoryDecoratorBindModule::class
    ]
)
interface RepositoryDecoratorComponent {

    fun repository(): DataRepository
}