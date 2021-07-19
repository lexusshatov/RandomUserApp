package com.example.random_user.model.di

import android.app.Application
import com.example.random_user.model.di.repository.RepositoryDecoratorComponent
import com.example.random_user.model.repository.DataRepository
import dagger.Component
import dagger.Provides
import javax.inject.Inject

@Component(dependencies = [RepositoryDecoratorComponent::class])
interface AppComponent {

    fun inject(application: Application)
}