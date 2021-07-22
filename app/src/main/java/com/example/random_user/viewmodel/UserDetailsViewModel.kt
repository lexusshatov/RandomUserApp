package com.example.random_user.viewmodel

import com.example.random_user.base.BaseViewModel
import com.example.random_user.model.repository.local.User
import com.example.random_user.model.repository.DataRepository

class UserDetailsViewModel(
    private val repository: DataRepository,
    private val userId: String
) : BaseViewModel<User>() {

    override val data by lazy {
        repository.getDataById(userId)
    }
}
