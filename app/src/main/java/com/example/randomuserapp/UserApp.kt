package com.example.randomuserapp

import android.app.Application
import androidx.room.Room
import com.example.randomuserapp.model.retrofit.UserApi
import com.example.randomuserapp.model.room.UserDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val databaseName = "User_Database"
const val baseUrl = "https://randomuser.me/"

class UserApp: Application(){

    companion object {
        lateinit var database: UserDatabase
        lateinit var retrofit: UserApi
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            UserDatabase::class.java,
            databaseName)
            .fallbackToDestructiveMigration()
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)

    }
}