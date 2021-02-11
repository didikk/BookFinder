package com.windranger.domain.repositories

import com.windranger.domain.models.BookModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface BookRepo {
    fun getBooks(query: String): Single<List<BookModel>>

    fun getBookmarks(): Single<List<BookModel>>

    fun addBookmark(book: BookModel): Completable

    fun removeBookmark(book: BookModel): Completable
}