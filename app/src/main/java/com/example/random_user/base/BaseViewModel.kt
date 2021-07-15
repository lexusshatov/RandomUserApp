package com.example.random_user.base

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.random_user.UserApp

abstract class BaseViewModel<Data> : AndroidViewModel(UserApp()) {

    abstract val data: LiveData<Data>
}

