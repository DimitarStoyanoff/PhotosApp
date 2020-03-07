package com.stoyanoff.kingcrimson.data.remote

data class SafeResponse<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): SafeResponse<T> {
            return SafeResponse(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): SafeResponse<T> {
            return SafeResponse(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): SafeResponse<T> {
            return SafeResponse(Status.LOADING, data, null)
        }
    }
}
