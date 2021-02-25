package com.windranger.bookfinder.di

import com.windranger.domain.models.MyObjectBox
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { MyObjectBox.builder().androidContext(androidContext()).build() }
}