package com.windranger.domain.models

data class BookModel(
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
)