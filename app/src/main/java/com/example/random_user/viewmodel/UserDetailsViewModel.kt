package com.example.random_user.viewmodel

import android.util.Log
import com.example.random_user.base.BaseViewModel
import com.example.random_user.model.repository.DataRepository
import com.example.random_user.model.repository.local.User
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class UserDetailsViewModel (
    private val repository: DataRepository,
    private val userId: String
) : BaseViewModel<User>() {

    override val data by lazy {
        Log.d("UserDetails", "UserID: $userId")
        repository.getDataById(userId)
    }
}
