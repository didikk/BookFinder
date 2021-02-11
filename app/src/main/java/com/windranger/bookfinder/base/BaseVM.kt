package com.windranger.bookfinder.base

import androidx.lifecycle.ViewModel
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseVM : ViewModel() {

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

    companion object {
        const val SOMETHING_WRONG = "Something went wrong"
    }
}