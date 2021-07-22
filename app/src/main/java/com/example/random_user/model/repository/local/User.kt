package com.example.random_user.model.repository.local

import androidx.room.*
import java.util.*

@TypeConverters(GenderConverter::class)
@Entity
data class User(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "gender") val gender: Gender,
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

enum class Gender(val gender: String) {
    MALE("male"),
    FEMALE("female")
}

class GenderConverter {

    @TypeConverter
    fun genderToEnum(gender: String) = enumValueOf<Gender>(gender.uppercase(Locale.getDefault()))

    @TypeConverter
    fun enumToGender(gender: Gender) = gender.gender
}

