package com.windranger.bookfinder.ui.bookmark

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.windranger.bookfinder.base.BaseVM
import com.windranger.domain.models.BookModel
import com.windranger.domain.usecases.bookmark.GetAllBookmarkUseCase
import kotlinx.coroutines.launch

class BookmarkVM(private val getAllBookmarkUseCase: GetAllBookmarkUseCase) : BaseVM() {
    val books = MutableLiveData<List<BookModel>>()

    fun getBookmarks() {
        viewModelScope.launch {
            getAllBookmarkUseCase.execute(null)
                .also { books.value = it }
        }
    }
}