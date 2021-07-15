package com.example.random_user.model.repository

interface DataFetcher<out T> {

    suspend fun fetchData(count: Int): T
}