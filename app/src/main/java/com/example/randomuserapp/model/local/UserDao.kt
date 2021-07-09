package com.example.randomuserapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE id=:id")
    fun getUserById(id: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<User>)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}