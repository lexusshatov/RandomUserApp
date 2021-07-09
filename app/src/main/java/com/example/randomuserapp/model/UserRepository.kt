package com.example.randomuserapp.model

import androidx.lifecycle.LiveData
import com.example.randomuserapp.UserApp
import com.example.randomuserapp.model.local.User
import com.example.randomuserapp.model.remote.UsersInfoRetrofit
import retrofit2.Call

object UserRepository {

    fun getUsersLocal(): LiveData<List<User>>
        = UserApp.database.userDao().getAll()
    fun getUsersRemote(count: Int): Call<UsersInfoRetrofit>
        = UserApp.retrofit.getUsers(count)

    fun saveUsersLocal(users: List<User>)
        = UserApp.database.userDao().insert(users)
    fun deleteUserLocal(user: User)
        = UserApp.database.userDao().delete(user)
}