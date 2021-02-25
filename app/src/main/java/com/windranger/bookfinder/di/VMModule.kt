package com.windranger.bookfinder.di

import com.windranger.bookfinder.ui.detail.DetailVM
import com.windranger.bookfinder.ui.main.MainVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { MainVM(get()) }

    viewModel { DetailVM(get(), get(), get()) }
}