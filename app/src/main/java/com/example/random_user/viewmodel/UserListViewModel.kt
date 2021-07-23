package com.example.random_user.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.random_user.base.BaseViewModel
import com.example.random_user.model.repository.DataRepository
import com.example.random_user.model.repository.local.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListViewModel(private val repository: DataRepository) : BaseViewModel<List<User>>() {

    override val data by lazy {
        repository.getAllData()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchData(usersToLoad)
        }
    }

    companion object {
        private const val usersToLoad = 20
    }
}