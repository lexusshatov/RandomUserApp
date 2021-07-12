package com.example.randomuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.randomuserapp.base.BaseViewModel
import com.example.randomuserapp.model.base.UserRepository
import com.example.randomuserapp.model.local.User
import com.example.randomuserapp.utils.convert
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
                val response = repository.getUsersRemote(usersToLoad).execute()
                if (response.isSuccessful) {
                    response.body()?.convert()?.let {
                        repository.saveUsersLocal(it)
                    }
                }
            } catch (error: Exception) {
                Log.d(TAG, error.toString())
            }
        }
    }

    companion object {
        private const val usersToLoad = 10
    }
}