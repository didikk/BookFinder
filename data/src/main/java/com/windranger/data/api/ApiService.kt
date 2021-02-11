package com.windranger.data.api

import com.windranger.data.models.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String): BookResponse
}