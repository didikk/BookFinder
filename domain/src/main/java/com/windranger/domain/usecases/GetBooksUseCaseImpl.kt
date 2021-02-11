package com.windranger.domain.usecases

import com.windranger.domain.models.BookModel
import com.windranger.domain.repositories.BookRepo

class GetBooksUseCaseImpl(private val bookRepo: BookRepo) : GetBooksUseCase {
    override suspend fun execute(params: String?): List<BookModel> {
        params?.let {
            return bookRepo.getBooks(params)
        } ?: throw IllegalArgumentException("Params can`t be null")
    }
}