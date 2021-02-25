package com.windranger.data.repo

import com.windranger.data.api.ApiService
import com.windranger.data.mappers.BookResponseMapper
import com.windranger.domain.models.BookModel
import com.windranger.domain.repositories.BookRepo
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.Property
import io.objectbox.kotlin.boxFor
import io.objectbox.query.QueryBuilder.StringOrder

class BookRepoImpl(
    private val apiService: ApiService,
    private val bookResponseMapper: BookResponseMapper,
    private val boxStore: BoxStore,
) : BookRepo {
    val bookBox: Box<BookModel> by lazy { boxStore.boxFor() }

    override suspend fun getBooks(query: String): List<BookModel> {
        return bookResponseMapper.map(apiService.getBooks(query))
    }

    override suspend fun getBookmarks(): List<BookModel> = bookBox.all

    override suspend fun addBookmark(book: BookModel) {
        bookBox.put(book)
    }

    override suspend fun removeBookmark(uid: Property<BookModel>, book: BookModel) {
        bookBox.query()
            .equal(uid, book.uid, StringOrder.CASE_SENSITIVE)
            .build()
            .remove()
    }

    override suspend fun isInBookmark(uid: Property<BookModel>, book: BookModel): Boolean {
        val result = bookBox.query()
            .equal(uid, book.uid, StringOrder.CASE_SENSITIVE)
            .build()
            .findUnique()

        return result != null
    }
}