package com.windranger.bookfinder.models

import android.os.Parcelable
import com.windranger.domain.models.BookModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: String,
    val title: String,
    val author: String,
    val category: String,
    val publisher: String,
    val pageCount: Int,
    val rating: Float,
    val publishedDate: String,
    val link: String,
    val cover: String,
    val description: String?,
) : Parcelable {

    fun toBookModel(): BookModel {
        return BookModel(
            id,
            title,
            author,
            category,
            publisher,
            pageCount,
            rating,
            publishedDate,
            link,
            cover,
            description
        )
    }

    companion object {
        fun fromBookModel(from: BookModel): Book {
            with(from) {
                return Book(
                    id,
                    title,
                    author,
                    category,
                    publisher,
                    pageCount,
                    rating,
                    publishedDate,
                    link,
                    cover,
                    description
                )
            }
        }
    }
}