package com.windranger.data.mappers

import com.windranger.data.models.BookResponse
import com.windranger.domain.models.BookModel

class BookResponseMapper : Mapper<BookResponse, List<BookModel>> {
    override fun map(from: BookResponse): List<BookModel> {
        val list = mutableListOf<BookModel>()
        from.items.mapTo(list) { item ->
            with(item.volumeInfo) {
                BookModel(
                    item.id,
                    title,
                    authors?.get(0) ?: "",
                    categories?.get(0) ?: "",
                    publisher ?: "",
                    pageCount,
                    ratingsCount,
                    publishedDate ?: "",
                    previewLink,
                    imageLinks?.thumbnail ?: ""
                )
            }
        }

        return list
    }
}