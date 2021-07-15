package com.example.random_user.model.repository

import androidx.lifecycle.LiveData
import com.example.random_user.model.local.User
import com.example.random_user.model.local.UserDatabase
import com.example.random_user.model.remote.UserApi
import com.example.random_user.model.remote.UsersInfoRetrofit

class LocalRepository(private val database: UserDatabase) {

    fun getUsers(): LiveData<List<User>> = database.userDao().getAll()

    fun getUserById(id: String): LiveData<User> = database.userDao().getUserById(id)

    suspend fun saveUsers(users: List<User>) = database.userDao().insert(users)
}