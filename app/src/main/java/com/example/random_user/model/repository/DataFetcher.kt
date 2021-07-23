package com.example.random_user.model.repository

interface DataFetcher<T> {

    suspend fun fetchData(count: Int): T
}