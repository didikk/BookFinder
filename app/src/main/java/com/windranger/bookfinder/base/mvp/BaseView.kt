package com.windranger.bookfinder.base.mvp

interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun showMessage(message: String)
}