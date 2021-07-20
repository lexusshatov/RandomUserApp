package com.example.random_user.model.di.data

import android.content.Context
import androidx.room.Room
import com.example.random_user.model.local.UserDao
import com.example.random_user.model.local.UserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

const val databaseName = "User_Database"

@Module
class DatabaseModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideDatabase(): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            databaseName
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }
}