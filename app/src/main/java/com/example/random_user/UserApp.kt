package com.example.random_user

import android.app.Application
import com.example.random_user.model.di.ApplicationModule
import com.example.random_user.model.di.DaggerApplicationComponent
import com.example.random_user.model.di.data.ApiModule
import com.example.random_user.model.di.data.DaggerDataComponent
import com.example.random_user.model.di.data.DatabaseModule
import com.example.random_user.model.di.data.repository.local.DaggerLocalRepositoryComponent
import com.example.random_user.model.di.data.repository.remote.DaggerApiRepositoryComponent
import com.example.random_user.model.di.decorator.DaggerRepoDecoratorComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class UserApp: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        val dataComponent = DaggerDataComponent.builder()
            .apiModule(ApiModule())
            .databaseModule(DatabaseModule(this))
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
        val appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .repoDecoratorComponent(repoDecoratorComponent)
            .build()
        appComponent.inject(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        //
    }
}