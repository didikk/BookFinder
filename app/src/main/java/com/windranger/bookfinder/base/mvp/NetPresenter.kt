package com.windranger.bookfinder.base.mvp

import com.windranger.bookfinder.util.rx.SchedulerProvider
import io.reactivex.rxjava3.core.CompletableTransformer
import io.reactivex.rxjava3.core.SingleTransformer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class NetPresenter<T : BaseView>(disposable: CompositeDisposable,
                                          protected val scheduler: SchedulerProvider
)
    : BasePresenter<T>(disposable) {

    protected fun <V> singleSchedulers(): SingleTransformer<V, V> {
        return SingleTransformer<V, V> { upstream ->
            upstream
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe { view?.showLoading() }
                .doAfterTerminate { view?.hideLoading() }
        }
    }

    protected fun completeableSchedulers(): CompletableTransformer {
        return CompletableTransformer { upstream ->
            upstream
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe { view?.showLoading() }
                .doAfterTerminate { view?.hideLoading() }
        }
    }

    protected fun processError(e: Throwable) {
        view?.showMessage(fetchErrorMessage(e))
    }

    protected fun fetchErrorMessage(e: Throwable): String {
        return when (e) {
            is HttpException -> {
                val responseBody = e.response()?.errorBody()
                getErrorMessage(responseBody!!)
            }
            is SocketTimeoutException -> "Connection timed out."
            is IOException -> "Connection lost, please check your connection."
            else -> e.message ?: SOMETHING_WRONG
        }
    }

    private fun getErrorMessage(responseBody: ResponseBody): String {
        return try {
            val jsonObject = JSONObject(responseBody.string())
            jsonObject.getJSONObject("error").getString("message")
        } catch (e: Exception) {
            e.message ?: SOMETHING_WRONG
        }
    }
}