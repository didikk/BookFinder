package com.windranger.domain.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Index

@Entity
data class BookModel(
    @Id var id: Long = 0,
    @Index val uid: String,
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