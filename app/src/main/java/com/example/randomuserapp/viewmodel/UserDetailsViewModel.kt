package com.example.randomuserapp.viewmodel

import com.example.randomuserapp.base.BaseViewModel
import com.example.randomuserapp.model.base.UserRepository
import com.example.randomuserapp.model.local.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class UserDetailsViewModel(
    private val repository: UserRepository,
    private val userId: String
) : BaseViewModel<User>() {

    override val data by lazy {
        runBlocking(Dispatchers.IO) { repository.getUserById(userId) }
    }

}