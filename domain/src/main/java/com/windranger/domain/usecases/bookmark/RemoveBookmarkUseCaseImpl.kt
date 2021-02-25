package com.windranger.domain.usecases.bookmark

import com.windranger.domain.models.BookModel
import com.windranger.domain.models.BookModel_
import com.windranger.domain.repositories.BookRepo

class RemoveBookmarkUseCaseImpl(private val bookRepo: BookRepo) : RemoveBookmarkUseCase {
    override suspend fun execute(params: BookModel?) {
        params?.let {
            bookRepo.removeBookmark(BookModel_.uid, params)
        } ?: throw IllegalArgumentException("Params can`t be null")
    }
}