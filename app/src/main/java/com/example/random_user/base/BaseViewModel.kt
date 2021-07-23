package com.example.random_user.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

abstract class BaseViewModel<Data> : ViewModel() {

    abstract val data: LiveData<Data>
}

