package com.example.random_user.model.di.data

import android.content.Context
import com.example.random_user.model.local.UserDatabase
import com.example.random_user.model.remote.UserApi
import dagger.Subcomponent

@Subcomponent(modules = [ApiModule::class, LocalModule::class])
interface DataComponent {

    fun inject(context: Context)
    fun userApi(): UserApi
    fun localDatabase(): UserDatabase
}