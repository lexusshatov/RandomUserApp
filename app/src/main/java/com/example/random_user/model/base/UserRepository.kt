package com.example.random_user.model.base

import androidx.lifecycle.LiveData
import com.example.random_user.model.local.User
import com.example.random_user.model.local.UserDatabase
import com.example.random_user.model.remote.UserApi
import com.example.random_user.model.remote.UsersInfoRetrofit

class UserRepository(private val database: UserDatabase, private val api: UserApi) {

    fun getUsersLocal(): LiveData<List<User>>
        = database.userDao().getAll()
    fun getUserById(id: String) : LiveData<User>
        = database.userDao().getUserById(id)
    suspend fun saveUsersLocal(users: List<User>)
        = database.userDao().insert(users)

    suspend fun getUsersRemote(count: Int): UsersInfoRetrofit
            = api.getUsers(count)
}