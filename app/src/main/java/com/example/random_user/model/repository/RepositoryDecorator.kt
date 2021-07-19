package com.example.random_user.model.repository

import com.example.random_user.model.local.User
import com.example.random_user.model.remote.Result
import com.example.random_user.model.remote.toUser
import javax.inject.Inject

interface DataRepository : DataFetcher<Unit>, DataCache<User, String>

class RepositoryDecorator @Inject constructor(
    private val api: DataFetcher<List<Result>>,
    private val database: DataCache<User, String>
) : DataRepository {

    override suspend fun fetchData(count: Int) {
        try {
            val body = api.fetchData(count)
            val users = body.map { it.toUser() }
            saveData(users)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getAllData() = database.getAllData()

    override fun getDataById(id: String) = database.getDataById(id)

    override suspend fun saveData(users: List<User>) {
        database.saveData(users)
    }
}