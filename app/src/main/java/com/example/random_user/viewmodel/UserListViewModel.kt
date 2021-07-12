package com.example.random_user.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.random_user.base.BaseViewModel
import com.example.random_user.model.base.UserRepository
import com.example.random_user.model.local.User
import com.example.random_user.utils.convert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val TAG = UserListViewModel::class.java.simpleName

class UserListViewModel(private val repository: UserRepository) : BaseViewModel<List<User>>() {

    override val data by lazy {
        repository.getUsersLocal()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val body = repository.getUsersRemote(usersToLoad)
                repository.saveUsersLocal(body.convert())
            } catch (error: Exception) {
                Log.d(TAG, error.toString())
            }
        }
    }

    companion object {
        private const val usersToLoad = 10
    }
}