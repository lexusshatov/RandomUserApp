package com.example.random_user

import android.app.Application
import com.example.random_user.model.base.DI

class UserApp: Application() {

    override fun onCreate() {
        super.onCreate()
        DI.init { applicationContext }
    }
}