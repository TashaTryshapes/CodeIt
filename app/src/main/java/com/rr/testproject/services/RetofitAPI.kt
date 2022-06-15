package com.rr.testproject.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetofitAPI {
    private const val baseUrl = "https://jsonplaceholder.typicode.com"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}