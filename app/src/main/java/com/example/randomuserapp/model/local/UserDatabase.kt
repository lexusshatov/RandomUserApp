package com.example.randomuserapp.model.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 7)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}