package com.example.random_user.model.repository

import androidx.lifecycle.LiveData
import com.example.random_user.model.local.User
import com.example.random_user.model.remote.toUser

class RepositoryDecorator(
    private val database: LocalRepository,
    private val api: ApiRepository
) {

    suspend fun fetchData(count: Int) {
        try {
            val body = api.getUsers(count)
            val users = body.results.map { it.toUser() }
            saveUsers(users)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getUsers(): LiveData<List<User>> = database.getUsers()

    fun getUserById(id: String) = database.getUserById(id)

    private suspend fun saveUsers(users: List<User>) {
        database.saveUsers(users)
    }
}