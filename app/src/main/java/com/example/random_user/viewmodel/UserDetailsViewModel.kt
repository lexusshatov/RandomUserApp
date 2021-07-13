package com.example.random_user.viewmodel

import com.example.random_user.base.BaseViewModel
import com.example.random_user.model.base.UserRepository
import com.example.random_user.model.local.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class UserDetailsViewModel(
    private val repository: UserRepository,
    private val userId: String
) : BaseViewModel<User>() {

    override val data by lazy {
        repository.getUserById(userId)
    }
}
