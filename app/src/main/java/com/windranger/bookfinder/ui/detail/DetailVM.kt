package com.windranger.bookfinder.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.windranger.bookfinder.base.BaseVM
import com.windranger.domain.models.BookModel
import com.windranger.domain.usecases.bookmark.AddBookmarkUseCase
import com.windranger.domain.usecases.bookmark.CheckDataInBookmarkUseCase
import com.windranger.domain.usecases.bookmark.RemoveBookmarkUseCase
import kotlinx.coroutines.launch

class DetailVM(
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase,
    private val checkDataInBookmarkUseCase: CheckDataInBookmarkUseCase,
) : BaseVM() {

    val isBookmarked = MutableLiveData(false)

    private var data: BookModel? = null

    fun setData(bookModel: BookModel) {
        data = bookModel
    }

    fun checkBookmark() {
        viewModelScope.launch {
            isBookmarked.value = checkDataInBookmarkUseCase.execute(data)
        }
    }

    fun addOrRemoveBookmark() {
        viewModelScope.launch {
            if (isBookmarked.value == true) {
                removeBookmarkUseCase.execute(data)
                isBookmarked.value = false
            } else {
                addBookmarkUseCase.execute(data)
                isBookmarked.value = true
            }
        }
    }
}