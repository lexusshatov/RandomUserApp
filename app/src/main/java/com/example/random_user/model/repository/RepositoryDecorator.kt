package com.example.random_user.model.repository

import androidx.lifecycle.LiveData
import com.example.random_user.model.local.User
import com.example.random_user.model.local.UserBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.ArrayList

class RepositoryDecorator(
    private val database: LocalRepository,
    private val api: ApiRepository
    ) {

    fun getUsers(count: Int): LiveData<List<User>> {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val body = api.getUsers(count)
                body.results.apply {
                    val userList = ArrayList<User>(size)
                    forEach {
                        val user = UserBuilder(it.login.uuid)
                            .withGender(it.gender)
                            .withAge(it.dob.age)
                            .withName(it.name.first, it.name.last)
                            .withLocation(
                                it.location.country,
                                it.location.city,
                                it.location.street.name,
                                it.location.street.number
                            )
                            .withEmail(it.email)
                            .withPhone(it.phone)
                            .withPicture(it.picture.large)
                            .build()

                        userList.add(user)
                    }
                    database.saveUsers(userList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return database.getUsers()
    }

    fun getUserById(id: String) : LiveData<User> {
        return database.getUserById(id)
    }

    suspend fun saveUsers(users: List<User>) {
        database.saveUsers(users)
    }
}