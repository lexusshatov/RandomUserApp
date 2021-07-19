package com.example.random_user.model.di.repository.base.data

import com.example.random_user.model.remote.UserApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val baseUrl = "https://randomuser.me/"

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }
}