package com.example.randomuserapp

import android.app.Application
import com.example.randomuserapp.model.base.DI

class UserApp: Application() {

    override fun onCreate() {
        super.onCreate()
        DI.init { applicationContext }
    }
}