package com.windranger.domain.usecases.bookmark

import com.windranger.domain.models.BookModel
import com.windranger.domain.models.BookModel_
import com.windranger.domain.repositories.BookRepo

class CheckDataInBookmarkUseCaseImpl(private val bookRepo: BookRepo) : CheckDataInBookmarkUseCase {
    override suspend fun execute(params: BookModel?): Boolean {
        params?.let {
            return bookRepo.isInBookmark(BookModel_.uid, params)
        } ?: throw IllegalArgumentException("Params can`t be null")
    }
}