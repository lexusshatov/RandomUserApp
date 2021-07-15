package com.example.random_user.model.local

class UserBuilder(private val id: String) {
    private var gender = Gender.MALE
    private var age = 20
    private var firstName = "John"
    private var lastName = "Smith"
    private var country = "United States"
    private var city = "New York"
    private var street = "Broadway"
    private var streetNumber = 1
    private var email = "example@gmail.com"
    private var phone = "3-800-0000-00-00"
    private var pictureUrl = ""

    fun withGender(gender: Gender): UserBuilder {
        this.gender = gender
        return this
    }

    fun withAge(age: Int): UserBuilder {
        this.age = age
        return this
    }

    fun withName(firstName: String, lastName: String): UserBuilder {
        this.firstName = firstName
        this.lastName = lastName
        return this
    }

    fun withStreet(street: String, streetNumber: Int): UserBuilder {
        this.street = street
        this.streetNumber = streetNumber
        return this
    }

    fun withCountry(country: String): UserBuilder {
        this.country = country
        return this
    }

    fun withCity(city: String): UserBuilder {
        this.city = city
        return this
    }

    fun withEmail(email: String): UserBuilder {
        this.email = email
        return this
    }

    fun withPhone(phone: String): UserBuilder {
        this.phone = phone
        return this
    }

    fun withPicture(url: String): UserBuilder {
        this.pictureUrl = url
        return this
    }

    fun build() = User(
        id = id,
        gender = gender,
        age = age,
        firstName = firstName,
        lastName = lastName,
        country = country,
        city = city,
        street = street,
        streetNumber = streetNumber,
        email = email,
        phone = phone,
        pictureUrl = pictureUrl
    )
}