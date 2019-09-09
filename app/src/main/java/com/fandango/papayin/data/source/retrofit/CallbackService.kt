package com.fandango.papayin.data.source.retrofit


interface CallbackService {
    interface SuccessCallback<T> {
        fun onSuccess(response: T)
    }

    interface ErrorCallback {
        fun onError(exception: Exception)
    }
}
