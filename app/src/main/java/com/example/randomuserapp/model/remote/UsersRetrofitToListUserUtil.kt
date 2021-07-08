package com.example.randomuserapp.model.remote

import com.example.randomuserapp.model.local.User

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
             phone = it.phone,
             pictureUrl = it.picture.large
         )
     }
 }