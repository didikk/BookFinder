package com.windranger.bookfinder.base.mvp

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BasePresenter<T>(protected val disposable: CompositeDisposable) {

    protected var view: T? = null

    fun attachView(view: T) {
        this.view = view
    }

    fun detachView() {
        disposable.dispose()
        view = null
    }

    protected fun Disposable.track() {
        disposable.add(this)
    }

    companion object {
        const val SOMETHING_WRONG = "Something went wrong"
    }
}