package com.example.random_user.viewmodel

import com.example.random_user.base.BaseViewModel
import com.example.random_user.model.repository.DataRepository
import com.example.random_user.model.repository.local.User
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class UserDetailsViewModel @AssistedInject constructor(
    private val repository: DataRepository,
    @Assisted private val userId: String
) : BaseViewModel<User>() {

    override val data by lazy {
        repository.getDataById(userId)
    }

    @AssistedFactory
    interface Factory {
        fun create(userId: String): UserDetailsViewModel
    }
}

