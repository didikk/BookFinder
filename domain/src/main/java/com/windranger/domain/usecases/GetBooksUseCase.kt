package com.windranger.domain.usecases

import com.windranger.domain.models.BookModel

interface GetBooksUseCase : BaseUseCase<String, List<BookModel>>