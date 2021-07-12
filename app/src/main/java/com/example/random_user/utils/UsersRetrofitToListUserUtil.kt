package com.example.random_user.utils

import com.example.random_user.model.local.User
import com.example.random_user.model.remote.UsersInfoRetrofit

fun UsersInfoRetrofit.convert(): List<User> {
     return this.results.map {
         User(
             id = it.login.uuid,
             gender = it.gender,
             age = it.dob.age,
             firstName = it.name.first,
             lastName = it.name.last,
             country = it.location.country,
             city = it.location.city,
             street = it.location.street.name,
             streetNumber = it.location.street.number,
             email = it.email,
             phone = it.phone,
             pictureUrl = it.picture.large
         )
     }
 }