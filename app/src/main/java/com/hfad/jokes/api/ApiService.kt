package com.hfad.jokes.api

import com.hfad.jokes.data.Jokes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/jokes/random/{number}")
    fun fetchJokes(
        @Path("number") number: Int
    ): Call<Jokes>
}