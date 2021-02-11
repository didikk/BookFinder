package com.windranger.bookfinder.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.windranger.bookfinder.App
import com.windranger.bookfinder.BuildConfig
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import java.io.File

val cacheModule = module {
    // provide cache
    single { createCache(androidContext()) }

    // provide gson
    single { createGson() }

    // provide logging interceptor
    single { createHttpLoggingInterceptor() }

    // provide cache interceptor
    single(named("cache")) { createCacheInterceptor() }

    // provide offline cache
    single(named("offlineCache")) { createOfflineCacheInterceptor(androidApplication() as App) }
}


fun createCache(context: Context): Cache {
    val cacheSize = 100 * 1024 * 1024 // 100 MiB
    return Cache(File(context.cacheDir, "http-cache"), cacheSize.toLong())
}

fun createGson(): Gson {
    val gsonBuilder = GsonBuilder()
    //gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    return gsonBuilder.create()
}

fun createHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (BuildConfig.DEBUG) BODY else NONE
    return logging
}

fun createCacheInterceptor(): Interceptor {
    return Interceptor { chain ->
        val response = chain.proceed(chain.request())

        // re-write response header to force use of cache
        val cacheControl = CacheControl.Builder()
            .maxAge(2, TimeUnit.MINUTES)
            .build()

        response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}

fun createOfflineCacheInterceptor(app: App): Interceptor {
    return Interceptor { chain ->
        var request = chain.request()

        if (!app.hasNetwork()) {
            val cacheControl = CacheControl.Builder()
                .maxStale(7, TimeUnit.DAYS)
                .build()

            request = request.newBuilder()
                .cacheControl(cacheControl)
                .build()
        }

        chain.proceed(request)
    }
}