package com.example.random_user.model.base

import android.content.Context
import androidx.room.Room
import com.example.random_user.model.local.UserDatabase
import com.example.random_user.model.remote.UserApi
import com.example.random_user.model.repository.ApiRepository
import com.example.random_user.model.repository.LocalRepository
import com.example.random_user.model.repository.RepositoryDecorator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val databaseName = "User_Database"
const val baseUrl = "https://randomuser.me/"

class DI {

    private var initialized = false
    private lateinit var contextProvider: () -> Context
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

    val repository by lazy {
        if (!initialized) throw UninitializedPropertyAccessException("DI not initialized")
        RepositoryDecorator(
            LocalRepository(database),
            ApiRepository(api)
        )
    }

    fun init(contextProvider: () -> Context) {
        this.contextProvider = contextProvider
        initialized = true
    }

    companion object {
        @Volatile
        private var INSTANCE: DI? = null

        fun getInstance(): DI {
            return INSTANCE ?: synchronized(DI::class) {
                INSTANCE = DI()
                INSTANCE!!
            }
        }
    }
}