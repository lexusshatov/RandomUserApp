package com.example.random_user.model.repository

import com.example.random_user.model.local.User
import com.example.random_user.model.local.UserDatabase

class LocalRepository(private val database: UserDatabase) : DataCache<User, String> {

    override fun getAllData() = database.userDao().getAll()

    override fun getDataById(id: String) = database.userDao().getUserById(id)

    override suspend fun saveData(users: List<User>) = database.userDao().insert(users)
}