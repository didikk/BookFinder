package com.windranger.bookfinder.di

import com.windranger.bookfinder.ui.main.MainPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory { MainPresenter(get(), get(), get()) }
}