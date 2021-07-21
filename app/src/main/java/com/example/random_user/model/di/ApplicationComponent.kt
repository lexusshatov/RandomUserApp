package com.example.random_user.model.di

import android.app.Application
import android.content.Context
import com.example.random_user.model.di.decorator.RepoDecoratorComponent
import com.example.random_user.model.repository.DataRepository
import com.example.random_user.view.details.UserDetailFragment
import com.example.random_user.view.list.UserListFragment
import dagger.Component
import dagger.android.ContributesAndroidInjector

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

    fun getDecorator(): DataRepository

    fun getContext(): Context

    @ContributesAndroidInjector
    @ApplicationScope
    fun injectUserListFragment(fragment: UserListFragment)

    @ContributesAndroidInjector
    @ApplicationScope
    fun injectUserDetailsFragment(fragment: UserDetailFragment)
}