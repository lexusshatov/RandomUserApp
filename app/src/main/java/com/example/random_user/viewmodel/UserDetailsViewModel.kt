package com.example.random_user.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.example.random_user.base.BaseViewModel
import com.example.random_user.model.repository.DataRepository
import com.example.random_user.model.repository.local.User
import com.example.random_user.utils.emptyString
import com.example.random_user.view.details.UserDetailsFragment.Companion.ARG_USER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val repository: DataRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<User>() {

    override val data by lazy {
        val userId = savedStateHandle.get<String>(ARG_USER_ID)
        Log.d("UserDetails", "UserID: $userId")
        repository.getDataById(userId ?: emptyString())
    }
}
