package com.example.randomuserapp.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "street") val street: String,
    @ColumnInfo(name = "street_number") val streetNumber: Int,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "picture_url") val pictureUrl: String
)
