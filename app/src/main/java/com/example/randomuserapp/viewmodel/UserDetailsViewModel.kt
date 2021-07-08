package com.example.randomuserapp.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.randomuserapp.UserApp
import com.example.randomuserapp.base.BaseViewModel
import com.example.randomuserapp.model.local.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDetailsViewModel(val userId: Long): BaseViewModel<User>() {

    override fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = UserApp.database.userDao().getUserById(userId)
            withContext(Dispatchers.Main) {
                if (user != null) {
                    mutableData.value = user
                }
            }
        }
    }
}