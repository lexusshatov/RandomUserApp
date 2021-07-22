package com.example.random_user.model.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 8)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}