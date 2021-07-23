package com.example.random_user.model.repository

import androidx.lifecycle.LiveData

interface DataCache<T, ID> {

    fun getAllData(): LiveData<List<T>>

    fun getDataById(id: ID): LiveData<T>

    suspend fun saveData(users: List<T>)
}