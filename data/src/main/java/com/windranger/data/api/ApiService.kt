package com.windranger.data.api

import com.windranger.data.models.BookResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("volumes")
    fun getBooks(@Query("q") query: String): Single<BookResponse>
}