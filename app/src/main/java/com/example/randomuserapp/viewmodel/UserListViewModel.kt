package com.example.randomuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.randomuserapp.base.BaseViewModel
import com.example.randomuserapp.model.UserRepository
import com.example.randomuserapp.model.local.User
import com.example.randomuserapp.utils.convert
import com.example.randomuserapp.view.list.UserListFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

private val TAG = UserListViewModel::class.java.simpleName

class UserListViewModel: BaseViewModel<List<User>>() {
    private val usersToLoad = 10
    private val localData = UserRepository.getUsersLocal()
    private val localDataObserver = Observer<List<User>> {
        mutableData.postValue(it)
    }

    override fun loadData() {
        viewModelScope.launch (Dispatchers.IO) {
            UserRepository.getUsersLocal().let {
                withContext(Dispatchers.Main) {
                    it.observeForever(localDataObserver)
                }
            }
            loadRemote()
        }
    }

    fun loadRemote() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                val response = UserRepository.getUsersRemote(usersToLoad).execute()
                if (response.isSuccessful) {
                    response.body()?.convert()?.let {
                        UserRepository.saveUsersLocal(it)
                    }
                }
            } catch (error: Exception) {
                Log.d(TAG, error.toString())
            }
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch (Dispatchers.IO) {
            UserRepository.deleteUserLocal(user)
        }
    }

    override fun onCleared() {
        localData.removeObserver(localDataObserver)
        super.onCleared()
    }
}