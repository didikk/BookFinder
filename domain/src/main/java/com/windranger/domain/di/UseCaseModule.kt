package com.windranger.domain.di

import com.windranger.domain.usecases.GetBooksUseCase
import com.windranger.domain.usecases.GetBooksUseCaseImpl
import com.windranger.domain.usecases.bookmark.*
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetBooksUseCase> { GetBooksUseCaseImpl(get()) }

    factory<AddBookmarkUseCase> { AddBookmarkUseCaseImpl(get()) }

    factory<RemoveBookmarkUseCase> { RemoveBookmarkUseCaseImpl(get()) }

    factory<GetAllBookmarkUseCase> { GetAllBookmarkUseCaseImpl(get()) }

    factory<CheckDataInBookmarkUseCase> { CheckDataInBookmarkUseCaseImpl(get()) }
}