package com.example.random_user.model.base

import android.content.Context
import androidx.room.Room
import com.example.random_user.model.local.UserDatabase
import com.example.random_user.model.remote.UserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val databaseName = "User_Database"
const val baseUrl = "https://randomuser.me/"

object DI {

    private var initialized = false
    private lateinit var contextProvider: () -> Context
    val repository by lazy {
        if (!initialized) {
            throw UninitializedPropertyAccessException("DI not initialized")
        } else {
            UserRepository(database, api)
        }
    }
    private val database by lazy {
        Room.databaseBuilder(
            contextProvider(),
            UserDatabase::class.java,
            databaseName
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    fun init(contextProvider: () -> Context) {
        DI.contextProvider = contextProvider
        initialized = true
    }
}