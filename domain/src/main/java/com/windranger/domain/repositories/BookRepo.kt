package com.windranger.domain.repositories

import com.windranger.domain.models.BookModel
import io.objectbox.Property

interface BookRepo {
    suspend fun getBooks(query: String): List<BookModel>

    suspend fun getBookmarks(): List<BookModel>

    suspend fun addBookmark(book: BookModel)

    suspend fun removeBookmark(uid: Property<BookModel>, book: BookModel)

    suspend fun isInBookmark(uid: Property<BookModel>, book: BookModel): Boolean
}