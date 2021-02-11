package com.windranger.data.repo

import com.windranger.data.api.ApiService
import com.windranger.data.mappers.BookResponseMapper
import com.windranger.domain.models.BookModel
import com.windranger.domain.repositories.BookRepo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class BookRepoImpl(
    private val apiService: ApiService,
    private val bookResponseMapper: BookResponseMapper,
) : BookRepo {
    override fun getBooks(query: String): Single<List<BookModel>> {
        return apiService.getBooks(query)
            .map { bookResponseMapper.map(it) }
    }

    override fun getBookmarks(): Single<List<BookModel>> {
        TODO("Not yet implemented")
    }

    override fun addBookmark(book: BookModel): Completable {
        TODO("Not yet implemented")
    }

    override fun removeBookmark(book: BookModel): Completable {
        TODO("Not yet implemented")
    }

}