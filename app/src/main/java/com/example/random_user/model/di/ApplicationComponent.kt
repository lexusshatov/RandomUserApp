package com.example.random_user.model.di

import com.example.random_user.UserApp
import com.example.random_user.model.di.decorator.RepoDecoratorComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModule::class
    ],
    dependencies = [
        RepoDecoratorComponent::class
    ]
)
interface ApplicationComponent : AndroidInjector<UserApp> {
    override fun inject(app: UserApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: UserApp): Builder

        fun decorator(decoratorComponent: RepoDecoratorComponent): Builder

        fun build(): ApplicationComponent
    }
}