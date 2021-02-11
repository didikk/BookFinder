package com.windranger.domain.di

import com.windranger.domain.usecases.GetBooksUseCase
import com.windranger.domain.usecases.GetBooksUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetBooksUseCase> { GetBooksUseCaseImpl(get()) }
}