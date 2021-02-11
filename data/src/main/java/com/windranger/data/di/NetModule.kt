package com.windranger.data.di

import com.google.gson.Gson
import com.windranger.data.api.ApiService
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val netModule = module {
    // provide okhttp client
    single { createOkHttpClient(get(), get(), get(named("cache")), get(named("offlineCache"))) }

    // provide retrofit
    single { createRetrofit(get(), get()) }

    // provide service
    single { createApi(get()) }

}

fun createOkHttpClient(
    cache: Cache, loggingInterceptor: HttpLoggingInterceptor,
    cacheInterceptor: Interceptor,
    offlineCacheInterceptor: Interceptor,
): OkHttpClient {

    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(offlineCacheInterceptor)
        .addNetworkInterceptor(cacheInterceptor)
        .cache(cache)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
}

fun createRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
    val BASE_URL = "https://www.googleapis.com/books/v1/"
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
}

fun createApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)