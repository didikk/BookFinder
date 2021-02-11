package com.windranger.bookfinder.di

import com.windranger.bookfinder.util.rx.AppSchedulerProvider
import com.windranger.bookfinder.util.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.dsl.module

val appModule = module {
    factory { CompositeDisposable() }

    factory<SchedulerProvider> { AppSchedulerProvider() }
}