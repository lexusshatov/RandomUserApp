package com.example.random_user.model.di.data

import com.example.random_user.model.di.ApplicationModule
import com.example.random_user.model.local.UserDao
import com.example.random_user.model.remote.UserApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        DatabaseModule::class,
        ApiModule::class
    ]
)
interface DataComponent {

    fun getApi(): UserApi

    fun getDao(): UserDao
}