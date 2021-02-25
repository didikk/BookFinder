package com.windranger.domain.usecases.bookmark

import com.windranger.domain.models.BookModel
import com.windranger.domain.repositories.BookRepo

class GetAllBookmarkUseCaseImpl(private val bookRepo: BookRepo) : GetAllBookmarkUseCase {
    override suspend fun execute(params: Any?): List<BookModel> {
        return bookRepo.getBookmarks()
    }
}