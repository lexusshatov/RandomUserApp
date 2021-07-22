package com.example.random_user.model.repository

import com.example.random_user.model.repository.local.User
import com.example.random_user.model.repository.local.UserDao
import javax.inject.Inject

class LocalRepository @Inject constructor(private val database: UserDao)
    : DataCache<User, String> {

    override fun getAllData() = database.getAll()

    override fun getDataById(id: String) = database.getUserById(id)

    override suspend fun saveData(users: List<User>) = database.insert(users)
}