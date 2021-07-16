package com.example.random_user.model.repository

import androidx.lifecycle.LiveData

interface DataCache<T, in ID> {

    fun getAllData(): LiveData<List<T>>

    fun getDataById(id: ID): LiveData<out T>

    suspend fun saveData(users: List<T>)
}