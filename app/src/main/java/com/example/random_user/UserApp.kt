package com.example.random_user

import com.example.random_user.model.di.DaggerApplicationComponent
import com.example.random_user.model.di.decorator.DaggerRepoDecoratorComponent
import com.example.random_user.model.di.repository.data.DaggerDataComponent
import com.example.random_user.model.di.repository.data.DatabaseModule
import com.example.random_user.model.di.repository.local.DaggerLocalRepositoryComponent
import com.example.random_user.model.di.repository.remote.DaggerApiRepositoryComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class UserApp : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val dataComponent = DaggerDataComponent.builder()
            .databaseModule(DatabaseModule(applicationContext))
            .build()
        val repoApiComponent = DaggerApiRepositoryComponent.builder()
            .dataComponent(dataComponent)
            .build()
        val repoLocalComponent = DaggerLocalRepositoryComponent.builder()
            .dataComponent(dataComponent)
            .build()
        val repoDecoratorComponent = DaggerRepoDecoratorComponent.builder()
            .apiRepositoryComponent(repoApiComponent)
            .localRepositoryComponent(repoLocalComponent)
            .build()
        return DaggerApplicationComponent.builder()
            .application(this)
            .decorator(repoDecoratorComponent)
            .build()
    }
}