package com.example.randomuserapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<Data>: ViewModel() {

    protected val mutableData = MutableLiveData<Data>()
    val data: LiveData<Data> = mutableData

    abstract fun loadData()
}