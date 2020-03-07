package com.stoyanoff.kingcrimson.data.remote

import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): SafeResponse<T> {
        return SafeResponse.success(data)
    }

    fun <T : Any> handleException(e: Exception): SafeResponse<T> {
        return when (e) {
            is HttpException -> SafeResponse.error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> SafeResponse.error(getErrorMessage(ErrorCodes.SocketTimeOut.code), null)
            else -> SafeResponse.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }
}
