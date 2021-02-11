package com.windranger.domain.repositories

import com.windranger.domain.models.BookModel

interface BookRepo {
    suspend fun getBooks(query: String): List<BookModel>

    suspend fun getBookmarks(): List<BookModel>

    suspend fun addBookmark(book: BookModel)

    suspend fun removeBookmark(book: BookModel)
}