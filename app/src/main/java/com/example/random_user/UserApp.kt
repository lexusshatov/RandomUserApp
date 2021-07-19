package com.example.random_user

import android.app.Application
import com.example.random_user.model.di.AppComponent
import com.example.random_user.model.di.DaggerAppComponent
import com.example.random_user.model.di.repository.RepositoryDecoratorComponent
import javax.inject.Inject

class UserApp: Application() {
    @Inject
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        //DI.getInstance().init { applicationContext }
        DaggerAppComponent.builder()
            .build().inject(this)
    }
}