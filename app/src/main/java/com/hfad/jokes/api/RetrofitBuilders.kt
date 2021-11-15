package com.hfad.jokes.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun buildRetrofit() : Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.icndb.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun buildApiService() : ApiService = buildRetrofit().create(ApiService::class.java)