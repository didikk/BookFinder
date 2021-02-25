package com.windranger.domain.usecases.bookmark

import com.windranger.domain.models.BookModel
import com.windranger.domain.usecases.BaseUseCase

interface CheckDataInBookmarkUseCase : BaseUseCase<BookModel, Boolean>