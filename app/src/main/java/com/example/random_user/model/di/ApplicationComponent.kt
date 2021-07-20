package com.example.random_user.model.di

import android.app.Application
import android.content.Context
import com.example.random_user.model.di.decorator.RepoDecoratorComponent
import dagger.Component

@ApplicationScope
@Component(
    dependencies = [
        RepoDecoratorComponent::class
    ],
    modules = [
        ApplicationModule::class
    ]
)
interface ApplicationComponent {

    fun inject(app: Application)

    fun getContext(): Context
}