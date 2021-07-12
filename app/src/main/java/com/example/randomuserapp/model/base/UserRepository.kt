package com.example.randomuserapp.model.base

import androidx.lifecycle.LiveData
import com.example.randomuserapp.model.local.User
import com.example.randomuserapp.model.local.UserDatabase
import com.example.randomuserapp.model.remote.UserApi
import com.example.randomuserapp.model.remote.UsersInfoRetrofit
import retrofit2.Call

class UserRepository(private val database: UserDatabase, private val api: UserApi) {

    fun getUsersLocal(): LiveData<List<User>>
        = database.userDao().getAll()
    fun getUserById(id: String) : LiveData<User>
        = database.userDao().getUserById(id)
    suspend fun saveUsersLocal(users: List<User>)
        = database.userDao().insert(users)

    suspend fun getUsersRemote(count: Int): Call<UsersInfoRetrofit>
            = api.getUsers(count)
}