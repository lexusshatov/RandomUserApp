package com.example.randomuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.randomuserapp.UserApp
import com.example.randomuserapp.base.BaseViewModel
import com.example.randomuserapp.model.local.User
import com.example.randomuserapp.model.remote.convert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class UserListViewModel: BaseViewModel<List<User>>() {

    private val usersToLoad = 50
    private val TAG = this.javaClass.simpleName

    override fun loadData() {
        loadRemoteData()
        loadLocalData()
    }

    private fun loadLocalData() {
        viewModelScope.launch (Dispatchers.IO) {
            val users = UserApp.database.userDao().getAll()
            withContext(Dispatchers.Main){
                mutableData.value = users.reversed()
            }
        }
    }

    private fun loadRemoteData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                val response = UserApp.retrofit.getUsers(usersToLoad).execute()
                val users = response.body()?.convert()
                if (users != null) {
                    saveToLocalBase(users)
                }
            } catch (error: Exception){
                Log.e(TAG, error.toString())
            }
        }
    }

    private fun saveToLocalBase(users: List<User>){
        users.forEach {
            UserApp.database.userDao().insert(it)
        }
    }
}