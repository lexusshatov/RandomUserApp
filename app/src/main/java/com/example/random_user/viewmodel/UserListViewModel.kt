package com.example.random_user.viewmodel

import com.example.random_user.base.BaseViewModel
import com.example.random_user.model.local.User
import com.example.random_user.model.repository.RepositoryDecorator

class UserListViewModel(private val repository: RepositoryDecorator) : BaseViewModel<List<User>>() {

    override val data by lazy {
        repository.getUsers(usersToLoad)
    }

    fun loadData() {
        repository.getUsers(usersToLoad)
    }

    companion object {
        private const val usersToLoad = 15
    }
}