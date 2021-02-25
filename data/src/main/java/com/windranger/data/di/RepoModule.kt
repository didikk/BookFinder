package com.windranger.data.di

import com.windranger.data.repo.BookRepoImpl
import com.windranger.domain.repositories.BookRepo
import org.koin.dsl.module

val repoModule = module {
    single<BookRepo> { BookRepoImpl(get(), get(), get()) }
}