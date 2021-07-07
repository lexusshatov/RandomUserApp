package com.example.randomuserapp.model.retrofit

import com.example.randomuserapp.model.room.User

fun UsersInfoRetrofit.convert(): List<User> {
     return this.results.map {
         User(
             gender = it.gender,
             firstName = it.name.first,
             lastName = it.name.last,
             country = it.location.country,
             city = it.location.city,
             street = it.location.street.name,
             streetNumber = it.location.street.number,
             email = it.email,
             pictureUrl = it.picture.large
         )
     }
 }