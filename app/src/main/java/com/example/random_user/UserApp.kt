package com.example.random_user

import android.app.Application
import com.example.random_user.model.di.dataModule
import com.example.random_user.model.di.decoratorModule
import com.example.random_user.model.di.repositoryModule
import com.example.random_user.model.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UserApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UserApp.applicationContext)
            modules(listOf(
                dataModule,
                repositoryModule,
                decoratorModule,
                viewModelModule
            ))
        }
    }
}