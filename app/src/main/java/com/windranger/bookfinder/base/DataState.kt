package com.windranger.bookfinder.base

sealed class DataState<out T : Any> {

    data class Success<out T : Any>(val data: T) : DataState<T>()

    data class Fail(val error: String) : DataState<Nothing>()
}