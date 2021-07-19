package com.example.random_user.model.di.data

import android.content.Context
import androidx.room.Room
import com.example.random_user.model.base.databaseName
import com.example.random_user.model.local.UserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideLocalDatabase(): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            databaseName
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}