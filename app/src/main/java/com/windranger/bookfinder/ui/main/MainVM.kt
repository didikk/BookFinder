package com.windranger.bookfinder.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.windranger.bookfinder.base.BaseVM
import com.windranger.bookfinder.base.DataState
import com.windranger.domain.models.BookModel
import com.windranger.domain.usecases.GetBooksUseCase
import kotlinx.coroutines.launch

class MainVM(private val getBooksUseCase: GetBooksUseCase) : BaseVM() {

    val loading = MutableLiveData<Boolean>()
    val books = MutableLiveData<DataState<List<BookModel>>>()

    fun getBooks(query: String) {
        viewModelScope.launch {
            loading.value = true
            try {
                val response = getBooksUseCase.execute(query)
                books.value = DataState.Success(response)
            } catch (e: Throwable) {
                books.value = DataState.Fail(fetchErrorMessage(e))
            } finally {
                loading.value = false
            }
        }
    }
}