package com.windranger.bookfinder.ui.main

import com.windranger.bookfinder.base.mvp.NetPresenter
import com.windranger.bookfinder.util.rx.SchedulerProvider
import com.windranger.domain.usecases.GetBooksUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainPresenter(
    disposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    private val getBooksUseCase: GetBooksUseCase,
) : NetPresenter<MainView>(disposable, schedulerProvider) {

    fun getBooks() {
        getBooksUseCase.execute("Harry potter")
            .compose(singleSchedulers())
            .subscribe(
                {
                    view?.setData(it)
                },
                this::processError
            )
            .track()
    }
}