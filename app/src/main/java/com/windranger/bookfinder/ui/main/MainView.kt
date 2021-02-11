package com.windranger.bookfinder.ui.main

import com.windranger.bookfinder.base.mvp.BaseView
import com.windranger.domain.models.BookModel

interface MainView : BaseView {
    fun setData(data: List<BookModel>)
}