package com.windranger.domain.di

import com.windranger.domain.usecases.GetBooksUseCase
import com.windranger.domain.usecases.GetBooksUseCaseImpl
import com.windranger.domain.usecases.bookmark.AddBookmarkUseCase
import com.windranger.domain.usecases.bookmark.AddBookmarkUseCaseImpl
import com.windranger.domain.usecases.bookmark.RemoveBookmarkUseCase
import com.windranger.domain.usecases.bookmark.RemoveBookmarkUseCaseImpl
import com.windranger.domain.usecases.bookmark.GetAllBookmarkUseCase
import com.windranger.domain.usecases.bookmark.GetAllBookmarkUseCaseImpl
import com.windranger.domain.usecases.bookmark.CheckDataInBookmarkUseCase
import com.windranger.domain.usecases.bookmark.CheckDataInBookmarkUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetBooksUseCase> { GetBooksUseCaseImpl(get()) }

    factory<AddBookmarkUseCase> { AddBookmarkUseCaseImpl(get()) }

    factory<RemoveBookmarkUseCase> { RemoveBookmarkUseCaseImpl(get()) }

    factory<GetAllBookmarkUseCase> { GetAllBookmarkUseCaseImpl(get()) }

    factory<CheckDataInBookmarkUseCase> { CheckDataInBookmarkUseCaseImpl(get()) }
}