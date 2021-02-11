package com.windranger.domain.usecases

import com.windranger.domain.models.BookModel
import com.windranger.domain.repositories.BookRepo
import io.reactivex.rxjava3.core.Single
import java.lang.IllegalArgumentException

class GetBooksUseCaseImpl(private val bookRepo: BookRepo) : GetBooksUseCase {
    override fun execute(params: String?): Single<List<BookModel>> {
        params?.let {
            return bookRepo.getBooks(params)
        } ?: throw IllegalArgumentException("Params can`t be null")
    }
}