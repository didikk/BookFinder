package com.windranger.data.repo

import com.windranger.data.api.ApiService
import com.windranger.data.mappers.BookResponseMapper
import com.windranger.domain.models.BookModel
import com.windranger.domain.repositories.BookRepo

class BookRepoImpl(
    private val apiService: ApiService,
    private val bookResponseMapper: BookResponseMapper,
) : BookRepo {
    override suspend fun getBooks(query: String): List<BookModel> {
        return bookResponseMapper.map(apiService.getBooks(query))
    }

    override suspend fun getBookmarks(): List<BookModel> {
        TODO("Not yet implemented")
    }

    override suspend fun addBookmark(book: BookModel) {
        TODO("Not yet implemented")
    }

    override suspend fun removeBookmark(book: BookModel) {
        TODO("Not yet implemented")
    }
}