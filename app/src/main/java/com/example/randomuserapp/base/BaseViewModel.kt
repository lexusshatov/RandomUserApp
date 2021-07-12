package com.example.randomuserapp.base

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.randomuserapp.UserApp

abstract class BaseViewModel<Data>: AndroidViewModel(UserApp()) {

    abstract val data: LiveData<Data>
}