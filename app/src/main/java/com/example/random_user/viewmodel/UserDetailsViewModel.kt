package com.example.random_user.viewmodel

import com.example.random_user.base.BaseViewModel
import com.example.random_user.model.local.User
import com.example.random_user.model.repository.RepositoryDecorator

class UserDetailsViewModel(
    private val repository: RepositoryDecorator,
    private val userId: String
) : BaseViewModel<User>() {

    override val data by lazy {
        repository.getDataById(userId)
    }
}
