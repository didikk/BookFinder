package com.windranger.bookfinder

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.windranger.bookfinder.di.appModule
import com.windranger.bookfinder.di.cacheModule
import com.windranger.bookfinder.di.vmModule
import com.windranger.data.di.mapperModule
import com.windranger.data.di.netModule
import com.windranger.data.di.repoModule
import com.windranger.domain.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    cacheModule,
                    netModule,
                    mapperModule,
                    repoModule,
                    useCaseModule,
                    vmModule,
                    appModule
                )
            )
        }
    }

    fun hasNetwork(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}