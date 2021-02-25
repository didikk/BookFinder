package com.windranger.domain.usecases.bookmark

import com.windranger.domain.models.BookModel
import com.windranger.domain.repositories.BookRepo

class AddBookmarkUseCaseImpl(private val bookRepo: BookRepo) : AddBookmarkUseCase {
    override suspend fun execute(params: BookModel?) {
        params?.let {
            bookRepo.addBookmark(params)
        } ?: throw IllegalArgumentException("Params can`t be null")
    }
}