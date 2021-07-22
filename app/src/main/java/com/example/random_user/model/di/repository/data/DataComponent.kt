package com.example.random_user.model.di.repository.data

import com.example.random_user.model.repository.local.UserDao
import com.example.random_user.model.repository.remote.UserApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        ApiModule::class
    ]
)
internal interface DataComponent {

    fun getApi(): UserApi

    fun getDao(): UserDao
}