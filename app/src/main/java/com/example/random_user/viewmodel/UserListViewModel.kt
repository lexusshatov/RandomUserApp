package com.example.random_user.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.random_user.base.BaseViewModel
import com.example.random_user.model.local.User
import com.example.random_user.model.repository.RepositoryDecorator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListViewModel(private val repository: RepositoryDecorator) : BaseViewModel<List<User>>() {

    override val data by lazy {
        repository.getUsers()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchData(usersToLoad)
        }
    }

    companion object {
        private const val usersToLoad = 15
    }
}