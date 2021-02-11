package com.windranger.data.di

import com.windranger.data.mappers.BookResponseMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { BookResponseMapper() }
}