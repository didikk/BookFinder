package com.windranger.bookfinder.ui.main

import androidx.lifecycle.viewModelScope
import com.windranger.bookfinder.base.BaseVM
import com.windranger.domain.usecases.GetBooksUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class MainVM(private val getBooksUseCase: GetBooksUseCase) : BaseVM() {

    fun getBooks() {
        viewModelScope.launch {
            try {
                val response = getBooksUseCase.execute("")
                Timber.d("getBooks: $response")
            } catch (e: Throwable) {
                Timber.d("getBooks: ${fetchErrorMessage(e)}")
            }
        }
    }
}