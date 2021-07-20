package com.example.random_user

import android.app.Application
import com.example.random_user.model.di.ApplicationModule
import com.example.random_user.model.di.DaggerApplicationComponent
import com.example.random_user.model.di.data.ApiModule
import com.example.random_user.model.di.data.DaggerDataComponent
import com.example.random_user.model.di.data.DatabaseModule
import com.example.random_user.model.di.decorator.DaggerRepoDecoratorComponent
import com.example.random_user.model.di.decorator.RepoDecoratorComponent
import com.example.random_user.model.di.decorator.RepoDecoratorModule
import com.example.random_user.model.di.repository.local.DaggerLocalRepositoryComponent
import com.example.random_user.model.di.repository.local.LocalRepositoryModule
import com.example.random_user.model.di.repository.remote.ApiRepositoryModule
import com.example.random_user.model.di.repository.remote.DaggerApiRepositoryComponent
import com.example.random_user.model.repository.DataRepository

class UserApp: Application() {
    lateinit var decorator: DataRepository

    override fun onCreate() {
        super.onCreate()

        val dataComponent = DaggerDataComponent.builder()
            .apiModule(ApiModule())
            .databaseModule(DatabaseModule(this))
            .build()
        val repoApiComponent = DaggerApiRepositoryComponent.builder()
            .apiRepositoryModule(ApiRepositoryModule())
            .dataComponent(dataComponent)
            .build()
        val repoLocalComponent = DaggerLocalRepositoryComponent.builder()
            .localRepositoryModule(LocalRepositoryModule())
            .dataComponent(dataComponent)
            .build()
        val repoDecoratorComponent = DaggerRepoDecoratorComponent.builder()
            .repoDecoratorModule(RepoDecoratorModule())
            .apiRepositoryComponent(repoApiComponent)
            .localRepositoryComponent(repoLocalComponent)
            .build()
        val appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .repoDecoratorComponent(repoDecoratorComponent)
            .build()
        appComponent.inject(this)

        decorator = appComponent.getDecorator()
    }
}